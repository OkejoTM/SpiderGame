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
            if (_playerSpider.getHealth() == 0){
                die();
            }
        }
    }

    private void clearPlayerEatBehaviour(){
        _playerSpider.clearEatBehaviour();
    }

    private void clearAlgorithm(){
        _algorithm = null;
    }

    public void botDie(){
        clearPlayerEatBehaviour();
        clearAlgorithm();
        _playerSpider = null;
    }

}
