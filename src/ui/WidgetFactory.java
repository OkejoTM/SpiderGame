package ui;

import Setting.*;
import ui.cell.*;

import java.util.HashMap;
import java.util.Map;

import static Utils.InsectUtils.getAllInsectSubclasses;
import static ui.cell.WebCrossWidget.CELL_SIZE;

public class WidgetFactory {
    private final Map<WebCross, WebCrossWidget> _webCrosses = new HashMap<>();
    private final Map<Animal, AnimalWidget> _animalMap = new HashMap<>();

    private boolean _playerInWeb = false;

    public WebCrossWidget create(WebCross webCross){
        if (_webCrosses.containsKey(webCross))
            return _webCrosses.get(webCross);

        WebCrossWidget item = new WebCrossWidget();
        _webCrosses.put(webCross, item);

        Animal animal = webCross.getAnimal();
        if (animal != null){
            this.create(animal);
        }

        return item;
    }

    public WebCrossWidget getWidget(WebCross webCross){
        return _webCrosses.get(webCross);
    }

    public PlayerSpiderWidget getWidget(PlayerSpider playerSpider){
        return (PlayerSpiderWidget) _animalMap.get(playerSpider);
    }

    public BotSpiderWidget getWidget(BotSpider botSpider){
        return (BotSpiderWidget) _animalMap.get(botSpider);
    }

    public AnimalWidget getWidget(Animal animal){
        return _animalMap.get(animal);
    }

    public void remove(Animal animal){
        _animalMap.remove(animal);
    }

    public AnimalWidget create(Animal animal){
        if (animal == null){
            throw new IllegalArgumentException();
        }
        if (animal instanceof PlayerSpider && _playerInWeb){
            throw new RuntimeException("Can not be 2 players in 1 game");
        }
        if (_animalMap.containsKey(animal)){
            return _animalMap.get(animal);
        }

        AnimalWidget animalWidget = createWidget(animal);
        WebCrossWidget webCrossWidget = _webCrosses.get(animal.getWebCross());

        if (animalWidget != null && webCrossWidget != null){
            webCrossWidget.addItem(animalWidget);
            _animalMap.put(animal, animalWidget);
        }

        return animalWidget;
    }

    private PlayerSpiderWidget createPlayer(PlayerSpider playerSpider){
        _playerInWeb = true;
        return new PlayerSpiderWidget(playerSpider);
    }

    private AnimalWidget createWidget(Animal animal){
        int size = 120;
        if (getAllInsectSubclasses().contains(animal.getClass())){
            size = calculateSize(((Insect)animal).getSize());
        }
        return switch (animal.getClass().getName().split("\\.")[animal.getClass().getName().split("\\.").length - 1]) {
            case "PlayerSpider" -> {
                assert animal instanceof PlayerSpider;
                yield createPlayer((PlayerSpider) animal);
            }
            case "BotSpider" -> {
                assert animal instanceof BotSpider;
                yield new BotSpiderWidget((BotSpider) animal);
            }
            case "Fly" -> new InsectWidget("images/fly.png", size, size);
            case "Mole" -> new InsectWidget("images/mole.png", size, size);
            case "GrassHopper" -> new InsectWidget("images/grasshopper.png", size, size);
            case "Wasp" -> new InsectWidget("images/bee.png", size, size);
            default -> null;
        };
    }

    private int calculateSize(int size) {
        return (int) (CELL_SIZE /3 + (size - 1) * (CELL_SIZE/1.5 - CELL_SIZE/3) / 9);
    }
}
