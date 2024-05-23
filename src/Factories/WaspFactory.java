package Factories;

import Setting.Wasp;

public class WaspFactory extends AbstractInsectFactory{
    @Override
    public Wasp createInsect() {
        int size = (int) (Math.random()*10);
        if (Math.round(Math.random() * 10)/10.0 < Wasp._probabilityOfAppearance*(size/10.0))
        {
            return new Wasp(5, null, size);
        }
        return null;
    }
}
