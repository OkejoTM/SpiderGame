package Setting;

import Events.*;
import Utils.BotSpiderMovementAlgorithm;
import Utils.Direction;

import java.util.ArrayList;

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
    public void clear() {
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
    protected void notifySpiderMoved(WebCross from, WebCross to) {
        fireBotSpiderMoved(from, to);
        fireBotMovedController(from, to);
    }

    private ArrayList<BotSpiderActionListener> _botSpiderListenerList = new ArrayList<>();

    public void addBotSpiderActionListener(BotSpiderActionListener listener) {
        _botSpiderListenerList.add(listener);
    }

    public void removeBotSpiderActionListener(BotSpiderActionListener listener) {
        _botSpiderListenerList.remove(listener);
    }

    protected void fireBotSpiderMoved(WebCross from, WebCross to){
        for(BotSpiderActionListener listener : _botSpiderListenerList){
            BotSpiderActionEvent event = new BotSpiderActionEvent(listener);
            event.setBot(this);
            event.setFrom(from);
            event.setTo(to);
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

    private ArrayList<BotControllerActionListener> _botControllerListenerList = new ArrayList<>();

    public void addBotControllerActionListener(BotControllerActionListener listener) {
        _botControllerListenerList.add(listener);
    }

    public void removeBotControllerActionListener(BotControllerActionListener listener) {
        _botControllerListenerList.remove(listener);
    }

    protected void fireBotMovedController(WebCross from, WebCross to){
        for(BotControllerActionListener listener : _botControllerListenerList){
            BotControllerActionEvent event = new BotControllerActionEvent(listener);
            event.setBotSpider(this);
            event.setFrom(from);
            event.setTo(to);
            listener.botMoved(event);
        }
    }


}
