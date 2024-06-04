package Setting;

import Events.*;
import Utils.BotSpiderMovementAlgorithm;
import Utils.Direction;

import java.util.ArrayList;

/**
 * Класс Паука-Бота
 */
public class BotSpider extends Spider{
    private BotSpiderMovementAlgorithm _botSpiderMovementAlgorithm;

    public BotSpider(int health, WebCross webCross, BotSpiderMovementAlgorithm botSpiderMovementAlgorithm) {
        super(health, webCross);
        _botSpiderMovementAlgorithm = botSpiderMovementAlgorithm;
    }

    public void setMovementAlgorithm(BotSpiderMovementAlgorithm botSpiderMovementAlgorithm){
        if (_botSpiderMovementAlgorithm == null){
            _botSpiderMovementAlgorithm = botSpiderMovementAlgorithm;
        }
    }

    public void makeOptimalMove(){
        Direction direction = _botSpiderMovementAlgorithm.findDirectionToNearest(this.getWebCross());
        if (direction != null){
            makeMove(direction);
        }
    }

    @Override
    void clear() {
        super.clear();
        clearAlgorithm();
    }

    private void clearAlgorithm(){
        _botSpiderMovementAlgorithm = null;
    }

    @Override
    protected void die(){
        super.die();
        fireBotSpiderDied();
    }

    @Override
    protected void notifySpiderMoved() {
        fireBotSpiderMoved();
        fireStepHappened();
    }

    public static BotSpider create(int health){
        return new BotSpider(health, null, null);
    }

    // ----------------- Listeners for game ------------------------

    private ArrayList<BotSpiderActionListener> _botSpiderListenerList = new ArrayList<>();

    public void addBotSpiderActionListener(BotSpiderActionListener listener) {
        _botSpiderListenerList.add(listener);
    }

    public void removeBotSpiderActionListener(BotSpiderActionListener listener) {
        _botSpiderListenerList.remove(listener);
    }

    protected void fireBotSpiderMoved(){
        for(BotSpiderActionListener listener : _botSpiderListenerList){
            BotSpiderActionEvent event = new BotSpiderActionEvent(listener);
            event.setBot(this);
            listener.botMoved(event);
        }
    }

    protected void fireBotSpiderDied(){
        for(BotSpiderActionListener listener : _botSpiderListenerList){
            BotSpiderActionEvent event = new BotSpiderActionEvent(listener);
            event.setBot(this);
            listener.botDied(event);
        }
    }

    private ArrayList<GameActionListener> _gameListeners = new ArrayList<>();

    public void addGameListener(GameActionListener listener){
        _gameListeners.add(listener);
    }

    protected void fireStepHappened(){
        for (GameActionListener listener : _gameListeners){
            listener.gameStepHappened(new GameActionEvent(listener));
        }
    }
}
