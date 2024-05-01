package Events.Controllers;

import Setting.Spider;
import Setting.WebCross;

import java.util.EventObject;

public class SpiderControllerActionEvent extends EventObject {

    private Spider _spider;
    private WebCross _from;
    private WebCross _to;

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

    public void setSpider(Spider spider){
        _spider = spider;
    }

    public Spider getSpider(){
        return _spider;
    }

    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public SpiderControllerActionEvent(Object source) {
        super(source);
    }
}
