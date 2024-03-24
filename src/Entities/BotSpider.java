package Entities;

import Interfaces.IDieable;
import Interfaces.IPredator;
import Interfaces.IPrey;
import Setting.WebCross;
import Utils.Algorithm;
import Utils.Direction;

public class BotSpider extends Animal implements IPredator, IDieable {
    private Algorithm _algorithm;
    private PlayerSpider _playerSpider;

    public BotSpider(int health, WebCross webCross, Algorithm algorithm) {
        super(health, webCross);
        _playerSpider = new PlayerSpider(health, webCross);
        _algorithm = algorithm;
    }

    public void makeOptimalMove(){
        Direction direction = _algorithm.findDirectionToNearest(this.getWebCross());
        if (_webCross.hasNext(direction)){
            WebCross nextWebCross = _webCross.getNextWebCross(direction);
            if (nextWebCross.getAnimal() == null){
                _playerSpider.changeHealth(-1);
                if (_health == 0)
                {
                    this.die();
                }else {
                    move(nextWebCross);
                }
            }
            else if (nextWebCross.getAnimal() instanceof IPrey prey){
                eat(prey);
                move(nextWebCross);
            }
        }
    }

    private void move(WebCross nextWebCross){
        _webCross.releaseAnimal(); // Убрать из текущего перекрестия
        nextWebCross.setAnimal(this); // Поставить животное в след. перекрестие
        setWebCross(nextWebCross); // Поставить животному след. перекрестие
    }

    @Override
    public void eat(IPrey prey) {
        if (prey instanceof Insect insect){
            _playerSpider.changeHealth(insect.getHealth());
            insect.die();
        }
        if (prey instanceof PlayerSpider playerSpider){
            playerSpider.die();
        }
    }
}
