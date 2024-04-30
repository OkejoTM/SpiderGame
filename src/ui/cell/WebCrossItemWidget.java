package ui.cell;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;


import ui.utils.ImageUtils;

public abstract class WebCrossItemWidget extends JPanel {

    protected BufferedImage _image;

    public WebCrossItemWidget(String imagePath, int imageWidth, int imageHeight) {
        updateState();
        setOpaque(false);
        setImage(imagePath, imageWidth, imageHeight);
    }

    private void updateState() {
        setPreferredSize(getDimension());
        repaint();
        revalidate();
    }

    protected BufferedImage getImage() {
        return _image;
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

    @Override
    protected void paintComponent(Graphics g) {
        System.out.println(LocalDateTime.now() + "Trying to repaint super" + super.getClass().getName());
        super.paintComponent(g);
        System.out.println(LocalDateTime.now() + "Trying to repaint" + this.getClass().getName());
        g.drawImage(getImage(), 0, 0, null);
        System.out.println(LocalDateTime.now() + " Repainted" + this.getClass().getName());
        System.out.println("\n");
    }
}
