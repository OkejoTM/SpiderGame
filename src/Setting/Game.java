package Setting;

import Entities.*;
import Events.*;
import Factories.*;
import Setting.*;

import java.util.ArrayList;
import java.util.Iterator;

public class Game {
    private Web _web;
    private Flora _flora;
    private static ArrayList<AbstractInsectFactory> factories = new ArrayList<>();
    static {
        factories.add(new MoleFactory());
        factories.add(new WaspFactory());
        factories.add(new FlyFactory());
        factories.add(new GrassHopperFactory());
    }

    public Game(Flora flora, int webSize) {
        createWeb(webSize);
        _flora = flora;
        _flora.setWeb(_web);
    }

    public void startGame() {
        _flora.instantiateAnimals();

        _web.getPlayer().addPlayerSpiderActionListener(new PlayerSpiderObserver());

        for (BotSpider bot : _web.getBotSpiders()) {
            bot.addBotSpiderActionListener(new BotSpiderObserver());
        }

        for (Insect insect : _web.getInsects()) {
            insect.addInsectActionListener(new InsectObserver());
        }

    }

    public void endGame() {
        _web.clearWeb();
        _flora.setWeb(null);
        _flora = null;
        _web = null;
        _botsToRemove = null;
        _insectsToRemove = null;
    }

    public void createWeb(int size) {
        _web = new Web(size);
    }

    private void moveAllBots() {
        for (BotSpider bot : _web.getBotSpiders()) {
            bot.makeOptimalMove();
        }
        _web.removeBotSpiders(_botsToRemove);
        _botsToRemove.clear();
    }

    private void disappearInsects() {
        for (Insect insect : _web.getInsects()) {
            insect.jumpOff();
        }
        _web.removeInsects(_insectsToRemove);
        _insectsToRemove.clear();
    }

    public Web getWeb() {
        return _web;
    }

    private ArrayList<BotSpider> _botsToRemove = new ArrayList<>();
    private ArrayList<Insect> _insectsToRemove = new ArrayList<>();

    private class PlayerSpiderObserver implements PlayerActionListener {
        @Override
        public void playerDied(PlayerActionEvent event) {
            endGame();
        }

        @Override
        public void playerMoved(PlayerActionEvent event) {
            moveAllBots(); // Если сходил паук-игрок, после него должны сходить пауки-боты
            disappearInsects(); // Пропадают насекомые
            _flora.createInsects(factories);
        }
    }

    private class BotSpiderObserver implements BotSpiderActionListener {

        @Override
        public void botMoved(BotSpiderActionEvent event) {

        }

        @Override
        public void botDied(BotSpiderActionEvent event) {
            _botsToRemove.add(event.getBot());
        }
    }

    private class InsectObserver implements InsectActionListener {

        @Override
        public void insectDied(InsectActionEvent event) {
            _insectsToRemove.add(event.getInsect());
        }
    }


}
