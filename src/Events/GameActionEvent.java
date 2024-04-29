package Events;

import Setting.Game;
import Setting.Insect;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.EventObject;

public class GameActionEvent extends EventObject {

    private Game _game;

    private ArrayList<Insect> _createdInsects;

    public void setGame(Game game){
        _game = game;
    }

    public Game getGame(){
        return _game;
    }

    public void setCreatedInsects(ArrayList<Insect> createdInsects){
        _createdInsects =createdInsects;
    }
    public ArrayList<Insect> getCreatedInsects(){
        return _createdInsects;
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
