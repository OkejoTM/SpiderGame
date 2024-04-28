package ui.cell;

import Setting.Fly;
import Setting.GrassHopper;

public class GrassHopperWidget extends InsectWidget {

    private final GrassHopper _grassHopper;

    public GrassHopperWidget(GrassHopper grassHopper) {
        _grassHopper = grassHopper;
        setImage("images/grasshopper.png");
    }

}
