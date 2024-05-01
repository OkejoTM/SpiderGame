package ui.cell;

import java.awt.*;

import static ui.cell.WebCrossWidget.CELL_SIZE;

public class GrassHopperWidget extends AnimalWidget {

    public GrassHopperWidget() {
        super("images/grasshopper.png", CELL_SIZE/2, (int) (CELL_SIZE/1.33));
    }

    @Override
    protected Dimension getDimension() {
        return new Dimension(CELL_SIZE/2, (int) (CELL_SIZE/1.33));
    }
}
