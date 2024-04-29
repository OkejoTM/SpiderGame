package ui.cell;

import ui.utils.ImageUtils;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class WebCrossWidget extends JPanel {
    public enum Layer {
        TOP,
        BOTTOM
    }

    private Map<Layer, WebCrossItemWidget> items = new HashMap();

    private static final int CELL_SIZE = 120;

    public WebCrossWidget() {
        setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
        setBackground(ImageUtils.BACKGROUND_COLOR);
    }

    public void addItem(WebCrossItemWidget item) {
        if (items.size() > 1 ) throw new IllegalArgumentException();
        int index = -1;

        item.setState(WebCrossItemWidget.State.DEFAULT);

        items.put(item.getLayer(), item);
        add(item, index);
    }

    public void removeItem(WebCrossItemWidget item) {
        if (items.containsValue(item)) {
            int index = 0;

            remove(index);

            items.remove(item.getLayer());

        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.white);
        g.drawLine(0, CELL_SIZE/2, CELL_SIZE, CELL_SIZE/2);
        g.drawLine(CELL_SIZE/2, 0, CELL_SIZE/2, CELL_SIZE);
        g.setColor(Color.black);
    }

}
