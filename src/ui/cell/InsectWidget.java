package ui.cell;

import java.awt.*;

public abstract class InsectWidget extends WebCrossItemWidget{

    public InsectWidget(String imagePath, int imageWidth, int imageHeight) {
        super(imagePath, imageWidth, imageHeight);
    }

    @Override
    protected Dimension getDimension() {
        return new Dimension(46, 76);
    }

}
