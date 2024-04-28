package Events;

import Setting.Insect;
import Setting.WebCross;

import java.util.EventObject;

public class InsectControllerActionEvent extends EventObject {

    private Insect _insect;
    private WebCross _from;

    public void setInsect(Insect insect){
        _insect = insect;
    }

    public Insect getInsect(){
        return _insect;
    }

    public void setFrom(WebCross from){
        _from = from;
    }

    public WebCross getFrom(){
        return _from;
    }


    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public InsectControllerActionEvent(Object source) {
        super(source);
    }
}
