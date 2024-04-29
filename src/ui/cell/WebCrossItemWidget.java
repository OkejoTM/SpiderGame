package ui.cell;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

import ui.cell.WebCrossWidget.Layer;

public abstract class WebCrossItemWidget extends JPanel {

    public enum State {
        DEFAULT,
        SMALL
    }

    protected State cellItemState = State.DEFAULT;


    public WebCrossItemWidget() {
        setState(State.DEFAULT);
        setOpaque(false);
    }

    void setState(State state) {
        cellItemState = state;
        setPreferredSize(getDimension());
        repaint();
        revalidate();
    }

    protected abstract BufferedImage getImage();

    public abstract Layer getLayer();

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
