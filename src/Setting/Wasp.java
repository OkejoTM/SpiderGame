package Setting;

public class Wasp extends Insect{

    public static final double _probabilityOfDisappearance = 0.4;
    public static final double _probabilityOfAppearance = 0.3;

    public Wasp(int health, WebCross webCross) {
        super(health, webCross);
    }

    @Override
    public void jumpOff() {
        double probability = Math.round(Math.random() * 10)/10.0;
        if (probability <= _probabilityOfDisappearance){
            die();
        }
    }


}