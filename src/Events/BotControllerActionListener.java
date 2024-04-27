package Events;

public interface BotControllerActionListener {
    void botMoved(BotControllerActionEvent event);
    void botDied(BotControllerActionEvent event);
}
