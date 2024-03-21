package Entities;

import Setting.WebCross;

public abstract class Animal{
    private int _health;
    private WebCross _webCross;

    public Animal(int health, WebCross webCross){
        _health = health;
        _webCross = webCross;
    }

    public int getHealth(){
        return _health;
    }

    public void setHealth(int health){

    }

    public WebCross getWebCross(){
        return null;
    }

    public void setWebCross(WebCross webCross){

    }

    public boolean canMoveInWebCross(WebCross webCross){
        return false;
    }

}
