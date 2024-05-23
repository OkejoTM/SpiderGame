import Setting.*;
import Utils.BotSpiderMovementAlgorithm;
import org.junit.Test;
import org.junit.Assert;

public class WebTests {

    @Test
    public void WebGetAllEmptyWebCrossesTest(){
        Web web = new Web(3);

        Assert.assertEquals(9, web.getEmptyWebCrosses().size());
    }

    @Test
    public void WebGetEmptyWebCrossesWithEntitiesInWebTest(){
        Web web = new Web(3);

        BotSpiderMovementAlgorithm algorithm = new BotSpiderMovementAlgorithm(web);

        Insect insect = new Mole(10, null, 5);
        web.addInsect(insect, web.getWebCross(0,2));

        BotSpider bot = new BotSpider(10, null, algorithm);
        web.addBotSpider(bot, web.getWebCross(2,2));

        Assert.assertEquals(7, web.getEmptyWebCrosses().size());
    }


    @Test
    public void WebGetNoEmptyWebCrossesTest(){
        Web web = new Web(2);

        Insect insect = new Mole(10, null, 5);
        web.addInsect(insect, web.getWebCross(0,0));

        Insect insect2 = new Mole(10, null, 5);
        web.addInsect(insect2, web.getWebCross(0,1));

        Insect insect3 = new Mole(10, null, 5);
        web.addInsect(insect3, web.getWebCross(1,0));

        Insect insect4 = new Mole(10, null, 5);
        web.addInsect(insect4, web.getWebCross(1,1));

        Assert.assertEquals(0, web.getEmptyWebCrosses().size());
    }

    @Test(expected = NullPointerException.class)
    public void PlaceInNonExistedWebCross(){
        Web web = new Web(2);

        Insect insect = new Mole(10, null, 5);

        web.addInsect(insect, web.getWebCross(0,4));
    }

    @Test
    public void WebGetPreysTest(){
        Web web = new Web(2);

        Insect insect = new Mole(10, null, 5);
        web.addInsect(insect, web.getWebCross(0,0));

        Insect insect2 = new Mole(10, null, 5);
        web.addInsect(insect2, web.getWebCross(0,1));

        Insect insect3 = new Mole(10, null, 5);
        web.addInsect(insect3, web.getWebCross(1,0));

        Insect insect4 = new Mole(10, null, 5);
        web.addInsect(insect4, web.getWebCross(1,1));

        Assert.assertEquals(4, web.getAllPreys().size());
    }

    @Test
    public void WebGetPreysWithPlayerTest(){
        Web web = new Web(2);

        PlayerSpider playerSpider = new PlayerSpider(1, null);
        web.setPlayer(playerSpider, web.getWebCross(0,0));

        Insect insect2 = new Mole(10, null, 5);
        web.addInsect(insect2, web.getWebCross(0,1));

        Insect insect3 = new Mole(10, null, 5);
        web.addInsect(insect3, web.getWebCross(1,0));

        Insect insect4 = new Mole(10, null, 5);
        web.addInsect(insect4, web.getWebCross(1,1));

        Assert.assertEquals(4, web.getAllPreys().size());
    }


}
