import Setting.BotSpider;
import Setting.*;
import Utils.BotSpiderMovementAlgorithm;
import Utils.Direction;
import org.junit.Test;
import org.junit.Assert;



public class GameTests {

    @Test
    public void PlayerMovesInWebCrossNearBot(){
        Web web = new Web(3);
        BotSpiderMovementAlgorithm algorithm = new BotSpiderMovementAlgorithm(web);
        PlayerSpider playerSpider = new PlayerSpider(10, null);
        web.setPlayer(playerSpider, web.getWebCross(0,2));

        BotSpider bot = new BotSpider(10, null, algorithm);
        web.addBotSpider(bot, web.getWebCross(0,0));

        playerSpider.makeMove(Direction.west());
        bot.makeOptimalMove();

        Assert.assertNull(web.getPlayer().getWebCross()); // У паука нет перекрестия
        Assert.assertEquals(bot, web.getWebCross(0, 1).getAnimal()); // Перекрестие игрока занято ботом
        Assert.assertEquals(0, playerSpider.getHealth()); // игрок умер
    }

    @Test
    public void PlayerMovesIntoBot(){
        Web web = new Web(3);
        BotSpiderMovementAlgorithm algorithm = new BotSpiderMovementAlgorithm(web);
        PlayerSpider playerSpider = new PlayerSpider(10, null);
        web.setPlayer(playerSpider, web.getWebCross(0,1));

        BotSpider bot = new BotSpider(10, null, algorithm);
        web.addBotSpider(bot, web.getWebCross(0,0));

        playerSpider.makeMove(Direction.west());
        bot.makeOptimalMove();

        Assert.assertNull(web.getPlayer().getWebCross()); // У паука нет перекрестия
        Assert.assertEquals(bot, web.getWebCross(0, 1).getAnimal()); // Перекрестие игрока занято ботом
        Assert.assertEquals(0, playerSpider.getHealth()); // игрок умер
    }

    @Test
    public void PlayerMovesOutOfBot(){
        Web web = new Web(3);
        BotSpiderMovementAlgorithm algorithm = new BotSpiderMovementAlgorithm(web);
        PlayerSpider playerSpider = new PlayerSpider(10, null);
        web.setPlayer(playerSpider, web.getWebCross(0,1));

        BotSpider bot = new BotSpider(10, null, algorithm);
        web.addBotSpider(bot, web.getWebCross(0,0));

        playerSpider.makeMove(Direction.east());
        bot.makeOptimalMove();

        Assert.assertEquals(web.getWebCross(0,2).getAnimal(), playerSpider); // Игрок пошел в противоположную сторону
        Assert.assertEquals(web.getWebCross(0,1).getAnimal(), bot); // Бот пошел в сторону паука
        Assert.assertEquals(9, playerSpider.getHealth());
        Assert.assertEquals(9, bot.getHealth());
    }

    @Test
    public void PlayerMovesNorthInCorner(){
        Web web = new Web(3);

        PlayerSpider playerSpider = new PlayerSpider(10, null);
        web.setPlayer(playerSpider, web.getWebCross(0,0));

        playerSpider.makeMove(Direction.north());

        Assert.assertEquals(web.getWebCross(0,0).getAnimal(), playerSpider); // Игрок пошел в противоположную сторону
        Assert.assertEquals(9, playerSpider.getHealth());

    }

    @Test
    public void PlayerMovesWestInCorner(){
        Web web = new Web(3);

        PlayerSpider playerSpider = new PlayerSpider(10, null);
        web.setPlayer(playerSpider, web.getWebCross(0,0));

        playerSpider.makeMove(Direction.west());

        Assert.assertEquals(web.getWebCross(0,0).getAnimal(), playerSpider); // Игрок пошел в противоположную сторону
        Assert.assertEquals(9, playerSpider.getHealth());

    }

    @Test
    public void PlayerMovesSouthInCorner(){
        Web web = new Web(3);

        PlayerSpider playerSpider = new PlayerSpider(10, null);
        web.setPlayer(playerSpider, web.getWebCross(2,2));

        playerSpider.makeMove(Direction.south());

        Assert.assertEquals(web.getWebCross(2,2).getAnimal(), playerSpider); // Игрок пошел в противоположную сторону
        Assert.assertEquals(9, playerSpider.getHealth());

    }

    @Test
    public void PlayerMovesEastInCorner(){
        Web web = new Web(3);

        PlayerSpider playerSpider = new PlayerSpider(10, null);
        web.setPlayer(playerSpider, web.getWebCross(2,2));

        playerSpider.makeMove(Direction.east());

        Assert.assertEquals(web.getWebCross(2,2).getAnimal(), playerSpider); // Игрок пошел в противоположную сторону
        Assert.assertEquals(9, playerSpider.getHealth());

    }

    @Test
    public void PlayerDiesCauseOfOneHealth(){
        Web web = new Web(3);

        PlayerSpider playerSpider = new PlayerSpider(1, null);
        web.setPlayer(playerSpider, web.getWebCross(0, 0));

        playerSpider.makeMove(Direction.west());

        Assert.assertEquals(0, playerSpider.getHealth());
        Assert.assertNull(playerSpider.getWebCross());
    }

    @Test
    public void BotDiesCauseOfOneHealth(){
        Web web = new Web(3);

        BotSpiderMovementAlgorithm algorithm = new BotSpiderMovementAlgorithm(web);

        PlayerSpider playerSpider = new PlayerSpider(1, null);
        web.setPlayer(playerSpider, web.getWebCross(2, 2));

        BotSpider bot = new BotSpider(1, null, algorithm);
        web.addBotSpider(bot, web.getWebCross(0,0));

        bot.makeOptimalMove();

        Assert.assertEquals(0, bot.getHealth());
        Assert.assertNull(bot.getWebCross());
    }

    @Test
    public void BotMovesNoEntitiesOnField(){
        Web web = new Web(3);

        BotSpiderMovementAlgorithm algorithm = new BotSpiderMovementAlgorithm(web);

        BotSpider bot = new BotSpider(1, null, algorithm);
        web.addBotSpider(bot, web.getWebCross(0,0));

        bot.makeOptimalMove();

        Assert.assertEquals(1, bot.getHealth());
        Assert.assertNotNull(bot.getWebCross());
    }

    @Test
    public void BotMovesToPlayerOneHealth(){
        Web web = new Web(3);

        BotSpiderMovementAlgorithm algorithm = new BotSpiderMovementAlgorithm(web);

        PlayerSpider playerSpider = new PlayerSpider(10, null);
        web.setPlayer(playerSpider, web.getWebCross(0, 1));

        BotSpider bot = new BotSpider(1, null, algorithm);
        web.addBotSpider(bot, web.getWebCross(0,0));

        bot.makeOptimalMove();

        Assert.assertEquals(0, bot.getHealth());
        Assert.assertNull(bot.getWebCross());
    }

    @Test
    public void BotMovesToInsectEast(){
        Web web = new Web(3);

        BotSpiderMovementAlgorithm algorithm = new BotSpiderMovementAlgorithm(web);

        Insect insect = new Mole(10, null);
        web.addInsect(insect, web.getWebCross(0,2));

        BotSpider bot = new BotSpider(10, null, algorithm);
        web.addBotSpider(bot, web.getWebCross(0,0));

        bot.makeOptimalMove();

        Assert.assertEquals(9, bot.getHealth());
        Assert.assertEquals(web.getWebCross(0,1).getAnimal(), bot);
    }

    @Test
    public void BotMovesToInsectSouth(){
        Web web = new Web(3);

        BotSpiderMovementAlgorithm algorithm = new BotSpiderMovementAlgorithm(web);

        Insect insect = new Mole(10, null);
        web.addInsect(insect, web.getWebCross(2,0));

        BotSpider bot = new BotSpider(10, null, algorithm);
        web.addBotSpider(bot, web.getWebCross(0,0));

        bot.makeOptimalMove();

        Assert.assertEquals(9, bot.getHealth());
        Assert.assertEquals(web.getWebCross(1,0).getAnimal(), bot);
    }

    @Test
    public void BotMovesToInsectWest(){
        Web web = new Web(3);

        BotSpiderMovementAlgorithm algorithm = new BotSpiderMovementAlgorithm(web);

        Insect insect = new Mole(10, null);
        web.addInsect(insect, web.getWebCross(2,0));

        BotSpider bot = new BotSpider(10, null, algorithm);
        web.addBotSpider(bot, web.getWebCross(2,2));

        bot.makeOptimalMove();

        Assert.assertEquals(9, bot.getHealth());
        Assert.assertEquals(web.getWebCross(2,1).getAnimal(), bot);
    }

    @Test
    public void BotMovesToInsectNorth(){
        Web web = new Web(3);

        BotSpiderMovementAlgorithm algorithm = new BotSpiderMovementAlgorithm(web);

        Insect insect = new Mole(10, null);
        web.addInsect(insect, web.getWebCross(0,2));

        BotSpider bot = new BotSpider(10, null, algorithm);
        web.addBotSpider(bot, web.getWebCross(2,2));

        bot.makeOptimalMove();

        Assert.assertEquals(9, bot.getHealth());
        Assert.assertEquals(web.getWebCross(1,2).getAnimal(), bot);
    }





}
