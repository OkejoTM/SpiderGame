package Events;

import Setting.PlayerSpider;
import Setting.WebCross;

import java.util.EventObject;

public class PlayerActionEvent extends EventObject {

    private PlayerSpider _player;

    public void setPlayer(PlayerSpider player){
        _player = player;
    }
    public PlayerSpider getPlayer(){
        return  _player;
    }

    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public PlayerActionEvent(Object source) {
        super(source);
    }
}
