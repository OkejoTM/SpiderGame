package ui.cell;

import Setting.Fly;
import Setting.GrassHopper;

import java.awt.*;

public class GrassHopperWidget extends InsectWidget {

    private final GrassHopper _grassHopper;

    public GrassHopperWidget(GrassHopper grassHopper) {
        super("images/grasshopper.png", 60, 90);
        _grassHopper = grassHopper;
    }

    @Override
    protected Dimension getDimension() {
        return new Dimension(60, 90);
    }
}
