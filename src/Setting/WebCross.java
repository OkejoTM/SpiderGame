package Setting;

import Entities.*;
import Utils.Direction;

import java.awt.*;
import java.util.HashMap;


public class WebCross {
    private Point _position;
    private Web _web;
    private Animal _animal = null;

    public WebCross(Web web, Point position){
        _web = web;
        _position = position;
    }

    public Animal getAnimal(){
        return _animal;
    }

    public boolean releaseAnimal(){
        if (_animal == null) {
            return false;
        }
        removeAnimalFromWeb(_animal); // Если животное осовобождается в результате смерти
        _animal = null;
        return true;
    }

    private void removeAnimalFromWeb(Animal animal){
        if (animal.getHealth() == 0){
            if (animal instanceof Insect) _web.removeInsect((Insect) animal);
            else if (animal instanceof BotSpider) _web.removeBotSpider((BotSpider) animal);
            else if (animal instanceof PlayerSpider) _web.removePlayer();
        }
    }

    public boolean setAnimal(Animal animal){
        if (_animal != null) return false; // Если занято
        _animal = animal;
        return true;
    }

    public Point getPosition() {
        return (Point)_position.clone();
    }

    public boolean isValid(Point position){
        return position.x < _web.getSize()-1 && position.y < _web.getSize()-1 && position.x >= 0 && position.y >= 0;
    }

    // ------------------ Порождение и проверка смежных позиций ---------------------
    public boolean hasNext(Direction direction){
        Point newPos = calcNewPosition((Point)_position.clone(), direction);
        return isValid(newPos);
    }

    public WebCross getNextWebCross(Direction direction){
        Point newPos = calcNewPosition((Point)_position.clone(), direction);
        return _web.getWebCross(newPos);
    }

    private Point calcNewPosition(Point position, Direction direct){

        // Таблица смещения для различных направлений: (горизонталь,вертикаль)
        HashMap<Direction, int [] > offset=  new  HashMap<Direction, int [] >();

        offset.put(Direction.north(),   new int []{ 0, -1} );
        offset.put(Direction.south(),   new int []{ 0,  1} );
        offset.put(Direction.east(),    new int []{ 1,  0} );
        offset.put(Direction.west(),    new int []{-1,  0} );

        return new Point(position.x + offset.get(direct)[1],position.y + offset.get(direct)[0]);
    }

}
