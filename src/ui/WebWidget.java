package ui;

import Events.*;
import Events.Controllers.AnimalControllerActionEvent;
import Events.Controllers.AnimalControllerActionListener;
import Events.Controllers.SpiderControllerActionEvent;
import Events.Controllers.SpiderControllerActionListener;
import Setting.*;
import ui.cell.*;

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
        _web.getPlayer().addAnimalControllerActionListener(new AnimalController());
        _web.getPlayer().addSpiderControllerActionListener(new SpiderController());

        for (BotSpider bot : _web.getBotSpiders()) {
            bot.addAnimalControllerActionListener(new AnimalController());
            bot.addSpiderControllerActionListener(new SpiderController());
            bot.addGameListener(new GameStepObserver());
        }

        for (Insect insect : _web.getInsects()) {
            insect.addAnimalControllerActionListener(new AnimalController());
        }

        _game.addGameActionListener(new GameStepObserver());
    }

    private class AnimalController implements AnimalControllerActionListener {
        @Override
        public void animalDied(AnimalControllerActionEvent event) {
            Animal animal = event.getAnimal();
            AnimalWidget animalWidget = _widgetFactory.getWidget(animal);
            WebCrossWidget webCrossWidget = _widgetFactory.getWidget(event.getWebCross());

            webCrossWidget.removeItem(animalWidget);
            _widgetFactory.remove(animal);
        }
    }

    private class SpiderController implements SpiderControllerActionListener {
        @Override
        public void spiderMoved(SpiderControllerActionEvent event) {
            AnimalWidget spiderWidget = _widgetFactory.getWidget(event.getSpider());
            WebCrossWidget webCrossWidgetFrom = _widgetFactory.getWidget(event.getFrom());
            WebCrossWidget webCrossWidgetTo = _widgetFactory.getWidget(event.getTo());

            webCrossWidgetFrom.removeItem(spiderWidget);
            webCrossWidgetTo.addItem(spiderWidget);
        }
    }

    private class GameStepObserver implements GameActionListener {

        @Override
        public void gameEnded(GameActionEvent event) {}

        @Override
        public void gameStepHappened(GameActionEvent event) {
            revalidate();
            repaint();
        }

        @Override
        public void insectsCreated(GameActionEvent event) {
            for (Insect insect : event.getCreatedInsects()) {
                _widgetFactory.create(insect);

                insect.addAnimalControllerActionListener(new AnimalController());
            }
        }

        @Override
        public void playerAteInsect(GameActionEvent event) {}
    }
}

