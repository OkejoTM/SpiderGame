package Factories;

import Setting.GrassHopper;

public class GrassHopperFactory extends AbstractInsectFactory{
    @Override
    public GrassHopper createInsect() {
        if (Math.round(Math.random() * 10)/10.0 < GrassHopper._probabilityOfAppearance)
        {
            return new GrassHopper(10, null);
        }
        return null;
    }
}
