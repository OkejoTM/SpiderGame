package Entities;

import Interfaces.IDieable;
import Interfaces.IPredator;
import Interfaces.IPrey;
import Setting.WebCross;
import Utils.Algorithm;

public class BotSpider extends Animal implements IPredator, IDieable {
    private Algorithm _algorithm;
    private PlayerSpider _playerSpider;

    public BotSpider(int health, WebCross webCross, Algorithm algorithm) {
        super(health, webCross);
        _playerSpider = new PlayerSpider(health, webCross);
        _algorithm = algorithm;
    }

    public void makeOptimalMove(){

    }

    @Override
    public void die() {

    }

    @Override
    public void eat(IPrey prey) {

    }
}
