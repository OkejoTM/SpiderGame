import Interfaces.IStingable;
import Interfaces.IStinging;
import Setting.BotSpider;
import Setting.*;
import Utils.BotSpiderMovementAlgorithm;
import Utils.Direction;
import org.junit.Test;
import org.junit.Assert;


/**
 * Класс Игровых тестов
 */
public class GameTests {
    static class GrassHopperMocked extends GrassHopper {

        public GrassHopperMocked() {
            super(5, null, 5);
        }

        public void jump() {
            jumpToOtherWebCross();
        }

    }

    static class WaspMocked extends Wasp implements IStinging {

        public WaspMocked() {
            super(5, null, 5);
        }

        @Override
        public void sting(IStingable object) {
            int health = (int) Math.round(Math.random() * 10);
            object.getStung(health);
        }

    }

    @Test
    public void PlayerMovesInWebCrossNearBot() {
        Web web = new Web(3);
        BotSpiderMovementAlgorithm algorithm = new BotSpiderMovementAlgorithm(web);
        PlayerSpider playerSpider = new PlayerSpider(10, null);
        web.setPlayer(playerSpider, web.getWebCross(0, 2));

        BotSpider bot = new BotSpider(10, null, algorithm);
        web.addBotSpider(bot, web.getWebCross(0, 0));

        playerSpider.makeMove(Direction.west());
        bot.makeOptimalMove();

        Assert.assertNull(web.getPlayer().getWebCross()); // У паука нет перекрестия
        Assert.assertEquals(bot, web.getWebCross(0, 1).getAnimal()); // Перекрестие игрока занято ботом
        Assert.assertEquals(0, playerSpider.getHealth()); // игрок умер
    }

    @Test
    public void PlayerMovesIntoBot() {
        Web web = new Web(3);
        BotSpiderMovementAlgorithm algorithm = new BotSpiderMovementAlgorithm(web);
        PlayerSpider playerSpider = new PlayerSpider(10, null);
        web.setPlayer(playerSpider, web.getWebCross(0, 1));

        BotSpider bot = new BotSpider(10, null, algorithm);
        web.addBotSpider(bot, web.getWebCross(0, 0));

        playerSpider.makeMove(Direction.west());
        bot.makeOptimalMove();

        Assert.assertNull(web.getPlayer().getWebCross()); // У паука нет перекрестия
        Assert.assertEquals(bot, web.getWebCross(0, 1).getAnimal()); // Перекрестие игрока занято ботом
        Assert.assertEquals(0, playerSpider.getHealth()); // игрок умер
    }

    @Test
    public void PlayerMovesOutOfBot() {
        Web web = new Web(3);
        BotSpiderMovementAlgorithm algorithm = new BotSpiderMovementAlgorithm(web);
        PlayerSpider playerSpider = new PlayerSpider(10, null);
        web.setPlayer(playerSpider, web.getWebCross(0, 1));

        BotSpider bot = new BotSpider(10, null, algorithm);
        web.addBotSpider(bot, web.getWebCross(0, 0));

        playerSpider.makeMove(Direction.east());
        bot.makeOptimalMove();

        Assert.assertEquals(web.getWebCross(0, 2).getAnimal(), playerSpider); // Игрок пошел в противоположную сторону
        Assert.assertEquals(web.getWebCross(0, 1).getAnimal(), bot); // Бот пошел в сторону паука
        Assert.assertEquals(9, playerSpider.getHealth());
        Assert.assertEquals(9, bot.getHealth());
    }

    @Test
    public void PlayerMovesNorthInCorner() {
        Web web = new Web(3);

        PlayerSpider playerSpider = new PlayerSpider(10, null);
        web.setPlayer(playerSpider, web.getWebCross(0, 0));

        playerSpider.makeMove(Direction.north());

        Assert.assertEquals(web.getWebCross(0, 0).getAnimal(), playerSpider); // Игрок пошел в противоположную сторону
        Assert.assertEquals(9, playerSpider.getHealth());

    }

    @Test
    public void PlayerMovesWestInCorner() {
        Web web = new Web(3);

        PlayerSpider playerSpider = new PlayerSpider(10, null);
        web.setPlayer(playerSpider, web.getWebCross(0, 0));

        playerSpider.makeMove(Direction.west());

        Assert.assertEquals(web.getWebCross(0, 0).getAnimal(), playerSpider); // Игрок пошел в противоположную сторону
        Assert.assertEquals(9, playerSpider.getHealth());

    }

    @Test
    public void PlayerMovesSouthInCorner() {
        Web web = new Web(3);

        PlayerSpider playerSpider = new PlayerSpider(10, null);
        web.setPlayer(playerSpider, web.getWebCross(2, 2));

        playerSpider.makeMove(Direction.south());

        Assert.assertEquals(web.getWebCross(2, 2).getAnimal(), playerSpider); // Игрок пошел в противоположную сторону
        Assert.assertEquals(9, playerSpider.getHealth());

    }

    @Test
    public void PlayerMovesEastInCorner() {
        Web web = new Web(3);

        PlayerSpider playerSpider = new PlayerSpider(10, null);
        web.setPlayer(playerSpider, web.getWebCross(2, 2));

        playerSpider.makeMove(Direction.east());

        Assert.assertEquals(web.getWebCross(2, 2).getAnimal(), playerSpider); // Игрок пошел в противоположную сторону
        Assert.assertEquals(9, playerSpider.getHealth());

    }

    @Test
    public void PlayerDiesCauseOfOneHealth() {
        Web web = new Web(3);

        PlayerSpider playerSpider = new PlayerSpider(1, null);
        web.setPlayer(playerSpider, web.getWebCross(0, 0));

        playerSpider.makeMove(Direction.west());

        Assert.assertEquals(0, playerSpider.getHealth());
        Assert.assertNull(playerSpider.getWebCross());
    }

    @Test
    public void BotDiesCauseOfOneHealth() {
        Web web = new Web(3);

        BotSpiderMovementAlgorithm algorithm = new BotSpiderMovementAlgorithm(web);

        PlayerSpider playerSpider = new PlayerSpider(1, null);
        web.setPlayer(playerSpider, web.getWebCross(2, 2));

        BotSpider bot = new BotSpider(1, null, algorithm);
        web.addBotSpider(bot, web.getWebCross(0, 0));

        bot.makeOptimalMove();

        Assert.assertEquals(0, bot.getHealth());
        Assert.assertNull(bot.getWebCross());
    }

    @Test
    public void BotMovesNoEntitiesOnField() {
        Web web = new Web(3);

        BotSpiderMovementAlgorithm algorithm = new BotSpiderMovementAlgorithm(web);

        BotSpider bot = new BotSpider(1, null, algorithm);
        web.addBotSpider(bot, web.getWebCross(0, 0));

        bot.makeOptimalMove();

        Assert.assertEquals(1, bot.getHealth());
        Assert.assertNotNull(bot.getWebCross());
    }

    @Test
    public void BotMovesToPlayerOneHealth() {
        Web web = new Web(3);

        BotSpiderMovementAlgorithm algorithm = new BotSpiderMovementAlgorithm(web);

        PlayerSpider playerSpider = new PlayerSpider(10, null);
        web.setPlayer(playerSpider, web.getWebCross(0, 1));

        BotSpider bot = new BotSpider(1, null, algorithm);
        web.addBotSpider(bot, web.getWebCross(0, 0));

        bot.makeOptimalMove();

        Assert.assertEquals(0, bot.getHealth());
        Assert.assertNull(bot.getWebCross());
    }

    @Test
    public void BotMovesToInsectEast() {
        Web web = new Web(3);

        BotSpiderMovementAlgorithm algorithm = new BotSpiderMovementAlgorithm(web);

        Insect insect = new Mole(10, null, 5);
        web.addInsect(insect, web.getWebCross(0, 2));

        BotSpider bot = new BotSpider(10, null, algorithm);
        web.addBotSpider(bot, web.getWebCross(0, 0));

        bot.makeOptimalMove();

        Assert.assertEquals(9, bot.getHealth());
        Assert.assertEquals(web.getWebCross(0, 1).getAnimal(), bot);
    }

    @Test
    public void BotMovesToInsectSouth() {
        Web web = new Web(3);

        BotSpiderMovementAlgorithm algorithm = new BotSpiderMovementAlgorithm(web);

        Insect insect = new GrassHopper(10, null, 5);
        web.addInsect(insect, web.getWebCross(2, 0));

        BotSpider bot = new BotSpider(10, null, algorithm);
        web.addBotSpider(bot, web.getWebCross(0, 0));

        bot.makeOptimalMove();

        Assert.assertEquals(9, bot.getHealth());
        Assert.assertEquals(web.getWebCross(1, 0).getAnimal(), bot);
    }

    @Test
    public void BotMovesToInsectWest() {
        Web web = new Web(3);

        BotSpiderMovementAlgorithm algorithm = new BotSpiderMovementAlgorithm(web);

        Insect insect = new Fly(10, null, 5);
        web.addInsect(insect, web.getWebCross(2, 0));

        BotSpider bot = new BotSpider(10, null, algorithm);
        web.addBotSpider(bot, web.getWebCross(2, 2));

        bot.makeOptimalMove();

        Assert.assertEquals(9, bot.getHealth());
        Assert.assertEquals(web.getWebCross(2, 1).getAnimal(), bot);
    }

    @Test
    public void BotMovesToInsectNorth() {
        Web web = new Web(3);

        BotSpiderMovementAlgorithm algorithm = new BotSpiderMovementAlgorithm(web);

        Insect insect = new Mole(10, null, 5);
        web.addInsect(insect, web.getWebCross(0, 2));

        BotSpider bot = new BotSpider(10, null, algorithm);
        web.addBotSpider(bot, web.getWebCross(2, 2));

        bot.makeOptimalMove();

        Assert.assertEquals(9, bot.getHealth());
        Assert.assertEquals(web.getWebCross(1, 2).getAnimal(), bot);
    }

    @Test
    public void SpiderGetStingByWaspAlive() {

    }

    @Test
    public void SpiderGetStingByWaspDead() {
        Web web = new Web(3);
        PlayerSpider spider = new PlayerSpider(5, null);
        web.setPlayer(spider, web.getWebCross(1, 1));

        Insect wasp = new Wasp(3, null, 5);
        web.addInsect(wasp, web.getWebCross(1, 0));

        spider.makeMove(Direction.west());
    }

    @Test
    public void GrassHopperJumpsOnNearestEmptyWebCross() {
        Web web = new Web(3);

        GrassHopperMocked grassHopper = new GrassHopperMocked();
        web.addInsect(grassHopper, web.getWebCross(0, 0));

        grassHopper.jump();

        Assert.assertEquals(web.getWebCross(0, 1), grassHopper.getWebCross());
    }

    @Test
    public void GrassHopperJumpsOnNearestEmptyWebCrossButItsOccupied() {
        Web web = new Web(3);

        GrassHopperMocked grassHopper = new GrassHopperMocked();
        web.addInsect(grassHopper, web.getWebCross(0, 0));

        Wasp wasp = new Wasp(5, null, 5);
        web.addInsect(wasp, web.getWebCross(0, 1));

        grassHopper.jump();

        Assert.assertEquals(web.getWebCross(1, 0), grassHopper.getWebCross());
    }

    @Test
    public void GrassHopperJumpsOnNearestEmptyWebCrossButAllNearestOccupied() {
        Web web = new Web(2);

        GrassHopperMocked grassHopper = new GrassHopperMocked();
        web.addInsect(grassHopper, web.getWebCross(0, 0));

        Wasp wasp = new Wasp(5, null, 5);
        web.addInsect(wasp, web.getWebCross(0, 1));
        Wasp wasp2 = new Wasp(5, null, 5);
        web.addInsect(wasp2, web.getWebCross(1, 0));

        grassHopper.jump();

        Assert.assertEquals(web.getWebCross(1, 1).getPosition().row(), grassHopper.getWebCross().getPosition().row());
        Assert.assertEquals(web.getWebCross(1, 1).getPosition().column(), grassHopper.getWebCross().getPosition().column());
    }


    @Test
    public void GrassHopperJumpsOnNearestEmptyWebCrossButAllWebCrossesOccupied() {
        Web web = new Web(2);

        GrassHopperMocked grassHopper = new GrassHopperMocked();
        web.addInsect(grassHopper, web.getWebCross(0, 0));

        Wasp wasp = new Wasp(5, null, 5);
        web.addInsect(wasp, web.getWebCross(0, 1));
        Wasp wasp2 = new Wasp(5, null, 5);
        web.addInsect(wasp2, web.getWebCross(1, 0));
        Wasp wasp3 = new Wasp(5, null, 5);
        web.addInsect(wasp3, web.getWebCross(1, 1));

        grassHopper.jump();
        Assert.assertEquals(web.getWebCross(0, 0), grassHopper.getWebCross());
    }

    @Test
    public void WaspStingSpider() {
        Web web = new Web(2);

        WaspMocked wasp = new WaspMocked();
        web.addInsect(wasp, web.getWebCross(0,0));
        BotSpider spider = new BotSpider(11, null, null);
        web.addBotSpider(spider, web.getWebCross(0,1));

        wasp.sting(spider);

        Assert.assertTrue(spider.getHealth() < 10);
        Assert.assertTrue(spider.isAlive());

    }

    @Test
    public void WaspStingSpiderDies() {
        Web web = new Web(2);

        WaspMocked wasp = new WaspMocked();
        web.addInsect(wasp, web.getWebCross(0,0));
        BotSpider spider = new BotSpider(1, null, null);
        web.addBotSpider(spider, web.getWebCross(0,1));

        wasp.sting(spider);

        Assert.assertFalse(spider.isAlive());
    }

}
