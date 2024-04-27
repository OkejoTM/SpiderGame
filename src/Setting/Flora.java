package Setting;

import Factories.*;
import Utils.BotSpiderMovementAlgorithm;

import java.util.ArrayList;

public class Flora {
    private Web _web;

    private static final ArrayList<AbstractInsectFactory> factories = new ArrayList<>();

    static {
        factories.add(new MoleFactory());
        factories.add(new WaspFactory());
        factories.add(new FlyFactory());
        factories.add(new GrassHopperFactory());
    }

    private static final PlayerSpiderFactory playerFactory = new PlayerSpiderFactory();
    private static final BotSpiderFactory botSpiderFactory = new BotSpiderFactory();

    public Flora(Web web) {
        _web = web;
    }

    public void setWeb(Web web) {
        _web = web;
    }


    public void instantiateAnimals() {
//        generateEasyLevel();
        generatePlayerSpider(100);
        generateBotSpiders(3);

    }

    public void generateEasyLevel() {
        generatePlayerSpider(10);
        generateBotSpiders(2);
        generateInsects(2);
    }

    private void generatePlayerSpider(int spiderHealth) {
        if (_web.getPlayer() != null) return;
        int pos = _web.getSize() / 2;
        WebCross webCross = _web.getWebCross(pos, pos);
        PlayerSpider playerSpider = playerFactory.create(spiderHealth);
        _web.setPlayer(playerSpider, webCross);
    }

    private void generateBotSpiders(int amount) {
        ArrayList<WebCross> emptyWebCrosses = _web.getEmptyWebCrosses();

        if (amount > emptyWebCrosses.size()) return;
        int created = 0;

        while (created < amount && !emptyWebCrosses.isEmpty()) {
            int spiderHealth = 5;
            WebCross webCross = getRandomWebCross(emptyWebCrosses);
            BotSpider botSpider = botSpiderFactory.create(spiderHealth);
            botSpider.setMovementAlgorithm(new BotSpiderMovementAlgorithm(_web));
            _web.addBotSpider(botSpider, webCross);
            emptyWebCrosses.remove(webCross);
            created++;
        }
    }

    // Game can invoke this method
    void generateInsects() {
        ArrayList<Insect> insectList = insectsFabricCreation();
        ArrayList<WebCross> emptyWebCrosses = _web.getEmptyWebCrosses();

        placeInsects(insectList, emptyWebCrosses, insectList.size());
    }

    private void generateInsects(int amount) {
        ArrayList<Insect> insectList = insectsFabricCreation(amount);
        ArrayList<WebCross> emptyWebCrosses = _web.getEmptyWebCrosses();

        placeInsects(insectList, emptyWebCrosses, amount);
    }

    private void placeInsects(ArrayList<Insect> insectList, ArrayList<WebCross> emptyWebCrosses, int amount) {
        while (amount > 0 && !insectList.isEmpty() && !emptyWebCrosses.isEmpty()) {
            WebCross webCross = getRandomWebCross(emptyWebCrosses);
            Insect insect = insectList.get(0);
            _web.addInsect(insect, webCross);
            insectList.remove(insect);
            emptyWebCrosses.remove(webCross);
        }
    }

    private ArrayList<Insect> insectsFabricCreation() {
        ArrayList<Insect> insectArrayList = new ArrayList<>();

        for (AbstractInsectFactory factory : factories) {
            Insect insect = factory.createInsect();
            if (insect != null) // Если создалось насекомое
            {
                insectArrayList.add(insect);
            }
        }

        return insectArrayList;
    }

    private ArrayList<Insect> insectsFabricCreation(int amount) {

        int created = 0;
        int factoryIndex = 0;
        ArrayList<Insect> insectArrayList = new ArrayList<>();

        while (created < amount) {
            AbstractInsectFactory factory = factories.get(factoryIndex);
            Insect insect = factory.createInsect();
            if (insect != null) // Если создалось насекомое
            {
                insectArrayList.add(insect);
                created++;
            }
            factoryIndex = (factoryIndex + 1) % factories.size();
        }

        return insectArrayList;
    }

    private WebCross getRandomWebCross(ArrayList<WebCross> webCrossesList) {
        return webCrossesList.get((int) (Math.random() * (webCrossesList.size() - 1)));
    }


}
