//import Entities.BotSpider;
//import Setting.*;
//import Utils.Algorithm;
//import Utils.Direction;
//import org.junit.Test;
//import org.junit.Assert;
//
//import java.awt.*;
//
//public class GameTests {
//    @Test
//    public void BotEatsPlayerTest(){
//        Web web = new Web(3);
//        Flora flora = new Flora();
//        flora.setWeb(web);
//        flora.createPlayerSpider(2);
//        WebCross webCross = web.getWebCross(new Point(0,1));
//        BotSpider botSpider = new BotSpider(2, webCross, new Algorithm(web));
//        webCross.setAnimal(botSpider);
//        botSpider.makeOptimalMove();
//        Assert.assertNotNull(botSpider);
//        Assert.assertEquals(0, web.getPlayer().getHealth());
//    }
//
//    @Test
//    public void BotEatsBotTest(){
//        Web web = new Web(3);
//        Flora flora = new Flora();
//        flora.setWeb(web);
//        flora.createBotSpiders(2);
//        WebCross webCross = web.getWebCross(new Point(0,1));
//        WebCross webCross2 = web.getWebCross(new Point(0,0));
//        BotSpider botSpider = new BotSpider(2, webCross, new Algorithm(web));
//        BotSpider botSpider2 = new BotSpider(2, webCross, new Algorithm(web));
//        webCross.setAnimal(botSpider);
//        webCross2.setAnimal(botSpider2);
//
//        botSpider.makeOptimalMove();
//        Assert.assertNotNull(botSpider);
//        Assert.assertNotNull(botSpider2);
//    }
//
//    @Test
//    public void PlayerEatsBotTest(){
//        Web web = new Web(3);
//        Flora flora = new Flora();
//        flora.setWeb(web);
//        flora.createPlayerSpider(2);
//        WebCross webCross = web.getWebCross(new Point(0,1));
//        BotSpider botSpider = new BotSpider(2, webCross, new Algorithm(web));
//        webCross.setAnimal(botSpider);
//
//        web.getPlayer().makeMove(Direction.north());
//        Assert.assertNotNull(botSpider);
//        Assert.assertEquals(2, botSpider.getHealth());
//    }
//
//    @Test
//    public void PlayerOneHealthMovesTest(){
//        Web web = new Web(3);
//        Flora flora = new Flora();
//        flora.setWeb(web);
//        flora.createPlayerSpider(1);
//        web.getPlayer().makeMove(Direction.north());
//        Assert.assertEquals(0, web.getPlayer().getHealth());
//    }
//
//    @Test
//    public void MoveToEdgeNorthTest(){
//        Web web = new Web(3);
//        Flora flora = new Flora();
//        flora.setWeb(web);
//        flora.createPlayerSpider(4);
//        web.getPlayer().makeMove(Direction.north());
//        web.getPlayer().makeMove(Direction.north());
//        Assert.assertEquals(web.getPlayer().getWebCross().getPosition(), new Point(0,1));
//    }
//
//    @Test
//    public void MoveToEdgeSouthTest(){
//        Web web = new Web(3);
//        Flora flora = new Flora();
//        flora.setWeb(web);
//        flora.createPlayerSpider(4);
//        web.getPlayer().makeMove(Direction.south());
//        Assert.assertEquals(web.getPlayer().getWebCross().getPosition(), new Point(1,1));
//    }
//
//    @Test
//    public void MoveToEdgeWestTest(){
//        Web web = new Web(3);
//        Flora flora = new Flora();
//        flora.setWeb(web);
//        flora.createPlayerSpider(4);
//        web.getPlayer().makeMove(Direction.west());
//        web.getPlayer().makeMove(Direction.west());
//        Assert.assertEquals(web.getPlayer().getWebCross().getPosition(), new Point(1,0));
//    }
//
//    @Test
//    public void MoveToEdgeEastTest(){
//        Web web = new Web(3);
//        Flora flora = new Flora();
//        flora.setWeb(web);
//        flora.createPlayerSpider(4);
//        web.getPlayer().makeMove(Direction.east());
//        Assert.assertEquals(web.getPlayer().getWebCross().getPosition(), new Point(1,1));
//    }
//
//
//
//}
