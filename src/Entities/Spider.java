package Entities;

import Interfaces.IPrey;
import Setting.Animal;
import Setting.WebCross;
import Utils.Direction;

import static java.lang.Math.max;

public abstract class Spider extends Animal {
    public Spider(int health, WebCross webCross) {
        super(health, webCross);
    }

    public void makeMove(Direction direction) {
        changeHealth(-1);
        if (getHealth() == 0) {
            die();
        }
        if (this.getHealth() != 0 && _webCross.hasNext(direction)) {
            WebCross nextWebCross = _webCross.getNextWebCross(direction);
            if (nextWebCross.getAnimal() instanceof IPrey prey) {
                eat(prey);
            }
            getIntoWebCross(nextWebCross);
        }
    }

    private void changeHealth(int delta) {
        _health = max(0, _health + delta);
    }

    private void eat(IPrey prey) {
        changeHealth(((Animal) prey).getHealth());
        prey.getsEaten();
    }

    private void getIntoWebCross(WebCross nextWebCross) {
        nextWebCross.setAnimal(this);
        notifySpiderMoved();
    }

    protected abstract void notifySpiderMoved();

}
