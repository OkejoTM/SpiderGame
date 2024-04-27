package Events;

public interface PlayerControllerActionListener {
    void playerMoved(PlayerControllerActionEvent event);
    void playerDied(PlayerControllerActionEvent event);
}
