package Setting;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import Entities.*;
import Interfaces.IPrey;


public class Web {
    private final int _size;
    private PlayerSpider _playerSpider;
    private ArrayList<WebCross> _webCrossList;
    private ArrayList<BotSpider> _botSpidersList;
    private ArrayList<Insect> _insectsList;

    public Web(int size){
        _size = size;
        createWebCrosses();
        _insectsList = new ArrayList<>();
        _botSpidersList = new ArrayList<>();
    }

    public int getSize() {
        return _size;
    }

    private void createWebCrosses(){
        _webCrossList = new ArrayList<>();
        for (int i = 0 ; i < _size-1; i++){
            for (int j = 0 ; j < _size-1; j++){
                _webCrossList.add(new WebCross(this, new Point(i,j)));
            }
        }
    }

    public WebCross getWebCross(Point position){
        for (WebCross webCross : _webCrossList){
            if (position.equals(webCross.getPosition())){
                return webCross;
            }
        }
        return null;
    }

    void setPlayer(PlayerSpider spider){
        if (_playerSpider == null){
            _playerSpider = spider;
        }
    }

    void addBotSpider(BotSpider botSpider){
        if (!_botSpidersList.contains(botSpider)){
            _botSpidersList.add(botSpider);
        }
    }

    void addInsect(Insect insect){
        if (!_insectsList.contains(insect)){
            _insectsList.add(insect);
        }
    }

    boolean removePlayer(){
        if (_playerSpider != null){
            _playerSpider.clear();
            _playerSpider = null;
            return true;
        }
        return false;
    }

    boolean removeInsects(ArrayList<Insect> insectRange){
        if (!_insectsList.isEmpty()){
            for (var insect : insectRange){
                if (!_insectsList.contains(insect)){
                    return false;
                }
                insect.clear();
            }
            return _insectsList.removeAll(insectRange);
        }
        return false;
    }

    boolean removeBotSpiders(ArrayList<BotSpider> botSpiderRange){
        if (!_botSpidersList.isEmpty()){
            for(var bot : botSpiderRange){
                if (!_botSpidersList.contains(bot)){
                    return false;
                }
                bot.clear();
            }
            return _botSpidersList.removeAll(botSpiderRange);
        }
        return false;
    }

    public ArrayList<IPrey> getAllPreys(){
        ArrayList<IPrey> preyList = new ArrayList<>(_insectsList);
        if (_playerSpider != null) preyList.add(_playerSpider);
        return preyList;
    }

    public ArrayList<WebCross> getEmptyWebCrosses(){
        ArrayList<WebCross> emptyWebCrossList = new ArrayList<>();
        for (WebCross webCross : _webCrossList){
            if (webCross.getAnimal() == null){
                emptyWebCrossList.add(webCross);
            }
        }
        return emptyWebCrossList;
    }

    public ArrayList<WebCross> getWebCrosses(){
        ArrayList<WebCross> webCrosses = new ArrayList<>();
        for(WebCross webCross : _webCrossList){
            webCrosses.add((WebCross)webCross.clone());
        }
        return webCrosses;
    }

    public PlayerSpider getPlayer(){
        return _playerSpider;
    }

    public ArrayList<BotSpider> getBotSpiders(){
        return new ArrayList<>(_botSpidersList);
    }

    public ArrayList<Insect> getInsects() {
        return new ArrayList<>(_insectsList);
    }

    public void clear(){
        removePlayer();
        clearSpiders();
        clearInsects();
        clearWebCrosses();
    }

    private void clearWebCrosses(){
        for (WebCross webCross : _webCrossList){
            webCross.clear();
        }
        _webCrossList.clear();
    }

    private void clearSpiders(){
        for (BotSpider spider : _botSpidersList){
            spider.clear();
        }
        _botSpidersList.clear();
    }

    private void clearInsects(){
        for (Insect insect : _insectsList){
            insect.clear();
        }
        _insectsList.clear();
    }
}
