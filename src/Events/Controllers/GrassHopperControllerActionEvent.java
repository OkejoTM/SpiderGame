package Events.Controllers;

import Setting.GrassHopper;
import Setting.WebCross;

import java.util.EventObject;

public class GrassHopperControllerActionEvent extends EventObject {

    private GrassHopper _grassHopper;
    private WebCross _from;
    private WebCross _to;

    public GrassHopper getGrassHopper() {
        return _grassHopper;
    }

    public void setGrassHopper(GrassHopper grassHopper) {
        _grassHopper = grassHopper;
    }

    public void setFrom(WebCross from) {
        _from = from;
    }

    public WebCross getFrom() {
        return _from;
    }

    public void setTo(WebCross to) {
        _to = to;
    }

    public WebCross getTo() {
        return _to;
    }


    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public GrassHopperControllerActionEvent(Object source) {
        super(source);
    }


}
