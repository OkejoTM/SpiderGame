package ui.cell;

import java.awt.*;


public class InsectWidget extends AnimalWidget{

    public InsectWidget(String imagePath, int imageWidth, int imageHeight) {
        super(imagePath, imageWidth, imageHeight);
    }

    @Override
    protected Dimension getDimension() {
        return new Dimension(getImage().getWidth()+30, getImage().getHeight()+40);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = (getWidth() - getImage().getWidth()) / 2;
        int y = (getHeight() - getImage().getHeight()) / 2;
        g.drawImage(getImage(), x, y, null);
    }

}
