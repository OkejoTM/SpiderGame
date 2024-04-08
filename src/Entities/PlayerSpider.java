package Entities;

import Events.BotSpiderActionEvent;
import Events.BotSpiderActionListener;
import Events.PlayerActionEvent;
import Events.PlayerActionListener;
import Interfaces.IPrey;
import Setting.WebCross;

import java.util.ArrayList;

public class PlayerSpider extends Spider implements IPrey {
    public PlayerSpider(int health, WebCross webCross) {
        super(health, webCross);

    }

    @Override
    public void die() {
        _health = 0;
        _webCross.releaseAnimal();
        firePlayerDied();
    }

    @Override
    public void getIntoWebCross(WebCross nextWebCross) {
        _webCross.releaseAnimal(); // Убрать из текущего перекрестия
        nextWebCross.setAnimal(this); // Поставить животное в след. перекрестие
        setWebCross(nextWebCross); // Поставить животному след. перекрестие
        firePlayerMoved();
    }

    @Override
    public void getsEaten() {
        die();
        firePlayerDied();
    }


    private ArrayList<PlayerActionListener> _playerSpiderListenerList = new ArrayList<>();

    public void addPlayerSpiderActionListener(PlayerActionListener listener) {
        _playerSpiderListenerList.add(listener);
    }

    public void removePlayerSpiderActionListener(PlayerActionListener listener) {
        _playerSpiderListenerList.remove(listener);
    }

    public void firePlayerMoved(){
        for(PlayerActionListener listener : _playerSpiderListenerList){
            PlayerActionEvent event = new PlayerActionEvent(listener);
            event.setPlayer(this);
            listener.playerMoved(event);
        }
    }

    public void firePlayerDied(){
        for(PlayerActionListener listener : _playerSpiderListenerList){
            PlayerActionEvent event = new PlayerActionEvent(listener);
            event.setPlayer(this);
            listener.playerDied(event);
        }
    }
}
