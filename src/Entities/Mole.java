package Entities;

import Setting.WebCross;

public class Mole extends Insect{
    public static final double _probabilityOfDisappearance = 0.2;
    public static final double _probabilityOfAppearance = 0.5;

    public Mole(int health, WebCross webCross) {
        super(health, webCross);
    }

    @Override
    public void jumpOff() {
        double probability = Math.round(Math.random() * 10)/10.0;
        if (probability <= _probabilityOfDisappearance){
            die();
        }
    }


}
