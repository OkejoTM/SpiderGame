package ui;

import Events.*;
import Setting.*;
import ui.cell.BotSpiderWidget;
import ui.cell.PlayerSpiderWidget;
import ui.cell.WebCrossWidget;

import javax.swing.*;

public class WebWidget extends JPanel {

    private final Web _web;
    private final WidgetFactory _widgetFactory;
    private final Game _game;

    public WebWidget(Web web, WidgetFactory widgetFactory, Game game) {
        _web = web;
        _widgetFactory = widgetFactory;
        _game = game;
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

        for (BotSpider bot : _web.getBotSpiders()){
            bot.addBotControllerActionListener(new BotController());
        }

        _game.addGameActionListener(new GameStepObserver());
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

    private class BotController implements BotControllerActionListener {

        @Override
        public void botMoved(BotControllerActionEvent event) {
            System.out.println(this.getClass().getName()  + " Doing listener method");
            BotSpiderWidget botSpiderWidget = _widgetFactory.getWidget(event.getBotSpider());
            WebCrossWidget webCrossWidgetFrom = _widgetFactory.getWidget(event.getFrom());
            WebCrossWidget webCrossWidgetTo = _widgetFactory.getWidget(event.getTo());

            System.out.println(this.getClass().getName()  + " removing from old");
            webCrossWidgetFrom.removeItem(botSpiderWidget);
            System.out.println(this.getClass().getName()  + " setting to new");
            webCrossWidgetTo.addItem(botSpiderWidget);
            System.out.println(this.getClass().getName()  + " Done listener method");
        }

        @Override
        public void botDied(BotControllerActionEvent event) {
            BotSpiderWidget botSpiderWidget = _widgetFactory.getWidget(event.getBotSpider());
            WebCrossWidget webCrossWidgetFrom = _widgetFactory.getWidget(event.getFrom());
            WebCrossWidget webCrossWidgetTo = _widgetFactory.getWidget(event.getTo());

            webCrossWidgetFrom.removeItem(botSpiderWidget);
            webCrossWidgetTo.addItem(botSpiderWidget);
        }
    }

    private class GameStepObserver implements GameActionListener{

        @Override
        public void gameEnded(GameActionEvent event) {

        }

        @Override
        public void gameStepHappened(GameActionEvent event) {
            repaint();
        }
    }
}

