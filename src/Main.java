import Setting.Flora;
import Setting.Game;
import Utils.Direction;

public class Main {
    public static void main(String[] args){
//        Web web = new Web(4);
//        Flora flora = new Flora();
//        flora.setWeb(web);
//        flora.createPlayerSpider(2);
//        web.getPlayer().makeMove(Direction.north());
//

        Flora flora = new Flora();
        Game game = new Game(flora, 3);
        game.startGame();
        game.getWeb().getPlayer().makeMove(Direction.north());


    }
}
