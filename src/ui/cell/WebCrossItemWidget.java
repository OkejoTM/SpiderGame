package ui.cell;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
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
        super.paintComponent(g);
        g.drawImage(getImage(), 0, 0, null);
    }
}
