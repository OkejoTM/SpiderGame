package Setting;

import java.awt.*;
import java.util.ArrayList;
import Entities.*;
import Interfaces.IPrey;


public class Web {
    private final int _size;
    private boolean _playerInWeb = false;
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

    public boolean isPlayerInWeb(){
        return _playerInWeb;
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
        for (var webCross : _webCrossList){
            if (position.equals(webCross.getPosition())){
                return webCross;
            }
        }
        return null;
    }

    void setPlayer(PlayerSpider spider){
        _playerSpider = spider;
        _playerInWeb = true;
    }

    void addBotSpider(BotSpider botSpider){
        _botSpidersList.add(botSpider);
    }

    void addInsect(Insect insect){
        _insectsList.add(insect);
    }

    public boolean removePlayer(){
        if (_playerSpider != null){
            _playerSpider = null;
            _playerInWeb = false;
            return true;
        }
        return false;
    }

    public boolean removeInsect(Insect insect){
        if (!_insectsList.isEmpty()){
            return _insectsList.remove(insect);
        }
        return false;
    }

    public boolean removeBotSpider(BotSpider botSpider){
        if (!_botSpidersList.isEmpty()){
            return _botSpidersList.remove(botSpider);
        }
        return false;
    }

    public ArrayList<IPrey> getAllPreys(){
        ArrayList<IPrey> preyList = new ArrayList<IPrey>(_insectsList);
        if (_playerSpider != null) preyList.add(_playerSpider);
        return preyList;
    }

    public PlayerSpider getPlayer(){
        return _playerSpider;
    }

    public ArrayList<BotSpider> getBotSpiders(){
        return _botSpidersList;
    }

    public ArrayList<Insect> getInsects(){
        return _insectsList;
    }


}
