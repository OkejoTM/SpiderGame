package ui.cell;
import java.awt.*;

import static ui.cell.WebCrossWidget.CELL_SIZE;

public abstract class AnimalWidget extends WebCrossItemWidget{
    public AnimalWidget(String imagePath, int imageWidth, int imageHeight) {
        super(imagePath, imageWidth, imageHeight);
    }

    @Override
    protected Dimension getDimension() {
        return new Dimension(CELL_SIZE / 2, CELL_SIZE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(getImage(), 0, 0, this);
    }
}
