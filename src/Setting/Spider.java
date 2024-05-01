package Setting;

import Events.Controllers.SpiderControllerActionEvent;
import Events.Controllers.SpiderControllerActionListener;
import Interfaces.IPrey;
import Utils.Direction;

import java.util.ArrayList;

import static java.lang.Math.max;

public abstract class Spider extends Animal {
    public Spider(int health, WebCross webCross) {
        super(health, webCross);
    }

    public void makeMove(Direction direction) {
        WebCross oldWebCross = this.getWebCross();
        WebCross newWebCross = oldWebCross;
        changeHealth(-1);
        if (!isAlive()) {
            die();
        }
        else if (_webCross.hasNeighbour(direction)) {
            WebCross nextWebCross = _webCross.neighbour(direction);

            if (nextWebCross.getAnimal() instanceof IPrey prey) {
                eat(prey);
            }
            // Если не занята
            if (!nextWebCross.isOccupied()){
                getIntoWebCross(nextWebCross);
                newWebCross = nextWebCross;
            }
        }
        if (isAlive()){
            fireSpiderMovedController(oldWebCross, newWebCross); // Сообщить контроллеру
        }
        notifySpiderMoved(); // Даже если паук не сделал шаг, сообщить, что он попытался сходить
    }

    private void changeHealth(int delta) {
        _health = max(0, _health + delta);
    }

    private void eat(IPrey prey) {
        changeHealth(((Animal) prey).getHealth());
        prey.getsEaten();
        if (prey instanceof Insect) {
            notifySpiderAteInsect();
        }
    }

    private void getIntoWebCross(WebCross nextWebCross) {
        nextWebCross.setAnimal(this);
    }

    protected abstract void notifySpiderMoved();

    protected void notifySpiderAteInsect(){

    }


    private ArrayList<SpiderControllerActionListener> _spiderControllerListenersList = new ArrayList<>();

    public void addSpiderControllerActionListener(SpiderControllerActionListener listener) {
        _spiderControllerListenersList.add(listener);
    }

    public void removeSpiderControllerActionListener(SpiderControllerActionListener listener) {
        _spiderControllerListenersList.remove(listener);
    }

    protected void fireSpiderMovedController(WebCross from, WebCross to){
        for(SpiderControllerActionListener listener : _spiderControllerListenersList){
            SpiderControllerActionEvent event = new SpiderControllerActionEvent(listener);
            event.setSpider(this);
            event.setFrom(from);
            event.setTo(to);
            listener.spiderMoved(event);
        }
    }


}
