package Entities;

import Interfaces.IEatBehaviour;
import Interfaces.IPrey;
import Setting.WebCross;
import Utils.Direction;

public class PlayerSpider extends Animal implements IPrey {
    private IEatBehaviour _eatBehaviour;

    public PlayerSpider(int health, WebCross webCross, IEatBehaviour eatBehaviour) {
        super(health, webCross);
        _eatBehaviour = eatBehaviour;
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
                int reducingHealth = ((Animal)prey).getHealth();
                _eatBehaviour.eat(prey);
                changeHealth(reducingHealth);
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
    void clearEatBehaviour(){
        _eatBehaviour = null;
    }
}
