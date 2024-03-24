package Entities;

import Interfaces.IDieable;
import Interfaces.IPredator;
import Interfaces.IPrey;
import Setting.WebCross;
import Utils.Algorithm;
import Utils.Direction;
import Utils.PredatorEatBehaviour;

public class BotSpider extends Animal implements IDieable {
    private Algorithm _algorithm;
    private PlayerSpider _playerSpider;
    private IPredator _predatorBehaviour = new PredatorEatBehaviour();

    public BotSpider(int health, WebCross webCross, Algorithm algorithm) {
        super(health, webCross);
        _playerSpider = new PlayerSpider(health, webCross, _predatorBehaviour);
        _algorithm = algorithm;
    }

    public void makeOptimalMove(){
        Direction direction = _algorithm.findDirectionToNearest(this.getWebCross());
        if (direction != null){
            _playerSpider.makeMove(direction);
        }
    }

    void setEatBehaviour(IPredator behaviour){
        _predatorBehaviour = behaviour;
    }

}
