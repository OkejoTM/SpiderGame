package Entities;

import Setting.WebCross;

public class Fly extends Insect{
    public static final double _probabilityOfDisappearance = 0.3;

    public Fly(int health, WebCross webCross) {
        super(health, webCross);
    }

    @Override
    public void jumpOff() {
        double probability = Math.round(Math.random() * 10)/10.0;
        if (probability <= _probabilityOfDisappearance){
            die();
            fireInsectDied();
        }
    }

}
