package Events;

import Setting.BotSpider;
import Setting.WebCross;

import java.util.EventObject;

public class BotSpiderActionEvent extends EventObject {

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
    public void setBot(BotSpider bot){
        _bot = bot;
    }
    public BotSpider getBot(){
        return  _bot;
    }

    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public BotSpiderActionEvent(Object source) {
        super(source);
    }
}
