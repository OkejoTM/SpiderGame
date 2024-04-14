import Setting.*;
import Entities.*;
import Utils.Direction;
import org.junit.Test;
import org.junit.Assert;

public class WebTests {

    @Test
    public void WebCreationTest(){
        Web web = new Web(3);
        Assert.assertEquals(4, web.getWebCrosses().size());
        for (var wb : web.getWebCrosses()){
            Assert.assertNull(wb.getAnimal());
        }
    }

    @Test
    public void WebGetPlayerTest(){
        Web web = new Web(3);
        Flora flora = new Flora();
        flora.setWeb(web);
        flora.createPlayerSpider(2);
        Assert.assertNotNull(web.getPlayer());
        Assert.assertEquals(web.getPlayer().getClass(), PlayerSpider.class);
    }

    @Test
    public void WebGetBotSpidersTest(){
        Web web = new Web(3);
        Flora flora = new Flora();
        flora.setWeb(web);
        flora.createBotSpiders(2);
        Assert.assertEquals(2, web.getBotSpiders().size());
    }

    @Test
    public void WebGetAllEmptyWebCrossesTest(){
        Web web = new Web(3);
        Assert.assertEquals(4, web.getEmptyWebCrosses().size());
    }

    @Test
    public void WebGetNoEmptyWebCrossesTest(){
        Web web = new Web(3);
        Flora flora = new Flora();
        flora.setWeb(web);
        flora.createBotSpiders(4);
        Assert.assertEquals(0, web.getEmptyWebCrosses().size());
    }

    @Test
    public void ClearWebTest(){
//        Web web = new Web(3);
//        Flora flora = new Flora();
//        flora.setWeb(web);
//        flora.createPlayerSpider();
//        flora.createBotSpiders(1);
//        Insect insect = new Mole(1, null);
//        insect.setWebCross(web.getEmptyWebCrosses().get(0));
//        insect.getWebCross().setAnimal(insect);
//        web.getInsects().add(insect);
//        web.clearWeb();
//
//        Assert.assertNull(web.getPlayer());
//        Assert.assertEquals(0, web.getWebCrosses().size());
//        Assert.assertEquals(0, web.getBotSpiders().size());
//        Assert.assertEquals(0, web.getInsects().size());
    }



}
