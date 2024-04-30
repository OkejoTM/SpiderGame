package ui.cell;

import Setting.Fly;
import Setting.GrassHopper;

import java.awt.*;

import static ui.cell.WebCrossWidget.CELL_SIZE;

public class GrassHopperWidget extends InsectWidget {

    private final GrassHopper _grassHopper;

    public GrassHopperWidget(GrassHopper grassHopper) {
        super("images/grasshopper.png", CELL_SIZE/2, (int) (CELL_SIZE/1.33));
        _grassHopper = grassHopper;
    }

    @Override
    protected Dimension getDimension() {
        return new Dimension(CELL_SIZE/2, (int) (CELL_SIZE/1.33));
    }
}
