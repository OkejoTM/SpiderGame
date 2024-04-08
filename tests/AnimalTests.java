import Entities.Insect;
import Entities.Mole;
import Factories.AbstractInsectFactory;
import Factories.MoleFactory;
import Setting.*;
import org.junit.Test;
import org.junit.Assert;

import java.awt.*;
import java.util.ArrayList;

public class AnimalTests {
    @Test
    public void PlayerSpiderDiesTest(){
        Web web = new Web(3);
        Flora flora = new Flora();
        flora.setWeb(web);
        flora.createPlayerSpider();
        web.getPlayer().die();
        Assert.assertEquals(0, web.getPlayer().getHealth());
        Assert.assertNull(web.getPlayer().getWebCross());
        Assert.assertEquals(4, web.getEmptyWebCrosses().size());
    }

    @Test
    public void BotSpiderDiesTest(){
        Web web = new Web(3);
        Flora flora = new Flora();
        flora.setWeb(web);
        flora.createBotSpiders(1);
        web.getBotSpiders().get(0).die();
        Assert.assertEquals(0, web.getBotSpiders().get(0).getHealth());
    }

    @Test
    public void InsectDiesTest(){
        Web web = new Web(3);
        Insect insect = new Mole(1, null);
        web.getInsects().add(insect);
        WebCross webCross = web.getWebCross(new Point(0,0));
        insect.setWebCross(webCross);
        webCross.setAnimal(insect);
        web.getInsects().get(0).die();
        Assert.assertEquals(0, web.getInsects().get(0).getHealth());
        Assert.assertNull(insect.getWebCross());
        Assert.assertNull(webCross.getAnimal());
    }

//    @Test
//    public void InsectJumpsOffTest(){
//        Web web = new Web(3);
//        Insect insect = new Mole(1, null);
//        web.getInsects().add(insect);
//        WebCross webCross = web.getWebCross(new Point(0,0));
//        insect.setWebCross(webCross);
//        webCross.setAnimal(insect);
//        web.getInsects().get(0).jumpOff();
//        Assert.assertEquals(0, web.getInsects().get(0).getHealth());
//        Assert.assertNull(insect.getWebCross());
//        Assert.assertNull(webCross.getAnimal());
//    }

}
