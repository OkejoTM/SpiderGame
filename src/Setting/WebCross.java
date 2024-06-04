package Setting;

import Utils.Direction;
import Utils.WebCrossPosition;
import java.util.HashMap;
import java.util.Map;


/**
 * Класс Перекрестия
 */
public class WebCross {
    private final WebCrossPosition _position;
    private Animal _animal = null;

    public WebCross(WebCrossPosition position) {
        _position = position;
    }

    public boolean isOccupied() {
        return _animal != null;
    }

    // ----- Neighbours ------
    private final Map<Direction, WebCross> _neighbours = new HashMap<>();

    public WebCross neighbour(Direction direction) {
        if (_neighbours.containsKey(direction)) {
            return _neighbours.get(direction);
        }
        return null;
    }

    void setNeighbour(Direction direction, WebCross neighbour) {
        if (neighbour != this && !isNeighbour(neighbour)) {
            _neighbours.put(direction, neighbour);
            neighbour.setNeighbour(direction.opposite(), this);
        }
    }

    public boolean isNeighbour(WebCross other) {
        return _neighbours.containsValue(other);
    }

    public boolean hasNeighbour(Direction direction) {
        return _neighbours.containsKey(direction);
    }

    // ----- Animal -----
    public Animal getAnimal() {
        return _animal;
    }

    void releaseAnimal() {
        if (_animal == null) {
            return;
        }
        _animal.setWebCross(null); // Убрать у животного пересечение
        _animal = null; // Убрать пересечение у животного
    }

    public boolean setAnimal(Animal animal) {
        if (animal == null) { // Если нужно удалить, удаляем
            releaseAnimal();
        } else if (this.getAnimal() == animal || (animal.getWebCross() != null && !animal.getWebCross().isOccupied())) { // Если одинаковые или животное занято другим
            return true;
        } else if (animal.getWebCross() == null && this.getAnimal() != null) { // Если занято, но у животного нет пересечения
            return true;
        } else {
            animal.setWebCross(this);
            _animal = animal;
        }
        return true;
    }

    public WebCrossPosition getPosition() {
        return _position;
    }

    void clear() {
        this.releaseAnimal();
    }
}
