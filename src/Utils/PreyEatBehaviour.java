package Utils;

import Entities.Insect;
import Interfaces.IPredator;
import Interfaces.IPrey;

public class PreyEatBehaviour implements IPredator {
    @Override
    public void eat(IPrey prey) {
        if (prey instanceof Insect insect){
            insect.die();
        }
    }
}