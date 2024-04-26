package Setting;

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
        notifySpiderMoved(oldWebCross, newWebCross); // Даже если паук не сделал шаг, сообщить, что он попытался сходить
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
//        notifySpiderMoved();
    }

    protected abstract void notifySpiderMoved(WebCross from, WebCross to);

}
