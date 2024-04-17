import Entities.Insect;
import Entities.Wasp;
import Setting.Flora;
import Setting.Game;
import Setting.Web;
import Setting.WebCross;
import Utils.Direction;

import java.awt.*;

public class Main {
    public static void main(String[] args){
//        Web web = new Web(4);
//        Flora flora = new Flora();
//        flora.setWeb(web);
//        flora.createPlayerSpider(2);
//        web.getPlayer().makeMove(Direction.north());
//

        Flora flora = new Flora();
        Game game = new Game(flora, 4);
        game.startGame();
        game.getWeb().getPlayer().makeMove(Direction.north());
    }
}
