package Setting;

/**
 * Класс Букашки
 */
public class Mole extends Insect{
    public static final double _probabilityOfDisappearance = 0.1;
    public static final double _probabilityOfAppearance = 0.9;

    public Mole(int health, WebCross webCross, int size) {
        super(health, webCross, size);
    }

    @Override
    public double getProbabilityDisAppearance() {
        return _size/10.0 * _probabilityOfDisappearance;
    }
}
