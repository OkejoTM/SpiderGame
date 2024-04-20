package Factories;

import Entities.BotSpider;
import Entities.Spider;
import Utils.BotSpiderMovementAlgorithm;

public class BotSpiderFactory extends AbstractSpiderFactory{
    @Override
    public BotSpider create(int health) {
         return new BotSpider(health, null, null);
    }
}
