package ui.cell;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import ui.utils.ImageUtils;

/**
 * Класс абстрактного предмета Перекрестия
 */
public abstract class WebCrossItemWidget extends JPanel {

    private BufferedImage _image;

    public WebCrossItemWidget(String imagePath, int imageWidth, int imageHeight) {
        setImage(imagePath, imageWidth, imageHeight);
        setOpaque(false);
        updateState();
    }

    protected BufferedImage getImage() {
        return _image;
    }

    private void updateState() {
        setPreferredSize(getDimension());
        repaint();
        revalidate();
    }

    private void setImage(String path, int width, int height) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(path));
            image = ImageUtils.resizeImage(image, width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }
        _image = image;
    }
    protected abstract Dimension getDimension();
}
