package Setting;

import Events.PlayerActionEvent;
import Events.PlayerActionListener;
import Interfaces.IPrey;

import java.util.ArrayList;

public class PlayerSpider extends Spider implements IPrey {
    public PlayerSpider(int health, WebCross webCross) {
        super(health, webCross);
    }

    @Override
    protected void die() {
        super.die();
        firePlayerDied();
    }

    @Override
    public void getsEaten() {
        this.die();
    }

    @Override
    protected void notifySpiderMoved() {
        firePlayerMoved();
    }

    // ----------------- Listeners for game ------------------------

    private ArrayList<PlayerActionListener> _playerSpiderListenerList = new ArrayList<>();

    public void addPlayerSpiderActionListener(PlayerActionListener listener) {
        _playerSpiderListenerList.add(listener);
    }

    public void removePlayerSpiderActionListener(PlayerActionListener listener) {
        _playerSpiderListenerList.remove(listener);
    }

    protected void firePlayerMoved(){
        for(PlayerActionListener listener : _playerSpiderListenerList){
            PlayerActionEvent event = new PlayerActionEvent(listener);
            event.setPlayer(this);
            listener.playerMoved(event);
        }
    }

    protected void firePlayerDied(){
        for(PlayerActionListener listener : _playerSpiderListenerList){
            PlayerActionEvent event = new PlayerActionEvent(listener);
            event.setPlayer(this);
            listener.playerDied(event);
        }
    }


}
