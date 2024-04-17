package Setting;

import Setting.WebCross;

public abstract class Animal {
    protected int _health;
    protected WebCross _webCross;

    public Animal(int health, WebCross webCross) {
        _health = health;
        _webCross = webCross;
    }

    public int getHealth() {
        return _health;
    }

    public WebCross getWebCross() {
        return _webCross;
    }

    // TODO
    void setWebCross(WebCross webCross) {
        // Если разные перекрестия
        if (webCross != null && this.getWebCross() != null && this.getWebCross() != webCross){
            webCross.releaseAnimal(); // Освобождаем устанавливаемую клетку перед назначением ее новому животному
            _webCross.releaseAnimal(); // Освобождаем собственную клетку
        }
        // Если перекрестие назначается в первый раз
        else if (webCross != null && _webCross == null){
            webCross.releaseAnimal();
        }
        _webCross = webCross;
    }

    public void die() {
        _health = 0;
        clear();
    }

    public void clear(){
        if (_webCross != null){
            _webCross.releaseAnimal();
        }
    }
}
