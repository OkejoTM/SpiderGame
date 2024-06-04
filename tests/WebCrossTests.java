import Setting.*;
import Utils.Direction;
import org.junit.Test;
import org.junit.Assert;

import java.awt.*;

/**
 * Класс тестов Перекрестия
 */
public class WebCrossTests {

    @Test
    public void WebCrossGetNorthNeighbourTest() {
        Web web = new Web(3);
        WebCross webCross = web.getWebCross(1, 0);

        WebCross nextWebCross = webCross.neighbour(Direction.north());

        Assert.assertEquals(web.getWebCross(0, 0), nextWebCross);
    }

    @Test
    public void WebCrossGetSouthNeighbourTest() {
        Web web = new Web(3);
        WebCross webCross = web.getWebCross(0, 0);

        WebCross nextWebCross = webCross.neighbour(Direction.south());

        Assert.assertEquals(web.getWebCross(1, 0), nextWebCross);
    }

    @Test
    public void WebCrossGetEastNeighbourTest() {
        Web web = new Web(3);
        WebCross webCross = web.getWebCross(0, 0);

        WebCross nextWebCross = webCross.neighbour(Direction.east());

        Assert.assertEquals(web.getWebCross(0, 1), nextWebCross);
    }

    @Test
    public void WebCrossGetWestNeighbourTest() {
        Web web = new Web(3);
        WebCross webCross = web.getWebCross(0, 1);

        WebCross nextWebCross = webCross.neighbour(Direction.west());

        Assert.assertEquals(web.getWebCross(0, 0), nextWebCross);
    }

    @Test
    public void NoNeighbourNorthTest() {
        Web web = new Web(3);
        WebCross webCross = web.getWebCross(0, 0);

        WebCross nextWebCross = webCross.neighbour(Direction.north());

        Assert.assertNull(nextWebCross);
    }

    @Test
    public void NoNeighbourWestTest() {
        Web web = new Web(3);
        WebCross webCross = web.getWebCross(0, 0);

        WebCross nextWebCross = webCross.neighbour(Direction.west());

        Assert.assertNull(nextWebCross);
    }

    @Test
    public void NoNeighbourSouthTest() {
        Web web = new Web(3);
        WebCross webCross = web.getWebCross(2, 2);

        WebCross nextWebCross = webCross.neighbour(Direction.south());

        Assert.assertNull(nextWebCross);
    }

    @Test
    public void NoNeighbourEastTest() {
        Web web = new Web(3);
        WebCross webCross = web.getWebCross(2, 2);

        WebCross nextWebCross = webCross.neighbour(Direction.east());

        Assert.assertNull(nextWebCross);
    }

    @Test
    public void WebCrossSetsAnimalTest() {
        Web web = new Web(3);
        WebCross webCross = web.getWebCross(0, 0);

        PlayerSpider playerSpider = new PlayerSpider(10, null);

        webCross.setAnimal(playerSpider);

        Assert.assertEquals(webCross, playerSpider.getWebCross());
        Assert.assertEquals(webCross.getAnimal(), playerSpider);
    }

    @Test
    public void WebCrossSetsNullWithAnimalTest() {
        Web web = new Web(3);
        WebCross webCross = web.getWebCross(0, 0);

        PlayerSpider playerSpider = new PlayerSpider(10, null);

        webCross.setAnimal(playerSpider);
        webCross.setAnimal(null);

        Assert.assertNull(playerSpider.getWebCross());
        Assert.assertNull(webCross.getAnimal());
    }

    @Test
    public void WebCrossSetAnimalInOccupiedWebCrossTest() {
        Web web = new Web(3);
        WebCross webCross = web.getWebCross(0, 0);

        PlayerSpider playerSpider = new PlayerSpider(10, null);

        webCross.setAnimal(playerSpider);

        BotSpider botSpider = new BotSpider(10, null, null);

        webCross.setAnimal(botSpider);

        Assert.assertEquals(webCross, playerSpider.getWebCross());
        Assert.assertEquals(webCross.getAnimal(), playerSpider);
    }

}
