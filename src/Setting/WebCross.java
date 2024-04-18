package Setting;

import Utils.Direction;

import java.awt.*;
import java.util.HashMap;


public class WebCross {
    private Point _position;
    private Web _web;
    private Animal _animal = null;
    private boolean _isValid;

    public WebCross(Web web, Point position){
        _web = web;
        if (!isValidPosition(position)){
            throw new RuntimeException("Invalid Position");
        }
        _position = position;
        _isValid = true;
    }

    public Animal getAnimal(){
        return _animal;
    }

    public boolean releaseAnimal(){
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

    public Point getPosition() {
        return (Point)_position.clone();
    }

    public boolean isValidPosition(Point position){
        return position.x < _web.getSize()-1 && position.y < _web.getSize()-1 && position.x >= 0 && position.y >= 0;
    }

    public boolean isValid(){
        return _isValid;
    }

    public void clear(){
        this.releaseAnimal();
        _isValid = false;
    }

    // ------------------ Порождение и проверка смежных позиций ---------------------
    public boolean hasNext(Direction direction){
        Point newPos = calcNewPosition((Point)_position.clone(), direction);
        return isValidPosition(newPos);
    }

    public WebCross getNextWebCross(Direction direction){
        Point newPos = calcNewPosition((Point)_position.clone(), direction);
        return _web.getWebCross(newPos);
    }

    private Point calcNewPosition(Point position, Direction direct) {

        // Таблица смещения для различных направлений: (горизонталь,вертикаль)
        HashMap<Direction, int[]> offset = new HashMap<Direction, int[]>();

        offset.put(Direction.north(), new int[]{0, -1});
        offset.put(Direction.south(), new int[]{0, 1});
        offset.put(Direction.east(), new int[]{1, 0});
        offset.put(Direction.west(), new int[]{-1, 0});

        return new Point(position.x + offset.get(direct)[1], position.y + offset.get(direct)[0]);
    }

    @Override
    public Object clone(){
        return new WebCross(this._web, (Point) this._position.clone());
    }


}
