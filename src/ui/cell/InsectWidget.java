package ui.cell;

import java.awt.*;

import static ui.cell.WebCrossWidget.CELL_SIZE;

public abstract class InsectWidget extends AnimalWidget{

    public InsectWidget(String imagePath, int imageWidth, int imageHeight) {
        super(imagePath, imageWidth, imageHeight);
    }

    @Override
    protected Dimension getDimension() {
        return new Dimension((int) (CELL_SIZE/2.6), (int) (CELL_SIZE/1.58));
    }

}
