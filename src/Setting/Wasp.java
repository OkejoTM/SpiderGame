package Setting;

import Interfaces.IStingable;
import Interfaces.IStinging;

public class Wasp extends Insect implements IStinging {

    public static final double _probabilityOfDisappearance = 0.3;
    public static final double _probabilityOfAppearance = 0.7;

    public Wasp(int health, WebCross webCross, int size) {
        super(health, webCross, size);
    }

    @Override
    public double getProbabilityDisAppearance() {
        return _size/10.0 * _probabilityOfDisappearance;
    }

    @Override
    public void sting(IStingable object) {
        double probability = Math.round(Math.random()*10)/10.0;
        if (probability <= 0.5){
            int health = (int) Math.round(Math.random()*10);
            object.getStung(health);
        }
    }
}
