package Utils;

/**
 * Класс Направления
 */
public class Direction {

    private int _angle = 90;

    private Direction(int angle) {
        angle = angle % 360;
        if (angle < 0) angle += 360;

        this._angle = angle;
    }

    // ------------------ Возможные направления ---------------------

    public static Direction north() {
        return new Direction(90);
    }

    public static Direction south() {
        return new Direction(270);
    }

    public static Direction east() {
        return new Direction(0);
    }

    public static Direction west() {
        return new Direction(180);
    }

    public Direction opposite() {
        return new Direction(this._angle + 180);
    }

    @Override
    public boolean equals(Object other) {

        if (other instanceof Direction otherDirect) {
            return _angle == otherDirect._angle;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return this._angle;
    }
}
