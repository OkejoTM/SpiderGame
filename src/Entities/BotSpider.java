package Entities;

import Events.BotSpiderActionEvent;
import Events.BotSpiderActionListener;
import Interfaces.IPrey;
import Setting.WebCross;
import Utils.Algorithm;
import Utils.Direction;

import java.util.ArrayList;

public class BotSpider extends Spider{
    private Algorithm _algorithm;

    public BotSpider(int health, WebCross webCross, Algorithm algorithm) {
        super(health, webCross);
        _algorithm = algorithm;
    }

    public void makeOptimalMove(){
        Direction direction = _algorithm.findDirectionToNearest(this.getWebCross());
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
        _algorithm = null;
    }

    @Override
    public void die(){
        super.die();
        fireBotSpiderDied();
    }

    @Override
    protected void notifySpiderMoved() {
        fireBotSpiderMoved();
    }

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

    @Override
    public Object clone(){
        return new BotSpider(this._health, (WebCross)_webCross.clone(), this._algorithm);
    }
}
