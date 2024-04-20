package Events;

import Setting.BotSpider;

import java.util.EventObject;

public class BotSpiderActionEvent extends EventObject {

    private BotSpider _bot;

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
