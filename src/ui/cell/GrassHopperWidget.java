package ui.cell;

import java.awt.*;

import static ui.cell.WebCrossWidget.CELL_SIZE;

public class GrassHopperWidget extends AnimalWidget {

    public GrassHopperWidget() {
        super("images/grasshopper.png", CELL_SIZE/2, CELL_SIZE);

    }

    @Override
    protected Dimension getDimension() {
        return new Dimension(CELL_SIZE/2, CELL_SIZE);
    }
}
