package Entities;

import Interfaces.IPredator;
import Interfaces.IPrey;
import Setting.WebCross;
import Utils.Direction;

public class PlayerSpider extends Animal implements IPrey {
    private IPredator _preyEatBehaviour;

    public PlayerSpider(int health, WebCross webCross, IPredator eatBehaviour) {
        super(health, webCross);
        _preyEatBehaviour = eatBehaviour;
    }

    public void makeMove(Direction direction){
        if (_webCross.hasNext(direction)){
            WebCross nextWebCross = _webCross.getNextWebCross(direction);
            if (nextWebCross.getAnimal() == null){
                changeHealth(-1);
                if (_health == 0)
                {
                    this.die();
                }else {
                    move(nextWebCross);
                }
            }
            else if (nextWebCross.getAnimal() instanceof IPrey prey){
                changeHealth(_preyEatBehaviour.eat(prey));
                move(nextWebCross);
            }
        }
    }

    private void move(WebCross nextWebCross){
        _webCross.releaseAnimal(); // Убрать из текущего перекрестия
        nextWebCross.setAnimal(this); // Поставить животное в след. перекрестие
        setWebCross(nextWebCross); // Поставить животному след. перекрестие
    }

    public void changeHealth(int delta){
        _health += delta;
    }

    void setEatBehaviour(IPredator behaviour){
        _preyEatBehaviour = behaviour;
    }
}
