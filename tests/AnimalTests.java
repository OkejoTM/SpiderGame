import Setting.*;
import org.junit.Test;
import org.junit.Assert;

import java.awt.*;

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
        Flora flora = new Flora();
        flora.setWeb(web);
        flora.createInsects(1);
        web.getInsects().get(0).die();
        Assert.assertEquals(0, web.getInsects().get(0).getHealth());
    }

//    @Test
//    public void InsectJumpsOffTest(){
//        Web web = new Web(3);
//        Flora flora = new Flora();
//        flora.setWeb(web);
//        flora.createInsects(1);
//        web.getInsects().get(0).jumpOff();
//        Assert.assertEquals(0, web.getInsects().get(0).getHealth());
//    }

}
