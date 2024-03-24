import Setting.*;
import Entities.*;
import Utils.Direction;
import org.junit.Test;
import org.junit.Assert;

import java.awt.*;

public class GameTests {
    @Test
    public void BotEatsPlayerTest(){
        Web web = new Web(3);
        Flora flora = new Flora(web);
        flora.createPlayerSpider();
        flora.createBotSpiders(1);
        web.getBotSpiders().get(0).setWebCross(web.getWebCross(new Point(0,1)));
        web.getWebCross(new Point(0,1)).setAnimal(web.getBotSpiders().get(0));
        web.getBotSpiders().get(0).makeOptimalMove();
        Assert.assertEquals(1, web.getBotSpiders().size());
        Assert.assertTrue(web.isPlayerInWeb());
    }

    @Test
    public void BotEatsBotTest(){
        Web web = new Web(3);
        Flora flora = new Flora(web);
        flora.createBotSpiders(2);
        web.getBotSpiders().get(0).setWebCross(web.getWebCross(new Point(0,1)));
        web.getBotSpiders().get(1).setWebCross(web.getWebCross(new Point(0,0)));
        web.getWebCross(new Point(0,1)).setAnimal(web.getBotSpiders().get(0));
        web.getWebCross(new Point(0,0)).setAnimal(web.getBotSpiders().get(1));
        web.getBotSpiders().get(0).makeOptimalMove();
        Assert.assertEquals(2, web.getBotSpiders().size());
    }

    @Test
    public void PlayerEatsBotTest(){
        Web web = new Web(3);
        Flora flora = new Flora(web);
        flora.createPlayerSpider();
        flora.createBotSpiders(1);
        web.getBotSpiders().get(0).setWebCross(web.getWebCross(new Point(0,1)));
        web.getWebCross(new Point(0,1)).setAnimal(web.getBotSpiders().get(0));
        web.getPlayer().makeMove(Direction.north());
        Assert.assertEquals(1, web.getBotSpiders().size());
        Assert.assertTrue(web.isPlayerInWeb());
    }
}
