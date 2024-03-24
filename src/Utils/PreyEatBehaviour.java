package Utils;

import Entities.Insect;
import Interfaces.IPredator;
import Interfaces.IPrey;

public class PreyEatBehaviour implements IPredator {
    @Override
    public int eat(IPrey prey) {
        if (prey instanceof Insect insect){
            int health = insect.getHealth();
            insect.die();
            return health;
        }
        return 0;
    }
}

