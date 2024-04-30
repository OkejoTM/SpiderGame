package ui;

import Events.*;
import Setting.*;
import ui.cell.BotSpiderWidget;
import ui.cell.InsectWidget;
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
            WebCross webCross = _web.getWebCross(rowIndex, i);
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

        for (Insect insect : _web.getInsects()){
            insect.addInsectControllerActionListener(new InsectController());
        }

        _game.addGameActionListener(new GameStepObserver());
    }

    private class PlayerController implements PlayerControllerActionListener {

        @Override
        public void playerDied(PlayerControllerActionEvent event) {
            PlayerSpider playerSpider = event.getPlayer();
            PlayerSpiderWidget playerSpiderWidget = _widgetFactory.getWidget(playerSpider);
            WebCrossWidget webCrossWidget = _widgetFactory.getWidget(event.getFrom());

            webCrossWidget.removeItem(playerSpiderWidget);
            _widgetFactory.remove(playerSpider);
        }

        @Override
        public void playerMoved(PlayerControllerActionEvent event) {
            PlayerSpiderWidget playerSpiderWidget = _widgetFactory.getWidget(event.getPlayer());
            WebCrossWidget webCrossWidgetFrom = _widgetFactory.getWidget(event.getFrom());
            WebCrossWidget webCrossWidgetTo = _widgetFactory.getWidget(event.getTo());

            webCrossWidgetFrom.removeItem(playerSpiderWidget);
            webCrossWidgetTo.addItem(playerSpiderWidget);
        }
    }

    private class BotController implements BotControllerActionListener {

        @Override
        public void botMoved(BotControllerActionEvent event) {
            BotSpiderWidget botSpiderWidget = _widgetFactory.getWidget(event.getBotSpider());
            WebCrossWidget webCrossWidgetFrom = _widgetFactory.getWidget(event.getFrom());
            WebCrossWidget webCrossWidgetTo = _widgetFactory.getWidget(event.getTo());

            webCrossWidgetFrom.removeItem(botSpiderWidget);
            webCrossWidgetTo.addItem(botSpiderWidget);
        }

        @Override
        public void botDied(BotControllerActionEvent event) {
            BotSpiderWidget botSpiderWidget = _widgetFactory.getWidget(event.getBotSpider());
            WebCrossWidget webCrossWidgetFrom = _widgetFactory.getWidget(event.getFrom());

            webCrossWidgetFrom.removeItem(botSpiderWidget);
            _widgetFactory.remove(event.getBotSpider());
        }
    }

    private class InsectController implements InsectControllerActionListener{

        @Override
        public void insectDied(InsectControllerActionEvent event) {
            InsectWidget insectWidget = _widgetFactory.getWidget(event.getInsect());
            WebCrossWidget webCrossWidget = _widgetFactory.getWidget(event.getFrom());
            webCrossWidget.removeItem(insectWidget);
            _widgetFactory.remove(event.getInsect());
        }
    }

    private class GameStepObserver implements GameActionListener{

        @Override
        public void gameEnded(GameActionEvent event) {
        }

        @Override
        public void gameStepHappened(GameActionEvent event) {
            revalidate();
            repaint();
        }

        @Override
        public void insectsCreated(GameActionEvent event) {
            for (Insect insect : event.getCreatedInsects()){
                _widgetFactory.create(insect);

                insect.addInsectControllerActionListener(new InsectController());
                System.out.println("Insect created at " + insect.getWebCross().getPosition().row() + insect.getWebCross().getPosition().column());
            }
        }



    }
}

