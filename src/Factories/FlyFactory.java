package Factories;

import Setting.Fly;

public class FlyFactory extends AbstractInsectFactory{
    @Override
    public Fly createInsect() {
        if (Math.round(Math.random() * 10)/10.0 < Fly._probabilityOfAppearance)
        {
            return new Fly(4, null);
        }
        return null;
    }
}
