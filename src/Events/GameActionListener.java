package Events;

public interface GameActionListener {
    void gameEnded(GameActionEvent event);
    void gameStepHappened(GameActionEvent event);
}
