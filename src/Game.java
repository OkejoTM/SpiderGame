import Entities.*;
import Events.*;
import Setting.*;

import java.util.ArrayList;
import java.util.Iterator;

public class Game {
    private Web _web;
    private Flora _flora;

    public Game(Flora flora){
        createWeb(4);
        _flora = flora;
        _flora.setWeb(_web);
    }

    public void startGame(){
        _flora.instantiateAnimals();

        _web.getPlayer().addPlayerSpiderActionListener(new PlayerSpiderObserver());

        for(var bot : _web.getBotSpiders()){
            bot.addBotSpiderActionListener(new BotSpiderObserver());
        }

        for (var insect : _web.getInsects()){
            insect.addInsectActionListener(new InsectObserver());
        }

    }

    public void endGame(){
        _web.clearWeb();
        _flora.setWeb(null);
        _flora = null;
        _web = null;
        _botsToRemove = null;
        _insectsToRemove = null;
    }

    public void createWeb(int size){
        _web = new Web(size);
    }

    public void moveAllBots(){
        Iterator<BotSpider> botSpiderIterator = _web.getBotSpiders().iterator();
        while (botSpiderIterator.hasNext()){
            BotSpider bot = botSpiderIterator.next();
            bot.makeOptimalMove();
        }
        _web.removeBotSpiders(_botsToRemove);
        _botsToRemove.clear();
    }

    public void disappearInsects(){
        Iterator<Insect> insectIterator = _web.getInsects().iterator();
        while (insectIterator.hasNext()){
            Insect insect = insectIterator.next();
            insect.jumpOff();
        }
        _web.removeInsects(_insectsToRemove);
        _insectsToRemove.clear();
    }

    public Web getWeb(){
        return _web;
    }

    private ArrayList<BotSpider> _botsToRemove = new ArrayList<>();
    private ArrayList<Insect> _insectsToRemove = new ArrayList<>();

    private class PlayerSpiderObserver implements PlayerActionListener {
        @Override
        public void playerDied(PlayerActionEvent event) {
            _web.removePlayer();
            endGame();
        }

        @Override
        public void playerMoved(PlayerActionEvent event) {
            moveAllBots(); // Если сходил паук-игрок, после него должны сходить пауки-боты
            disappearInsects(); // Пропадают насекомые
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
