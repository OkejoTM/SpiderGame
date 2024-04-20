package Factories;

import Setting.Mole;

public class MoleFactory extends AbstractInsectFactory{
    @Override
    public Mole createInsect() {
        if (Math.round(Math.random()*10)/10.0 < Mole._probabilityOfAppearance)
        {
            return new Mole(2,null);
        }
        return null;
    }
    
}
