package Utils;

import Entities.Insect;
import Interfaces.IEatBehaviour;
import Interfaces.IPrey;

public class PreyEatBehaviour implements IEatBehaviour {
    @Override
    public void eat(IPrey prey) {
        if (prey instanceof Insect insect){
            insect.die();
        }
    }
}