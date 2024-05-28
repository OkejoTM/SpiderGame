package Setting;

import Utils.BotSpiderMovementAlgorithm;
import Utils.InsectUtils;

import java.util.ArrayList;
import java.util.Set;

public class Fauna {
    private final Web _web;

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
        generateInsects(10);
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
        PlayerSpider playerSpider = PlayerSpider.create(spiderHealth);
        _web.setPlayer(playerSpider, webCross);
    }

    private void generateBotSpiders(int amount) {
        ArrayList<WebCross> emptyWebCrosses = _web.getEmptyWebCrosses();

        if (amount > emptyWebCrosses.size()) return;
        int created = 0;

        while (created < amount && !emptyWebCrosses.isEmpty()) {
            int spiderHealth = 10;
            WebCross webCross = getRandomWebCross(emptyWebCrosses);
            BotSpider botSpider = BotSpider.create(spiderHealth);
            botSpider.setMovementAlgorithm(new BotSpiderMovementAlgorithm(_web));
            _web.addBotSpider(botSpider, webCross);
            emptyWebCrosses.remove(webCross);
            created++;
        }
    }

    // Game can invoke this method
    ArrayList<Insect> generateInsects() {
        ArrayList<Insect> insectList = insectsCreation();
        ArrayList<WebCross> emptyWebCrosses = _web.getEmptyWebCrosses();

        return placeInsects(insectList, emptyWebCrosses, insectList.size());
    }

    private void generateInsects(int amount) {
        ArrayList<Insect> insectList = insectsCreation(amount);
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

    private ArrayList<Insect> insectsCreation() {
        ArrayList<Insect> insectArrayList = new ArrayList<>();
        Set<Class<? extends Insect>> insectClasses = InsectUtils.getAllInsectSubclasses();

        for (Class<? extends Insect> insectClass: insectClasses) {
            Insect insect = Insect.create(insectClass);

            if (insect != null) {
                insectArrayList.add(insect);
            }
        }

        return insectArrayList;
    }

    private ArrayList<Insect> insectsCreation(int amount) {
        ArrayList<Insect> insectArrayList = new ArrayList<>();
        Set<Class<? extends Insect>> insectClasses = InsectUtils.getAllInsectSubclasses();

        int created = 0;

        while (created < amount) {
            for (Class<? extends Insect> insectClass : insectClasses) {
                if (created >= amount) break;

                Insect insect = Insect.create(insectClass);

                if (insect != null) {
                    insectArrayList.add(insect);
                    created++;
                }
            }
        }

        return insectArrayList;
    }

    private WebCross getRandomWebCross(ArrayList<WebCross> webCrossesList) {
        return webCrossesList.get((int) (Math.random() * (webCrossesList.size() - 1)));
    }


}
