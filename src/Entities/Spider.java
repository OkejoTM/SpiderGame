package Entities;

import Interfaces.IPrey;
import Setting.WebCross;
import Utils.Direction;

public abstract class Spider extends Animal {
    public Spider(int health, WebCross webCross) {
        super(health, webCross);
    }

    public void makeMove(Direction direction){
        if (_webCross.hasNext(direction)){
            WebCross nextWebCross = _webCross.getNextWebCross(direction);
            if (nextWebCross.getAnimal() == null){
                goIntoWebCross(nextWebCross);
            }
            else if (nextWebCross.getAnimal() instanceof IPrey prey){
                eat(prey);
                goIntoWebCross(nextWebCross);
            }
        }
    }

    private void goIntoWebCross(WebCross nextWebCross){
        changeHealth(-1);
        if (_health == 0){
            die();
        }else{
            _webCross.releaseAnimal(); // Убрать из текущего перекрестия
            nextWebCross.setAnimal(this); // Поставить животное в след. перекрестие
            setWebCross(nextWebCross); // Поставить животному след. перекрестие
        }
    }

    public void changeHealth(int delta){
        _health += delta;
    }

    public abstract void eat(IPrey prey);

}
