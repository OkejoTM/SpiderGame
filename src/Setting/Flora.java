package Setting;

import Entities.*;
import Factories.*;
import Utils.Algorithm;

import java.awt.*;
import java.util.ArrayList;

public class Flora {
    private Web _web;

    public Flora() {
    }

    public void instantiateAnimals() {
        createPlayerSpider(2);
        createBotSpiders(1);
        ArrayList<AbstractInsectFactory> factories = new ArrayList<>();
        factories.add(new MoleFactory());
        factories.add(new WaspFactory());
        factories.add(new FlyFactory());
        factories.add(new GrassHopperFactory());
        createInsects(factories);
    }

    public boolean createPlayerSpider(int spiderHealth) {
        if (_web.getPlayer() != null) return false;
        int pos = (_web.getSize() - 1) / 2;
        WebCross webCross = _web.getWebCross(new Point(pos, pos));
        PlayerSpider playerSpider = new PlayerSpider(spiderHealth, null);
        placeAnimalInWebCross(webCross, playerSpider);
        _web.setPlayer(playerSpider);
        return true;
    }

    public boolean createBotSpiders(int amount) {
        if (amount > _web.getEmptyWebCrosses().size()) return false;
        for (int i = 0; i < amount; i++) {
            int spiderHealth = 10;
            ArrayList<WebCross> emptyWebCrosses = _web.getEmptyWebCrosses();
            BotSpider botSpider = new BotSpider(spiderHealth, null, new Algorithm(_web));
//            BotSpider botSpider = new BotSpider(spiderHealth, _web.getWebCross(new Point(0, 0)), new Algorithm(_web));
            placeAnimalInWebCross(getRandomWebCross(emptyWebCrosses), botSpider);
            _web.addBotSpider(botSpider);
        }
        return true;
    }


    public void createInsects(ArrayList<AbstractInsectFactory> factories) {
        for (AbstractInsectFactory factory : factories) {
            ArrayList<WebCross> emptyWebCrosses = _web.getEmptyWebCrosses();
            Insect insect = factory.createInsect();
            if (insect != null && !emptyWebCrosses.isEmpty()) // Если создалось насекомое, и место для установки есть
            {
                placeAnimalInWebCross(getRandomWebCross(emptyWebCrosses), insect);
                _web.addInsect(insect);
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
