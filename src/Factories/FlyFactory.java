package Factories;

import Setting.Fly;

public class FlyFactory extends AbstractInsectFactory{
    @Override
    public Fly createInsect() {
        int size = (int) (Math.random()*10);
        if (Math.round(Math.random() * 10)/10.0 < Fly._probabilityOfAppearance*(size/10.0))
        {
            return new Fly(4, null, size);
        }
        return null;
    }
}
