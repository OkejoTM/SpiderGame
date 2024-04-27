package Setting;

import Events.PlayerActionEvent;
import Events.PlayerActionListener;
import Events.PlayerControllerActionEvent;
import Events.PlayerControllerActionListener;
import Interfaces.IPrey;

import java.util.ArrayList;

public class PlayerSpider extends Spider implements IPrey {
    public PlayerSpider(int health, WebCross webCross) {
        super(health, webCross);
    }

    @Override
    protected void die() {
        firePlayerDiedController(this.getWebCross());
        super.die();
        firePlayerDied();
    }

    @Override
    public void getsEaten() {
        this.die();
    }

    @Override
    protected void notifySpiderMoved(WebCross from, WebCross to) {

        firePlayerMovedController(from, to);
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

    // ----------------- Listeners for widgets ------------------------

    private ArrayList<PlayerControllerActionListener> _playerControllerListenerList = new ArrayList<>();

    public void addPlayerControllerActionListener(PlayerControllerActionListener listener) {
        _playerControllerListenerList.add(listener);
    }

    public void removePlayerControllerActionListener(PlayerControllerActionListener listener) {
        _playerControllerListenerList.remove(listener);
    }

    protected void firePlayerMovedController(WebCross from, WebCross to){
        for(PlayerControllerActionListener listener : _playerControllerListenerList){
            PlayerControllerActionEvent event = new PlayerControllerActionEvent(listener);
            event.setPlayer(this);
            event.setFrom(from);
            event.setTo(to);
            listener.playerMoved(event);
        }
    }

    protected void firePlayerDiedController(WebCross from){
        for(PlayerControllerActionListener listener : _playerControllerListenerList){
            PlayerControllerActionEvent event = new PlayerControllerActionEvent(listener);
            event.setPlayer(this);
            event.setFrom(from);
            listener.playerDied(event);
        }
    }


}
