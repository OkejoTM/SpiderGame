import Setting.*;
import Entities.*;
import Utils.Direction;
import org.junit.Test;
import org.junit.Assert;

import java.awt.*;

public class WebCrossTests {

    @Test
    public void ReleasePlayerTest(){
        Web web = new Web(3);
        Flora flora = new Flora();
        flora.setWeb(web);
        flora.createPlayerSpider();
        WebCross webCross = web.getWebCross(new Point(1, 1));
        webCross.releaseAnimal();
        Assert.assertNull(webCross.getAnimal());
        Assert.assertNull(web.getPlayer().getWebCross());
    }

    @Test
    public void ReleaseBotTest(){
        Web web = new Web(3);
        Flora flora = new Flora();
        flora.setWeb(web);
        flora.createBotSpiders(1);
        WebCross webCross = web.getBotSpiders().get(0).getWebCross();
        webCross.releaseAnimal();
        Assert.assertNull(webCross.getAnimal());
        Assert.assertNull(web.getBotSpiders().get(0).getWebCross());
    }

    @Test
    public void ReleaseInsectTest(){
        Web web = new Web(3);
        Insect insect = new Mole(1, null);
        web.getInsects().add(insect);
        WebCross webCross = web.getWebCross(new Point(0,0));
        insect.setWebCross(webCross);
        webCross.setAnimal(insect);
        web.getInsects().get(0).getWebCross().releaseAnimal();
        Assert.assertNull(webCross.getAnimal());
        Assert.assertNull(web.getInsects().get(0).getWebCross());
    }

    @Test
    public void SetAnimalOccupiedWebCross(){
        Web web = new Web(3);
        Flora flora = new Flora();
        flora.setWeb(web);
        flora.createPlayerSpider();
        WebCross webCross = web.getWebCross(new Point(1, 1));
        Insect insect = new Mole(1, null);
        webCross.setAnimal(insect);
        Assert.assertEquals(web.getPlayer(), webCross.getAnimal());
    }

    @Test
    public void NorthHasNextWebCrossTest(){
        Web web = new Web(3);
        WebCross webCross = web.getWebCross(new Point(1,1));
        Assert.assertTrue(webCross.hasNext(Direction.north()));
    }

    @Test
    public void NorthHasNotNextWebCrossTest(){
        Web web = new Web(3);
        WebCross webCross = web.getWebCross(new Point(0,0));
        Assert.assertFalse(webCross.hasNext(Direction.north()));
    }

    @Test
    public void SouthHasNextWebCrossTest(){
        Web web = new Web(3);
        WebCross webCross = web.getWebCross(new Point(0,0));
        Assert.assertTrue(webCross.hasNext(Direction.south()));
    }

    @Test
    public void SouthHasNotNextWebCrossTest(){
        Web web = new Web(3);
        WebCross webCross = web.getWebCross(new Point(1, 0));
        Assert.assertFalse(webCross.hasNext(Direction.south()));
    }

    @Test
    public void EastHasNextWebCrossTest(){
        Web web = new Web(3);
        WebCross webCross = web.getWebCross(new Point(1,0));
        Assert.assertTrue(webCross.hasNext(Direction.east()));
    }

    @Test
    public void EastHasNotNextWebCrossTest(){
        Web web = new Web(3);
        WebCross webCross = web.getWebCross(new Point(0,1));
        Assert.assertFalse(webCross.hasNext(Direction.east()));
    }

    @Test
    public void WestHasNextWebCrossTest(){
        Web web = new Web(3);
        WebCross webCross = web.getWebCross(new Point(0, 1));
        Assert.assertTrue(webCross.hasNext(Direction.west()));
    }

    @Test
    public void WestHasNotNextWebCrossTest(){
        Web web = new Web(3);
        WebCross webCross = web.getWebCross(new Point(0,0));
        Assert.assertFalse(webCross.hasNext(Direction.west()));
    }

    @Test
    public void NorthGetNextWebCrossTest(){
        Web web = new Web(3);
        WebCross webCross = web.getWebCross(new Point(1,1));
        Assert.assertEquals(web.getWebCross(new Point(0, 1)), webCross.getNextWebCross(Direction.north()));
    }

    @Test
    public void SouthGetNextWebCrossTest(){
        Web web = new Web(3);
        WebCross webCross = web.getWebCross(new Point(0,0));
        Assert.assertEquals(web.getWebCross(new Point(1, 0)), webCross.getNextWebCross(Direction.south()));
    }

    @Test
    public void EastGetNextWebCrossTest(){
        Web web = new Web(3);
        WebCross webCross = web.getWebCross(new Point(0,0));
        Assert.assertEquals(web.getWebCross(new Point(0, 1)), webCross.getNextWebCross(Direction.east()));
    }

    @Test
    public void WestGetNextWebCrossTest(){
        Web web = new Web(3);
        WebCross webCross = web.getWebCross(new Point(1,1));
        Assert.assertEquals(web.getWebCross(new Point(1, 0)), webCross.getNextWebCross(Direction.west()));
    }

}
