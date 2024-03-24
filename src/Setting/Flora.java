package Setting;

import Entities.*;
import Utils.Algorithm;

import java.awt.*;

public class Flora {
    private Web _web;

    public Flora(Web web){
        _web = web;
    }

    public void instantiateAnimals(){
        createPlayerSpider();
        createBotSpiders(2);
        createInsects(2);
    }

    public boolean createPlayerSpider(){
        if (_web.isPlayerInWeb()) return false;
        int spiderHealth = 10;
        int pos = (_web.getSize()-1)/2;
        PlayerSpider playerSpider = new PlayerSpider(spiderHealth, _web.getWebCross(new Point(pos, pos)));
        playerSpider.getWebCross().setAnimal(playerSpider);
        _web.setPlayer(playerSpider);
        return true;
    }

    public void createBotSpiders(int amount){
        for (int i = 0; i < amount; i++){
            int spiderHealth = 10;
            BotSpider botSpider = new BotSpider(spiderHealth, getRandomWebCross(), new Algorithm(_web));
            botSpider.getWebCross().setAnimal(botSpider);
            _web.addBotSpider(botSpider);
        }
    }

    public void createInsects(int amount){
        for (int i = 0; i < amount; i++){
            int moleHealth = 2;
            Mole mole = new Mole(moleHealth, getRandomWebCross());
            mole.getWebCross().setAnimal(mole);
            _web.addInsect(mole);
        }
    }

    public WebCross getRandomWebCross(){
        int randomX = (int)(Math.random()*_web.getSize()-1);
        int randomY = (int)(Math.random()*_web.getSize()-1);
        while (_web.getWebCross(new Point(randomX, randomY)).getAnimal() != null)
        {
            randomX = (int)(Math.random()*_web.getSize()-1);
            randomY = (int)(Math.random()*_web.getSize()-1);
        }
        return _web.getWebCross(new Point(randomX, randomY));
    }
}
