package ui;

import Setting.Animal;
import Setting.PlayerSpider;
import Setting.WebCross;
import ui.cell.PlayerSpiderWidget;
import ui.cell.WebCrossWidget;

import java.util.HashMap;
import java.util.Map;

public class WidgetFactory {
    private final Map<WebCross, WebCrossWidget> _webCrosses = new HashMap<>();
    private final Map<PlayerSpider, PlayerSpiderWidget> _playerSpider = new HashMap<>();


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


}
