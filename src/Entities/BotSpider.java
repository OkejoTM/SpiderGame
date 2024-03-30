package Entities;

import Setting.WebCross;
import Utils.Algorithm;
import Utils.Direction;
import Utils.PredatorEatBehaviour;

public class BotSpider extends Animal{
    private Algorithm _algorithm;
    private PlayerSpider _playerSpider;

    public BotSpider(int health, WebCross webCross, Algorithm algorithm) {
        super(health, webCross);
        _playerSpider = new PlayerSpider(health, webCross,  new PredatorEatBehaviour());
        _algorithm = algorithm;
    }

    public void makeOptimalMove(){
        Direction direction = _algorithm.findDirectionToNearest(this.getWebCross());
        if (direction != null){
            _playerSpider.makeMove(direction);
        }
    }

    public void clearEatBehaviour(){
        _playerSpider.setEatBehaviour(null);
    }

}
