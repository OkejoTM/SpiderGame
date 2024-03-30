import Setting.Flora;

public class Main {
    public static void main(String[] args){
        Flora flora = new Flora();
        Game game = new Game(flora);
        game.startGame();
    }
}
