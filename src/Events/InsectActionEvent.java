package Events;

import Setting.Insect;

import java.util.EventObject;

public class InsectActionEvent extends EventObject {

    private Insect _insect;

    public void setInsect(Insect insect){
        _insect = insect;
    }
    public Insect getInsect(){
        return  _insect;
    }

    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public InsectActionEvent(Object source) {
        super(source);
    }
}