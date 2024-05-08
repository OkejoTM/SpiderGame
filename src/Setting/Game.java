package Setting;

import Events.*;

import java.util.ArrayList;

public class Game {
    private Web _web;
    private Flora _flora;

    private enum GameStatus {
        ON_GAME,
        END_GAME
    }

    private GameStatus gameStatus;

    public enum GameLevel {
        EASY,
        MIDDLE,
        HARD
    }

    private final GameLevel _gameLevel;

    public Game(int webSize, GameLevel level) {
        createWeb(webSize);
        _gameLevel = level;
        _flora = new Flora(_web);
        startGame();
    }

    public void startGame() {
        _flora.instantiateAnimals(_gameLevel);

        _web.getPlayer().addPlayerSpiderActionListener(new PlayerSpiderObserver());

        for (BotSpider bot : _web.getBotSpiders()) {
            bot.addBotSpiderActionListener(new BotSpiderObserver());
        }

        for (Insect insect : _web.getInsects()) {
            insect.addInsectActionListener(new InsectObserver());
        }

        gameStatus = GameStatus.ON_GAME;
    }

    public void endGame() {
        System.out.println("Game ended");
        fireGameStepHappened();
        fireGameEnded();
    }

    private void gameStatusChanged() {
        gameStatus = GameStatus.END_GAME;
    }

    private void createWeb(int size) {
        if (_web == null) {
            _web = new Web(size);
        }
    }

    private synchronized void moveAllBots() {
        try {
            for (BotSpider bot : _web.getBotSpiders()) {
                bot.makeOptimalMove();
                Thread.sleep(50);
            }
            _web.removeBotSpiders(_botsToRemove);
            _botsToRemove.clear();
            if (_web.getBotSpiders().isEmpty()) {
                gameStatusChanged();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private synchronized void disappearInsects() {

        try {
            for (Insect insect : _web.getInsects()) {
                insect.jumpOff();
                Thread.sleep(50);
            }
            _web.removeInsects(_insectsToRemove);

            _insectsToRemove.clear();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private synchronized void generateInsects() {
        try {
            ArrayList<Insect> createdInsects = _flora.generateInsects();
            for (Insect insect : createdInsects) {
                insect.addInsectActionListener(new InsectObserver());
                Thread.sleep(50);
            }
            fireInsectsAppeared(createdInsects);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

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
            gameStatusChanged();
            _web.removePlayer();
        }

        @Override
        public void playerMoved(PlayerActionEvent event) {
            moveAllBots(); // Если сходил паук-игрок, после него должны сходить пауки-боты
            disappearInsects(); // Пропадают насекомые
            generateInsects();
            if (gameStatus == GameStatus.END_GAME) {
                endGame();
            }
            fireGameStepHappened();
        }

        @Override
        public void playerAteInsect(PlayerActionEvent event) {
            firePlayerAteInsect();
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

    public void addGameActionListener(GameActionListener listener) {
        _gameListeners.add(listener);
    }

    public void removeGameActionListener(GameActionListener listener) {
        _gameListeners.remove(listener);
    }

    protected void fireGameEnded() {
        for (GameActionListener listener : _gameListeners) {
            GameActionEvent event = new GameActionEvent(listener);
            event.setGame(this);
            listener.gameEnded(event);
        }
    }

    protected void fireGameStepHappened() {
        for (GameActionListener listener : _gameListeners) {
            GameActionEvent event = new GameActionEvent(listener);
            event.setGame(this);
            listener.gameStepHappened(event);
        }
    }

    protected void fireInsectsAppeared(ArrayList<Insect> createdInsects) {
        for (GameActionListener listener : _gameListeners) {
            GameActionEvent event = new GameActionEvent(listener);
            event.setCreatedInsects(createdInsects);
            event.setGame(this);
            listener.insectsCreated(event);
        }
    }

    protected void firePlayerAteInsect() {
        for (GameActionListener listener : _gameListeners) {
            GameActionEvent event = new GameActionEvent(listener);
            event.setGame(this);
            listener.playerAteInsect(event);
        }
    }

}
