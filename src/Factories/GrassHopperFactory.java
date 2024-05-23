package Factories;

import Setting.GrassHopper;

public class GrassHopperFactory extends AbstractInsectFactory{
    @Override
    public GrassHopper createInsect() {
        int size = (int) (Math.random()*10);
        if (Math.round(Math.random() * 10)/10.0 < GrassHopper._probabilityOfAppearance *(size/10.0))
        {
            return new GrassHopper(6, null, size);
        }
        return null;
    }
}
