import Setting.*;
import Entities.*;
import Utils.Direction;
import org.junit.Test;
import org.junit.Assert;

import java.awt.*;
public class FloraTests {
    @Test
    public void FloraCreatesPlayerTest(){
        Web web = new Web(3);
        Flora flora = new Flora();
        flora.setWeb(web);
        flora.createPlayerSpider(2);
        Assert.assertNotNull(web.getPlayer());
        Assert.assertEquals(web.getPlayer().getClass(), PlayerSpider.class);
    }

    @Test
    public void FloraCreateBotTest(){
        Web web = new Web(3);
        Flora flora = new Flora();
        flora.setWeb(web);
        flora.createBotSpiders(1);
        Assert.assertEquals(1, web.getBotSpiders().size());
        Assert.assertEquals(web.getBotSpiders().get(0).getClass(), BotSpider.class);
    }

    @Test
    public void NoEmptySpacesForBotCreationTest(){
        Web web = new Web(3);
        Flora flora = new Flora();
        flora.setWeb(web);
        flora.createBotSpiders(4);
        flora.createBotSpiders(4);
        Assert.assertEquals(4, web.getBotSpiders().size());
    }

    @Test
    public void CreateMoreBotsThanWebCrossesTest(){
        Web web = new Web(3);
        Flora flora = new Flora();
        flora.setWeb(web);
        flora.createBotSpiders(5);
        Assert.assertEquals(0, web.getBotSpiders().size());
    }



}
