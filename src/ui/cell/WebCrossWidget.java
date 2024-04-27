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

    public void addItem(WebCrossItemWidget item) { // !!! Какой-то контроль за количеством элементов должен вестись??
        // DONE: Добавлен контроль за кол-вом элеменов
        if (items.size() > 2 ) throw new IllegalArgumentException();
        int index = -1;

        if (items.containsKey(Layer.BOTTOM)) {
            item.setState(WebCrossItemWidget.State.SMALL);
        } else {
            item.setState(WebCrossItemWidget.State.DEFAULT);
        }

        if (items.containsKey(Layer.TOP)) {
            item.setState(WebCrossItemWidget.State.DEFAULT);
            items.get(Layer.TOP).setState(WebCrossItemWidget.State.SMALL);
            index = 0;
        }

        System.out.println(this.getClass().getName()  + " Amount of items :" + items.size());
        items.put(item.getLayer(), item);
        System.out.println(this.getClass().getName()  + " Put in items");
        System.out.println(this.getClass().getName()  + " Amount of items :" + items.size());
        System.out.println(this.getClass().getName()  + " Adding component..");
        add(item, index);
        System.out.println(this.getClass().getName()  + " Done adding component");

    }

    public void removeItem(WebCrossItemWidget item) {
        if (items.containsValue(item)) {
            int index = 0;

            if(item.getLayer() == Layer.BOTTOM) {
                if (items.containsKey(Layer.TOP)) {
                    items.get(Layer.TOP).setState(WebCrossItemWidget.State.DEFAULT);
                }
            }

            if(item.getLayer() == Layer.TOP) {
                if(items.containsKey(Layer.BOTTOM)) {
                    index = 1;
                    items.get(Layer.BOTTOM).setState(WebCrossItemWidget.State.DEFAULT);
                }
            }

            System.out.println(this.getClass().getName()  + " Amount of items :" + items.size());
            System.out.println(this.getClass().getName()  + " remove component :");
            remove(index);
            System.out.println(this.getClass().getName()  + " Done removing");
            System.out.println(this.getClass().getName()  + " Remove from items");
            items.remove(item.getLayer());
            System.out.println(this.getClass().getName()  + " Amount of items :" + items.size());
            repaint();
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