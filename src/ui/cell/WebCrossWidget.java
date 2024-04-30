package ui.cell;

import ui.utils.ImageUtils;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class WebCrossWidget extends JPanel {

    private WebCrossItemWidget _item = null;

    public static final int CELL_SIZE = 120;

    public WebCrossWidget() {
        setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
        setBackground(ImageUtils.BACKGROUND_COLOR);
    }

    public void addItem(WebCrossItemWidget item) {
        if (_item == item)
            return;

        if (_item != null) throw new IllegalArgumentException();

        int index = -1;

        item.updateState();

        _item = item;
        add(item, index);
    }

    public void removeItem(WebCrossItemWidget item) {
        if (_item == item) {
            int index = 0;

            remove(index);

            _item = null;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.white);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));
        g2.drawLine(0, CELL_SIZE / 2, CELL_SIZE, CELL_SIZE / 2);
        g2.drawLine(CELL_SIZE / 2, 0, CELL_SIZE / 2, CELL_SIZE);
    }

}
