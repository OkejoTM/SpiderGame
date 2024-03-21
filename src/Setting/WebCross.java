package Setting;

import Entities.*;
import Utils.Direction;


public class WebCross {
    private Web _web;
    private Animal _animal;

    public WebCross(Web web, Animal animal){
        _web = web;
        _animal = animal;
    }

    public Animal getAnimal(){
        return null;
    }

    public WebCross getNextWebCross(Direction direction){
        return null;
    }

    public boolean releaseAnimal(){
        return false;
    }

    public boolean setAnimal(Animal animal){
        return false;
    }

}
