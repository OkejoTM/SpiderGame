package Setting;

import Utils.Direction;
import Utils.WebCrossPosition;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;


public class WebCross {
    private WebCrossPosition _position;
    private Animal _animal = null;
    private boolean _isValid;

    public WebCross(WebCrossPosition position){
        _position = position;
        _isValid = true;
    }

    public boolean isValid(){
        return _isValid;
    }

    // ----- Neighbours ------
    private final Map<Direction, WebCross> _neighbours = new HashMap<>();

    public WebCross neighbour(Direction direction){
        if (_neighbours.containsKey(direction)){
            return _neighbours.get(direction);
        }
        return null;
    }

    void setNeighbour(Direction direction, WebCross neighbour){
        if (neighbour != this && !isNeighbour(neighbour)){
            _neighbours.put(direction, neighbour);
            neighbour.setNeighbour(direction.opposite(), this);
        }
    }

    public boolean isNeighbour(WebCross other){
        return _neighbours.containsValue(other);
    }

    public boolean hasNeighbour(Direction direction){
        return _neighbours.containsKey(direction);
    }

    // ----- Animal -----

    public Animal getAnimal(){
        return _animal;
    }

    boolean releaseAnimal(){
        if (_animal == null) {
            return false;
        }
        _animal.setWebCross(null); // Убрать у животного пересечение
        _animal = null; // Убрать пересечение у животного
        return true;
    }

    public boolean setAnimal(Animal animal){

        if (this.getAnimal() == animal){
            return true;
        }
        else if (animal == null){
            releaseAnimal();
        }
        else{
            animal.setWebCross(this);
            _animal = animal;
        }
        return true;
    }

    public WebCrossPosition getPosition() {
        return _position;
    }



    void clear(){
        this.releaseAnimal();
        _isValid = false;
    }
}
