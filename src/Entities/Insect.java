package Entities;

import Interfaces.IDieable;
import Interfaces.IPrey;
import Setting.WebCross;

public abstract class Insect extends Animal implements IPrey {
    public Insect(int health, WebCross webCross) {
        super(health, webCross);
    }

}
