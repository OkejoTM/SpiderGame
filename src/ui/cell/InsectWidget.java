package ui.cell;

import ui.utils.ImageUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class InsectWidget extends WebCrossItemWidget{

    protected BufferedImage _image;

    @Override
    public WebCrossWidget.Layer getLayer() {
        return WebCrossWidget.Layer.BOTTOM;
    }

    @Override
    protected Dimension getDimension() {
        return new Dimension(46, 76);
    }

    @Override
    protected BufferedImage getImage() {
        return _image;
    }

    protected void setImage(String path){
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(path));
            image = ImageUtils.resizeImage(image, getDimension().width, getDimension().height);
        } catch (IOException e) {
            e.printStackTrace();
        }
        _image = image;
    }

}
