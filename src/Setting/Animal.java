package Setting;

import Events.Controllers.AnimalControllerActionEvent;
import Events.Controllers.AnimalControllerActionListener;

import java.util.ArrayList;

public abstract class Animal {
    protected int _health;
    protected WebCross _webCross;

    public Animal(int health, WebCross webCross) {
        _health = health;
        _webCross = webCross;
        if (_webCross != null){
            webCross.setAnimal(this);
        }
    }

    public int getHealth() {
        return _health;
    }

    public boolean isAlive() {
        return _health > 0;
    }

    public WebCross getWebCross() {
        return _webCross;
    }

    void setWebCross(WebCross webCross) {
        if (this.getWebCross() == webCross){
            return;
        }
        if (webCross != null && _webCross != null){
            _webCross.releaseAnimal(); // Освобождаем собственную клетку
        }
        _webCross = webCross;
    }

    protected void die() {
        _health = 0;
        fireAnimalDiedController(this.getWebCross());
        clear();
    }

    void clear() {
        if (_webCross != null) {
            _webCross.clear();
        }
    }

    private ArrayList<AnimalControllerActionListener> _animalControllerListenerList = new ArrayList<>();

    public void addAnimalControllerActionListener(AnimalControllerActionListener listener) {
        _animalControllerListenerList.add(listener);
    }

    public void removeAnimalControllerActionListener(AnimalControllerActionListener listener) {
        _animalControllerListenerList.remove(listener);
    }

    protected void fireAnimalDiedController(WebCross webCross){
        for(AnimalControllerActionListener listener : _animalControllerListenerList){
            AnimalControllerActionEvent event = new AnimalControllerActionEvent(listener);
            event.setAnimal(this);
            event.setWebCross(webCross);
            listener.animalDied(event);
        }
    }


}
