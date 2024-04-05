package Setting;

import Entities.*;
import Factories.AbstractInsectFactory;
import Utils.Algorithm;

import java.awt.*;
import java.util.ArrayList;

public class Flora {
    private Web _web;

    public Flora() {
    }

    public void instantiateAnimals() {
        createPlayerSpider();
        createBotSpiders(1);
//        createInsects();
    }

    public boolean createPlayerSpider() {
        if (_web.isPlayerInWeb()) return false;
        int spiderHealth = 1;
        int pos = (_web.getSize() - 1) / 2;
        PlayerSpider playerSpider = new PlayerSpider(spiderHealth, _web.getWebCross(new Point(pos, pos)));
        placeAnimalInWebCross(playerSpider.getWebCross(), playerSpider);
        _web.setPlayer(playerSpider);
        return true;
    }

    public void createBotSpiders(int amount) {
        for (int i = 0; i < amount; i++) {
            int spiderHealth = 10;
//            BotSpider botSpider = new BotSpider(spiderHealth, getRandomWebCross(), new Algorithm(_web));
            BotSpider botSpider = new BotSpider(spiderHealth, _web.getWebCross(new Point(0, 0)), new Algorithm(_web));
            placeAnimalInWebCross(botSpider.getWebCross(), botSpider);
            _web.addBotSpider(botSpider);
        }
    }

    public void createInsects(ArrayList<AbstractInsectFactory> factories) {
        for (var factory : factories) {
            ArrayList<WebCross> emptyWebCrosses = _web.getEmptyWebCrosses();
            var insect = factory.createInsect();
            if (insect != null && !emptyWebCrosses.isEmpty()) // Если создалось насекомое, и место для установки есть
            {
                placeAnimalInWebCross(getRandomWebCross(emptyWebCrosses), insect);
            }
        }
    }

    private void placeAnimalInWebCross(WebCross webCross, Animal animal) {
        webCross.setAnimal(animal);
    }

    private WebCross getRandomWebCross(ArrayList<WebCross> webCrossesList) {
        return webCrossesList.get((int)(Math.random() * (webCrossesList.size() - 1)));
    }

    public void setWeb(Web web) {
        _web = web;
    }

}
