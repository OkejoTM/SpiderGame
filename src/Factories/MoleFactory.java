package Factories;

import Setting.Mole;

public class MoleFactory extends AbstractInsectFactory{
    @Override
    public Mole createInsect() {
        int size = (int) (Math.random()*10);
        if (Math.round(Math.random()*10)/10.0 < Mole._probabilityOfAppearance *(size/10.0))
        {
            return new Mole(2,null, size);
        }
        return null;
    }
    
}
