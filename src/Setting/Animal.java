package Setting;

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
        _webCross = webCross;
    }

    protected void die() {
        _health = 0;
        clear();
    }

    void clear() {
        if (_webCross != null) {
            _webCross.clear();
        }
    }

}
