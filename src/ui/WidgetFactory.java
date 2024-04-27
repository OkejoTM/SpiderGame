package ui;

import Setting.Animal;
import Setting.BotSpider;
import Setting.PlayerSpider;
import Setting.WebCross;
import ui.cell.BotSpiderWidget;
import ui.cell.PlayerSpiderWidget;
import ui.cell.WebCrossWidget;

import java.util.HashMap;
import java.util.Map;

public class WidgetFactory {
    private final Map<WebCross, WebCrossWidget> _webCrosses = new HashMap<>();
    private final Map<PlayerSpider, PlayerSpiderWidget> _playerSpider = new HashMap<>();
    private final Map<BotSpider, BotSpiderWidget> _botSpiderMap = new HashMap<>();


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

}
