import Entities.BotSpider;
import Setting.*;
import Utils.Direction;
import java.lang.Math;
import java.util.Iterator;

public class Game {
    private Web _web;
    private Flora _flora;

    public Game(Flora flora){
        createWeb(4);
        _flora = flora;
        _flora.setWeb(_web);
    }

    public void startGame(){
        _flora.instantiateAnimals();
        do
        {
            Direction[] directions = new Direction[] {Direction.north(), Direction.east(),Direction.west(),Direction.south()};
            int randomDirection = (int)(Math.random()*4);
//            _web.getPlayer().makeMove(directions[randomDirection]);

            double randomDigit = Math.random()*100;
            if (randomDigit >10 && randomDigit < 15 && !_web.getInsects().isEmpty()){
                int randomIndex = (int)(0+Math.random()*_web.getInsects().size());
                _web.getInsects().get(randomIndex).die();
            }

            randomDigit = Math.random()*100;
            if (randomDigit > 10 && randomDigit < 15){
                _flora.createInsects(1);
            }

            // Если не осталось пауков-ботов, gameOver - false
            moveAllBots();

        }while(_web.isPlayerInWeb() && !_web.getBotSpiders().isEmpty());
        endGame();
    }

    public void endGame(){
        System.out.println(_web.getPlayer());
        System.out.println(_web.getBotSpiders().size());
        _web.clearWeb();
        _flora.setWeb(null);
        _flora = null;
        _web = null;
    }

    public void createWeb(int size){
        _web = new Web(size);
    }

    public void moveAllBots(){
        Iterator<BotSpider> botSpiderIterator = _web.getBotSpiders().iterator();
        while (botSpiderIterator.hasNext()){
            BotSpider bot = botSpiderIterator.next();
            bot.makeOptimalMove();
            if (bot.getHealth() == 0){
                botSpiderIterator.remove();
            }
        }
    }
}
