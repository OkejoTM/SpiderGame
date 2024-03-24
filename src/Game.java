import Setting.*;
import Utils.Direction;
import java.lang.Math;
import java.util.ArrayList;

public class Game {
    private Web _web;
    private Flora _flora;
    private boolean gameOver = false;

    public Game(){
        createWeb(4);
        _flora = new Flora(_web);
    }

    public void startGame(){
        _flora.instantiateAnimals();
        do
        {
            Direction[] directions = new Direction[] {Direction.north(), Direction.east(),Direction.west(),Direction.south()};
            int randomDirection = (int)(Math.random()*4);
            _web.getPlayer().makeMove(directions[randomDirection]);

            double randomDigit = Math.random()*100;
            if (randomDigit >10 && randomDigit < 15 && !_web.getInsects().isEmpty()){
                int randomIndex = (int)(0+Math.random()*_web.getInsects().size());
                _web.getInsects().get(randomIndex).die();
            }

            randomDigit = Math.random()*100;
            if (randomDigit > 10 && randomDigit < 15){
                _flora.createInsects(1);
            }

            // Если не осталось пауков-ботов
            if (!moveAllBots()){
                gameOver = true;
            };
        }while(_web.isPlayerInWeb() && !gameOver);
        endGame();
    }

    public void endGame(){
        _web.clearWeb();
        _flora.setWeb(null);
        _flora = null;
    }

    public void createWeb(int size){
        _web = new Web(size);
    }

    public boolean moveAllBots(){
        if (_web.getBotSpiders().isEmpty()) return false;
        for (var bot : _web.getBotSpiders()){
            bot.makeOptimalMove();
        }
        return true;
    }

    public static void main(String[] args){
        Game game = new Game();
        game.startGame();
    }
}
