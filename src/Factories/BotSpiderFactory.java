package Factories;

import Setting.BotSpider;

public class BotSpiderFactory extends AbstractSpiderFactory{
    @Override
    public BotSpider create(int health) {
         return new BotSpider(health, null, null);
    }
}
