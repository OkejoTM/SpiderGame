package Entities;

import Setting.WebCross;

public class GrassHopper extends Insect{
    public static final double _probabilityOfDisappearance = 0.5;
    public static final double _probabilityOfAppearance = 0.1;

    public GrassHopper(int health, WebCross webCross) {
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
