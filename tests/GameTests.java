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
        Flora flora = new Flora();
        flora.setWeb(web);
        flora.createPlayerSpider();
        flora.createBotSpiders(1);
        web.getBotSpiders().get(0).getWebCross().releaseAnimal();
        web.getBotSpiders().get(0).setWebCross(web.getWebCross(new Point(0,1)));
        web.getWebCross(new Point(0,1)).setAnimal(web.getBotSpiders().get(0));
        web.getBotSpiders().get(0).makeOptimalMove();
        Assert.assertEquals(1, web.getBotSpiders().size());
        Assert.assertTrue(web.isPlayerInWeb());
    }

    @Test
    public void BotEatsBotTest(){
        Web web = new Web(3);
        Flora flora = new Flora();
        flora.setWeb(web);
        flora.createBotSpiders(2);
        web.getBotSpiders().get(0).getWebCross().releaseAnimal();
        web.getBotSpiders().get(1).getWebCross().releaseAnimal();
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
        Flora flora = new Flora();
        flora.setWeb(web);
        flora.createPlayerSpider();
        flora.createBotSpiders(1);
        web.getBotSpiders().get(0).setWebCross(web.getWebCross(new Point(0,1)));
        web.getWebCross(new Point(0,1)).setAnimal(web.getBotSpiders().get(0));
        web.getPlayer().makeMove(Direction.north());
        Assert.assertEquals(1, web.getBotSpiders().size());
        Assert.assertTrue(web.isPlayerInWeb());
    }

    @Test
    public void PlayerOneHealthMovesTest(){
        Web web = new Web(3);
        Flora flora = new Flora();
        flora.setWeb(web);
        flora.createPlayerSpider();
        web.getPlayer().setHealth(1);
        web.getPlayer().makeMove(Direction.north());
        Assert.assertEquals(0, web.getPlayer().getHealth());
    }

    @Test
    public void MoveToEdgeNorthTest(){
        Web web = new Web(3);
        Flora flora = new Flora();
        flora.setWeb(web);
        flora.createPlayerSpider();
        web.getPlayer().setHealth(10);
        web.getPlayer().makeMove(Direction.north());
        web.getPlayer().makeMove(Direction.north());
        Assert.assertTrue(web.isPlayerInWeb());
        Assert.assertEquals(web.getPlayer().getWebCross().getPosition(), new Point(0,1));
    }

    @Test
    public void MoveToEdgeSouthTest(){
        Web web = new Web(3);
        Flora flora = new Flora();
        flora.setWeb(web);
        flora.createPlayerSpider();
        web.getPlayer().setHealth(10);
        web.getPlayer().makeMove(Direction.south());
        Assert.assertTrue(web.isPlayerInWeb());
        Assert.assertEquals(web.getPlayer().getWebCross().getPosition(), new Point(1,1));
    }

    @Test
    public void MoveToEdgeWestTest(){
        Web web = new Web(3);
        Flora flora = new Flora();
        flora.setWeb(web);
        flora.createPlayerSpider();
        web.getPlayer().setHealth(10);
        web.getPlayer().makeMove(Direction.west());
        web.getPlayer().makeMove(Direction.west());
        Assert.assertTrue(web.isPlayerInWeb());
        Assert.assertEquals(web.getPlayer().getWebCross().getPosition(), new Point(1,0));
    }

    @Test
    public void MoveToEdgeEastTest(){
        Web web = new Web(3);
        Flora flora = new Flora();
        flora.setWeb(web);
        flora.createPlayerSpider();
        web.getPlayer().setHealth(10);
        web.getPlayer().makeMove(Direction.east());
        Assert.assertTrue(web.isPlayerInWeb());
        Assert.assertEquals(web.getPlayer().getWebCross().getPosition(), new Point(1,1));
    }



}
