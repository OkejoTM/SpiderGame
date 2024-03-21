package Setting;

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
    }

    public int getSize() {
        return _size;
    }

    public boolean isPlayerInWeb(){
        return _playerInWeb;
    }

    private void createWebCrosses(){

    }
    public WebCross getWebCross(int dx, int dy){
        return null;
    }

    void setPlayer(PlayerSpider player){

    }

    void addBotSpiders(BotSpider botSpider){

    }

    void addInsect(Insect insect){

    }

    public boolean removePlayer(){
        return false;
    }

    public boolean removeInsect(Insect insect){
        return false;
    }

    public boolean removeBotSpider(BotSpider botSpider){
        return false;
    }

    public ArrayList<IPrey> getAllPreys(){
        return null;
    }


}
