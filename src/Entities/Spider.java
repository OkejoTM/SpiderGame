package Entities;

import Interfaces.IPrey;
import Setting.Animal;
import Setting.WebCross;
import Utils.Direction;

public abstract class Spider extends Animal {
    public Spider(int health, WebCross webCross) {
        super(health, webCross);
    }

    // TODO
    public void makeMove(Direction direction){
        if (_webCross.hasNext(direction)){
            WebCross nextWebCross = _webCross.getNextWebCross(direction);
            if (nextWebCross.getAnimal() == null){
                getIntoWebCross(nextWebCross);
            }
            else if (nextWebCross.getAnimal() instanceof IPrey prey){
                eat(prey);
                getIntoWebCross(nextWebCross);
            }
        }
    }

    private void changeHealth(int delta){
        if(delta > _health){
            _health = 0;
        }
        else{
            _health += delta;
        }
    }

    private void eat(IPrey prey){
        int reducingHealth = ((Animal)prey).getHealth();
        changeHealth(reducingHealth);
        prey.getsEaten();
    }

    private void getIntoWebCross(WebCross nextWebCross){
        changeHealth(-1);
        if (getHealth() == 0){
            die();
        }else{
            nextWebCross.setAnimal(this);
            notifySpiderMoved();
        }
    }

    protected abstract void notifySpiderMoved();

}
