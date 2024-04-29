package ui;

import Setting.*;
import ui.cell.*;

import java.util.HashMap;
import java.util.Map;

public class WidgetFactory {
    private final Map<WebCross, WebCrossWidget> _webCrosses = new HashMap<>();
    private final Map<PlayerSpider, PlayerSpiderWidget> _playerSpider = new HashMap<>();
    private final Map<BotSpider, BotSpiderWidget> _botSpiderMap = new HashMap<>();
    private final Map<Insect, InsectWidget> _insectMap = new HashMap<>();


    public WebCrossWidget create(WebCross webCross){
        if (_webCrosses.containsKey(webCross)) return _webCrosses.get(webCross);

        WebCrossWidget item = new WebCrossWidget();
        Animal animal = webCross.getAnimal();
        if (animal != null){
            switch (animal.getClass().getName().split("\\.")[animal.getClass().getName().split("\\.").length-1]){
                case "PlayerSpider":
                    PlayerSpiderWidget playerSpiderWidget = create((PlayerSpider) animal);
                    item.addItem(playerSpiderWidget);
                    break;
                case "BotSpider":
                    BotSpiderWidget botSpiderWidget = create((BotSpider) animal);
                    item.addItem(botSpiderWidget);
                    break;
                case "Fly":
                    FlyWidget flyWidget = create((Fly) animal);
                    item.addItem(flyWidget);
                    break;
                case "Mole":
                    MoleWidget moleWidget = create((Mole) animal);
                    item.addItem(moleWidget);
                    break;
                case "GrassHopper":
                    GrassHopperWidget grassHopperWidget = create((GrassHopper) animal);
                    item.addItem(grassHopperWidget);
                    break;
                case "Wasp":
                    WaspWidget waspWidget = create((Wasp) animal);
                    item.addItem(waspWidget);
                    break;
                case "Insect":
                    break;
            }
        }

        _webCrosses.put(webCross, item);
        return item;
    }

    public WebCrossWidget getWidget(WebCross webCross){
        return _webCrosses.get(webCross);
    }

    public PlayerSpiderWidget create(PlayerSpider playerSpider){
        if (_playerSpider.containsKey(playerSpider)) return _playerSpider.get(playerSpider);
        else if (_playerSpider.size() > 1) throw new IllegalArgumentException();

        PlayerSpiderWidget item = new PlayerSpiderWidget(playerSpider);
        _playerSpider.put(playerSpider, item);
        return item;
    }

    public PlayerSpiderWidget getWidget(PlayerSpider playerSpider){
        return _playerSpider.get(playerSpider);
    }

    public void remove(PlayerSpider playerSpider){
        _playerSpider.remove(playerSpider);
    }

    public BotSpiderWidget create(BotSpider botSpider){
        if (_botSpiderMap.containsKey(botSpider)) return _botSpiderMap.get(botSpider);
        BotSpiderWidget botSpiderWidget = new BotSpiderWidget(botSpider);
        _botSpiderMap.put(botSpider, botSpiderWidget);
        return botSpiderWidget;
    }

    public BotSpiderWidget getWidget(BotSpider botSpider){
        return _botSpiderMap.get(botSpider);
    }

    public void remove(BotSpider botSpider){
        _botSpiderMap.remove(botSpider);
    }

    private FlyWidget create(Fly fly){
        if (_insectMap.containsKey(fly)) return (FlyWidget)_insectMap.get(fly);
        FlyWidget flyWidget = new FlyWidget(fly);
        _insectMap.put(fly, flyWidget);

        return flyWidget;
    }

    private WaspWidget create(Wasp wasp){
        if (_insectMap.containsKey(wasp)) return (WaspWidget)_insectMap.get(wasp);
        WaspWidget waspWidget = new WaspWidget(wasp);
        _insectMap.put(wasp, waspWidget);

        return waspWidget;
    }

    private GrassHopperWidget create(GrassHopper grassHopper){
        if (_insectMap.containsKey(grassHopper)) return (GrassHopperWidget)_insectMap.get(grassHopper);
        GrassHopperWidget grassHopperWidget = new GrassHopperWidget(grassHopper);
        _insectMap.put(grassHopper, grassHopperWidget);

        return grassHopperWidget;
    }

    private MoleWidget create(Mole mole){
        if (_insectMap.containsKey(mole)) return (MoleWidget) _insectMap.get(mole);
        MoleWidget moleWidget = new MoleWidget(mole);
        _insectMap.put(mole, moleWidget);

        return moleWidget;
    }

    public InsectWidget getWidget(Insect insect) {
        return _insectMap.get(insect);
    }

    public void remove(Insect insect){
        _insectMap.remove(insect);
    }

    public InsectWidget create(Insect insect){
        if (_insectMap.containsKey(insect)){
            return _insectMap.get(insect);
        }
        WebCrossWidget item = _webCrosses.get(insect.getWebCross());
        switch(insect.getClass().getName().split("\\.")[insect.getClass().getName().split("\\.").length-1]){
            case "Fly":
                FlyWidget fly = create((Fly) insect);
                item.addItem(fly);
                return fly;
            case "Mole":
                MoleWidget mole = create((Mole) insect);
                item.addItem(mole);
                return mole;
            case "GrassHopper":
                GrassHopperWidget grassHopperWidget = create((GrassHopper) insect);
                item.addItem(grassHopperWidget);
                return grassHopperWidget;
            case "Wasp":
                WaspWidget waspWidget = create((Wasp) insect);
                item.addItem(waspWidget);
                return waspWidget;
        }
        return null;
    }


}
