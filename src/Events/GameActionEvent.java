package Events;

import Setting.Game;

import java.util.EventObject;

public class GameActionEvent extends EventObject {

    private Game _game;

    public void setGame(Game game){
        _game = game;
    }

    public Game getGame(){
        return _game;
    }

    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public GameActionEvent(Object source) {
        super(source);
    }
}
