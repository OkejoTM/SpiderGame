import Setting.Flora;
import Utils.Direction;

public class Main {
    public static void main(String[] args){
        Flora flora = new Flora();
        Game game = new Game(flora);
        game.startGame();
        game.getWeb().getPlayer().makeMove(Direction.east());

    }
}
