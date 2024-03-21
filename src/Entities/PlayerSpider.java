package Entities;

import Interfaces.IDieable;
import Interfaces.IPredator;
import Interfaces.IPrey;
import Setting.WebCross;
import Utils.Direction;

public class PlayerSpider extends Animal implements IPredator, IDieable, IPrey {

    public PlayerSpider(int health, WebCross webCross) {
        super(health, webCross);
    }

    public void makeMove(Direction direction){

    }

    public void changeHealth(int delta){

    }

    @Override
    public void die() {

    }

    @Override
    public void eat(IPrey prey) {

    }
}
