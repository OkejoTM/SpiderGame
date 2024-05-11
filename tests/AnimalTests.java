//import Setting.BotSpider;
//import Setting.Insect;
//import Setting.Mole;
//import Factories.AbstractInsectFactory;
//import Factories.MoleFactory;
//import Setting.*;
//import org.junit.Test;
//import org.junit.Assert;
//
//import java.awt.*;
//import java.util.ArrayList;
//
//public class AnimalTests {
//    @Test
//    public void PlayerSpiderDiesTest(){
//        Web web = new Web(3);
//        Flora flora = new Flora(web);
//        flora.instantiateAnimals(Game.GameLevel.EASY);
//
//        Assert.assertEquals(0, web.getPlayer().getHealth());
//        Assert.assertNull(web.getPlayer().getWebCross());
//        Assert.assertEquals(4, web.getEmptyWebCrosses().size());
//    }
//
//    @Test
//    public void BotSpiderDiesTest(){
//        Web web = new Web(3);
//        WebCross webCross = web.getWebCross(new Point(1,1));
//        BotSpider bot = new BotSpider(1, webCross, null);
//        webCross.setAnimal(bot);
//        bot.die();
//        Assert.assertEquals(0, bot.getHealth());
//        Assert.assertNull(bot.getWebCross());
//    }
//
//    @Test
//    public void InsectDiesTest(){
//        Web web = new Web(3);
//        WebCross webCross = web.getWebCross(new Point(1,1));
//        Insect insect = new Mole(1, webCross);
//        webCross.setAnimal(insect);
//        insect.die();
//        Assert.assertEquals(0, insect.getHealth());
//        Assert.assertNull(insect.getWebCross());
//    }
//
////    @Test
////    public void InsectJumpsOffTest(){
////        Web web = new Web(3);
////        Insect insect = new Mole(1, null);
////        web.getInsects().add(insect);
////        WebCross webCross = web.getWebCross(new Point(0,0));
////        insect.setWebCross(webCross);
////        webCross.setAnimal(insect);
////        web.getInsects().get(0).jumpOff();
////        Assert.assertNull(insect.getWebCross());
////        Assert.assertNull(webCross.getAnimal());
////    }
//
//}
