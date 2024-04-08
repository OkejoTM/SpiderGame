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

    private void clearAlgorithm(){
        _algorithm = null;
    }

    @Override
    public void die(){
        _health = 0;
        _webCross.releaseAnimal();
        clearAlgorithm();
        fireBotSpiderDied();
    }

    @Override
    public void getIntoWebCross(WebCross nextWebCross) {
        _webCross.releaseAnimal(); // Убрать из текущего перекрестия
        nextWebCross.setAnimal(this); // Поставить животное в след. перекрестие
        setWebCross(nextWebCross); // Поставить животному след. перекрестие
        fireBotSpiderDied();
    }

    private ArrayList<BotSpiderActionListener> _botSpiderListenerList = new ArrayList<>();

    public void addBotSpiderActionListener(BotSpiderActionListener listener) {
        _botSpiderListenerList.add(listener);
    }

    public void removeBotSpiderActionListener(BotSpiderActionListener listener) {
        _botSpiderListenerList.remove(listener);
    }

    public void fireBotSpiderMoved(){
        for(BotSpiderActionListener listener : _botSpiderListenerList){
            BotSpiderActionEvent event = new BotSpiderActionEvent(listener);
            event.setBot(this);
            listener.botMoved(event);
        }
    }

    public void fireBotSpiderDied(){
        for(BotSpiderActionListener listener : _botSpiderListenerList){
            BotSpiderActionEvent event = new BotSpiderActionEvent(listener);
            event.setBot(this);
            listener.botDied(event);
        }
    }


}
