package Factories;

import Entities.Wasp;

public class WaspFactory extends AbstractInsectFactory{
    @Override
    public Wasp createInsect() {
        if (Math.round(Math.random() * 10)/10.0 < Wasp._probabilityOfAppearance)
        {
            return new Wasp(6, null);
        }
        return null;
    }
}
