package Factories;

import Entities.PlayerSpider;
import Entities.Spider;

public class PlayerSpiderFactory extends AbstractSpiderFactory{
    @Override
    public PlayerSpider create(int health) {
        return new PlayerSpider(health, null);
    }
}
