package Events;

import Setting.BotSpider;
import Setting.PlayerSpider;
import Setting.WebCross;

import java.util.EventObject;

public class BotControllerActionEvent extends EventObject {

    private BotSpider _bot;
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
    public void setBotSpider(BotSpider bot){
        _bot = bot;
    }
    public BotSpider getBotSpider(){
        return  _bot;
    }


    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public BotControllerActionEvent(Object source) {
        super(source);
    }
}
