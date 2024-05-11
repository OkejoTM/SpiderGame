package Setting;

import Factories.*;
import Utils.BotSpiderMovementAlgorithm;

import java.util.ArrayList;

public class Fauna {
    private final Web _web;

    private static final ArrayList<AbstractInsectFactory> factories = new ArrayList<>();

    static {
        factories.add(new MoleFactory());
        factories.add(new WaspFactory());
        factories.add(new FlyFactory());
        factories.add(new GrassHopperFactory());
    }

    private static final PlayerSpiderFactory playerFactory = new PlayerSpiderFactory();
    private static final BotSpiderFactory botSpiderFactory = new BotSpiderFactory();

    public Fauna(Web web) {
        _web = web;
    }

    public void instantiateAnimals(Game.GameLevel level) {
        switch (level){
            case EASY -> generateEasyLevel();
            case MIDDLE -> generateMiddleLevel();
            case HARD -> generateHardLevel();
        }
    }

    private void generateEasyLevel() {
        generatePlayerSpider(100);
        generateBotSpiders(1);
        generateInsects(5);
    }

    private void generateMiddleLevel() {
        generatePlayerSpider(50);
        generateBotSpiders(2);
        generateInsects(3);
    }

    private void generateHardLevel() {
        generatePlayerSpider(10);
        generateBotSpiders(3);
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
            int spiderHealth = 10;
            WebCross webCross = getRandomWebCross(emptyWebCrosses);
            BotSpider botSpider = botSpiderFactory.create(spiderHealth);
            botSpider.setMovementAlgorithm(new BotSpiderMovementAlgorithm(_web));
            _web.addBotSpider(botSpider, webCross);
            emptyWebCrosses.remove(webCross);
            created++;
        }
    }

    // Game can invoke this method
    ArrayList<Insect> generateInsects() {
        ArrayList<Insect> insectList = insectsFabricCreation();
        ArrayList<WebCross> emptyWebCrosses = _web.getEmptyWebCrosses();

        return placeInsects(insectList, emptyWebCrosses, insectList.size());
    }

    private void generateInsects(int amount) {
        ArrayList<Insect> insectList = insectsFabricCreation(amount);
        ArrayList<WebCross> emptyWebCrosses = _web.getEmptyWebCrosses();

        placeInsects(insectList, emptyWebCrosses, amount);
    }

    private ArrayList<Insect> placeInsects(ArrayList<Insect> insectList, ArrayList<WebCross> emptyWebCrosses, int amount) {
        ArrayList<Insect> createdList = new ArrayList<>();
        while (amount > 0 && !insectList.isEmpty() && !emptyWebCrosses.isEmpty()) {
            WebCross webCross = getRandomWebCross(emptyWebCrosses);
            Insect insect = insectList.get(0);
            _web.addInsect(insect, webCross);
            createdList.add(insect);
            insectList.remove(insect);
            emptyWebCrosses.remove(webCross);
        }
        return createdList;
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