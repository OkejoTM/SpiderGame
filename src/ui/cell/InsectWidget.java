package ui.cell;

import java.awt.*;

import static ui.cell.WebCrossWidget.CELL_SIZE;

/**
 * Класс виджета Насекомого
 */
public class InsectWidget extends AnimalWidget{

    public InsectWidget(String imagePath, int imageWidth, int imageHeight) {
        super(imagePath, imageWidth, imageHeight);
    }

    @Override
    protected Dimension getDimension() {
        return new Dimension(CELL_SIZE, CELL_SIZE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        int x = (getWidth() - getImage().getWidth()) / 2;
        int y = (getHeight() - getImage().getHeight()) / 2;
        g.drawImage(getImage(), x, y, null);
    }
}
