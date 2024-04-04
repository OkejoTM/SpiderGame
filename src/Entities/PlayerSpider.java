package Entities;

import Interfaces.IEatBehaviour;
import Interfaces.IPrey;
import Setting.WebCross;
import Utils.Direction;

public class PlayerSpider extends Spider implements IPrey {


    public PlayerSpider(int health, WebCross webCross) {
        super(health, webCross);

    }

    @Override
    public void eat(IPrey prey) {
        int reducingHealth = ((Animal)prey).getHealth();
        changeHealth(reducingHealth);
        if (prey instanceof Insect insect){
            insect.die();
        }
    }


}
