package Entities;

import Interfaces.IMoveable;
import Interfaces.IPrey;
import Setting.WebCross;
import Utils.Direction;

public abstract class Spider extends Animal implements IMoveable {
    public Spider(int health, WebCross webCross) {
        super(health, webCross);
    }

    public void makeMove(Direction direction){
        if (_webCross.hasNext(direction)){
            WebCross nextWebCross = _webCross.getNextWebCross(direction);
            if (nextWebCross.getAnimal() == null){
                go(nextWebCross);
            }
            else if (nextWebCross.getAnimal() instanceof IPrey prey){
                eat(prey);
                go(nextWebCross);
            }
        }
    }

    public void changeHealth(int delta){
        _health += delta;
    }

    public void eat(IPrey prey){
        int reducingHealth = ((Animal)prey).getHealth();
        changeHealth(reducingHealth);
        prey.getsEaten();
    }

    private void go(WebCross nextWebCross){
        changeHealth(-1);
        if (_health == 0){
            die();
        }else{
            getIntoWebCross(nextWebCross);
        }
    }

}
