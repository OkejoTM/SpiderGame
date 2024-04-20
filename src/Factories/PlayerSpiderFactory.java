package Factories;

import Setting.PlayerSpider;

public class PlayerSpiderFactory extends AbstractSpiderFactory{
    @Override
    public PlayerSpider create(int health) {
        return new PlayerSpider(health, null);
    }
}
