package Setting;

public class Fly extends Insect{
    public static final double _probabilityOfDisappearance = 0.2;
    public static final double _probabilityOfAppearance = 1;

    public Fly(int health, WebCross webCross, int size) {
        super(health, webCross, size);
    }

    @Override
    public double getProbabilityDisAppearance() {
        return _size/10.0 * _probabilityOfDisappearance;
    }
}
