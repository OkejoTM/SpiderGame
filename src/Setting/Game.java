package Setting;

import Events.*;

import java.util.ArrayList;

public class Game {
    private Web _web;
    private Flora _flora;

    public Game(int webSize) {
        createWeb(webSize);
        _flora = new Flora(_web);
        startGame();
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
        System.out.println("Game ended");
        fireGameStepHappened();
        fireGameEnded();
    }

    private void createWeb(int size) {
        if (_web == null){
            _web = new Web(size);
        }
    }

    private void moveAllBots() {
        for (BotSpider bot : _web.getBotSpiders()) {
            bot.makeOptimalMove();
        }
        _web.removeBotSpiders(_botsToRemove);
        _botsToRemove.clear();
        if (_web.getBotSpiders().isEmpty()){
            fireGameEnded();
        }
    }

    private void disappearInsects() {
        for (Insect insect : _web.getInsects()) {
            insect.jumpOff();
        }
        _web.removeInsects(_insectsToRemove);

        _insectsToRemove.clear();
    }

    private void generateInsects(){
        ArrayList<Insect> createdInsects = _flora.generateInsects();
        for (Insect insect : createdInsects){
            insect.addInsectActionListener(new InsectObserver());
        }
        fireInsectsAppeared(createdInsects);
    }

    public Web getWeb() {
        return _web;
    }

    private ArrayList<BotSpider> _botsToRemove = new ArrayList<>();
    private ArrayList<Insect> _insectsToRemove = new ArrayList<>();

    // --------------------------- Game Observes ---------------------------------------

    private class PlayerSpiderObserver implements PlayerActionListener {
        @Override
        public void playerDied(PlayerActionEvent event) {
            endGame();
        }

        @Override
        public void playerMoved(PlayerActionEvent event) {
            moveAllBots(); // Если сходил паук-игрок, после него должны сходить пауки-боты
            disappearInsects(); // Пропадают насекомые
            generateInsects();
            fireGameStepHappened();
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

        @Override
        public void insectWasEaten(InsectActionEvent event) {
            _insectsToRemove.add(event.getInsect());
            _web.removeInsects(_insectsToRemove);
            _insectsToRemove.clear();
        }
    }

    // --------------------------- Game Listeners ---------------------------------------

    private ArrayList<GameActionListener> _gameListeners = new ArrayList<>();

    public void addGameActionListener(GameActionListener listener){
        _gameListeners.add(listener);
    }

    public void removeGameActionListener(GameActionListener listener){
        _gameListeners.remove(listener);
    }

    protected void fireGameEnded(){
        for(GameActionListener listener : _gameListeners){
            GameActionEvent event = new GameActionEvent(listener);
            event.setGame(this);
            listener.gameEnded(event);
        }
    }

    protected void fireGameStepHappened(){
        for(GameActionListener listener : _gameListeners){
            GameActionEvent event = new GameActionEvent(listener);
            event.setGame(this);
            listener.gameStepHappened(event);
        }
    }

    protected void fireInsectsAppeared(ArrayList<Insect> createdInsects){
        for (GameActionListener listener : _gameListeners){
            GameActionEvent event = new GameActionEvent(listener);
            event.setCreatedInsects(createdInsects);
            event.setGame(this);
            listener.insectsCreated(event);
        }
    }



}
