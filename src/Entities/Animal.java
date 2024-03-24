package Entities;

import Interfaces.IDieable;
import Setting.WebCross;

public abstract class Animal implements IDieable {
    protected int _health;
    protected WebCross _webCross;

    public Animal(int health, WebCross webCross){
        _health = health;
        _webCross = webCross;
    }

    public int getHealth(){
        return _health;
    }

    public void setHealth(int health){
        _health = health;
    }

    public WebCross getWebCross(){
        return _webCross;
    }

    public void setWebCross(WebCross webCross){
        _webCross = webCross;
    }

    @Override
    public void die() {
        _health = 0;
        _webCross.releaseAnimal();
        setWebCross(null);
        if (this instanceof PlayerSpider playerSpider){
            playerSpider.setEatBehaviour(null);
        }
        if (this instanceof BotSpider botSpider){
            botSpider.setEatBehaviour(null);
        }
    }
}
