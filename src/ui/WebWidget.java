package ui;

import Events.PlayerActionEvent;
import Events.PlayerActionListener;
import Events.PlayerControllerActionEvent;
import Events.PlayerControllerActionListener;
import Setting.PlayerSpider;
import Setting.Web;
import Setting.WebCross;
import ui.cell.PlayerSpiderWidget;
import ui.cell.WebCrossWidget;

import javax.swing.*;

public class WebWidget extends JPanel {

    private final Web _web;
    private final WidgetFactory _widgetFactory;

    public WebWidget(Web web, WidgetFactory widgetFactory) {
        _web = web;
        _widgetFactory = widgetFactory;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        fillWeb();
        subscribeOnEntities();
    }

    private void fillWeb() {
        for (int i = 0; i < _web.getSize(); ++i) {
            JPanel row = createRow(i);
            add(row);
        }
    }

    private JPanel createRow(int rowIndex) {
        JPanel row = new JPanel();
        row.setLayout(new BoxLayout(row, BoxLayout.X_AXIS));

        for (int i = 0; i < _web.getSize(); i++) {
            WebCross webCross = _web.getWebCross(rowIndex, i); // TODO точно ли (rowIndex, i)
            WebCrossWidget webCrossWidget = _widgetFactory.create(webCross);

            row.add(webCrossWidget);
        }
        return row;
    }

    private void subscribeOnEntities() {
        _web.getPlayer().addPlayerControllerActionListener(new PlayerController());
    }

    private class PlayerController implements PlayerControllerActionListener {


//        @Override
//        public void playerDied(PlayerActionEvent event) {
//            PlayerSpider playerSpider = event.getPlayer();
//            WebCrossWidget webCrossWidget = _widgetFactory.getWidget(playerSpider.getWebCross());
//            PlayerSpiderWidget playerSpiderWidget = _widgetFactory.getWidget(playerSpider);
//            webCrossWidget.removeItem(playerSpiderWidget);
//            _widgetFactory.remove(playerSpider);
//        }

        @Override
        public void playerMoved(PlayerControllerActionEvent event) {
            System.out.println(this.getClass().getName()  + " Doing listener method");
            PlayerSpiderWidget playerSpiderWidget = _widgetFactory.getWidget(event.getPlayer());
            WebCrossWidget webCrossWidgetFrom = _widgetFactory.getWidget(event.getFrom());
            WebCrossWidget webCrossWidgetTo = _widgetFactory.getWidget(event.getTo());
            System.out.println(this.getClass().getName()  + " removing from old");
            webCrossWidgetFrom.removeItem(playerSpiderWidget);
            System.out.println(this.getClass().getName()  + " setting to new");
            webCrossWidgetTo.addItem(playerSpiderWidget);
            System.out.println(this.getClass().getName()  + " Done listener method");
        }
    }
}

