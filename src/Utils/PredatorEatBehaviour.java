package Utils;

import Entities.Insect;
import Entities.PlayerSpider;
import Interfaces.IEatBehaviour;
import Interfaces.IPrey;

public class PredatorEatBehaviour implements IEatBehaviour {
    @Override
    public void eat(IPrey prey) {
        if (prey instanceof Insect insect){
            insect.die();
        }
        if (prey instanceof PlayerSpider playerSpider){
            playerSpider.die();
        }
    }
}
