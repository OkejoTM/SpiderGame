package Utils;

import Entities.Insect;
import Entities.PlayerSpider;
import Interfaces.IPredator;
import Interfaces.IPrey;

public class PredatorEatBehaviour implements IPredator {
    @Override
    public int eat(IPrey prey) {
        int health = 0;
        if (prey instanceof Insect insect){
            health = insect.getHealth();
            insect.die();
        }
        if (prey instanceof PlayerSpider playerSpider){
            health = playerSpider.getHealth();
            playerSpider.die();
        }
        return health;
    }
}
