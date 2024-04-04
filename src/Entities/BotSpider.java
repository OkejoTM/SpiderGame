package Entities;

import Interfaces.IPrey;
import Setting.WebCross;
import Utils.Algorithm;
import Utils.Direction;

public class BotSpider extends Spider{
    private Algorithm _algorithm;

    public BotSpider(int health, WebCross webCross, Algorithm algorithm) {
        super(health, webCross);
        _algorithm = algorithm;
    }

    public void makeOptimalMove(){
        Direction direction = _algorithm.findDirectionToNearest(this.getWebCross());
        if (direction != null){
            makeMove(direction);
        }
    }

    private void clearAlgorithm(){
        _algorithm = null;
    }

    @Override
    public void eat(IPrey prey) {
        int reducingHealth = ((Animal)prey).getHealth();
        changeHealth(reducingHealth);
        if (prey instanceof Insect insect){
            insect.die();
        }
        if (prey instanceof PlayerSpider playerSpider){
            playerSpider.die();
        }
    }

    @Override
    public void die(){
        _health = 0;
        _webCross.releaseAnimal();
        setWebCross(null);
        clearAlgorithm();
    }
}
