package Entities;

import Interfaces.IDieable;
import Interfaces.IPrey;
import Setting.WebCross;

public abstract class Insect extends Animal implements IDieable, IPrey {

    public Insect(int health, WebCross webCross) {
        super(health, webCross);
    }

    @Override
    public void die(){

    }
}
