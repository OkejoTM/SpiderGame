package Setting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import Interfaces.IPrey;
import Utils.Direction;
import Utils.WebCrossPosition;


public class Web implements Iterable<WebCross>{

    private final int _size;
    private PlayerSpider _playerSpider;
    private final HashMap<WebCrossPosition, WebCross> _webCrosses = new HashMap<>();
    private final ArrayList<BotSpider> _botSpidersList = new ArrayList<>();
    private final ArrayList<Insect> _insectsList = new ArrayList<>();

    public Web(int size){
        if (size < 0) throw new IllegalArgumentException("Illegal size");
        _size = size;
        createWebCrosses();
    }

    public int getSize() {
        return _size;
    }

    @Override
    public Iterator<WebCross> iterator() {
        return new WebIterator(this);
    }

    private void createWebCrosses(){
        for (int i = 0; i < _size  ; i++){
            for (int j = 0 ; j < _size  ; j++){
                WebCrossPosition pos = new WebCrossPosition(i,j);
                _webCrosses.put(pos, new WebCross(pos));
            }
        }

        for (int i = 0; i < _size  ; i++){
            for (int j = 0 ; j < _size  ; j++){
                WebCross webCross = getWebCross(new WebCrossPosition(i, j));

                if (_size > 1 && i < _size - 1){
                    webCross.setNeighbour(Direction.south(), getWebCross(i+1, j));
                }
                if (i > 0){
                    webCross.setNeighbour(Direction.north(), getWebCross(i-1, j));
                }
                if (_size > 1 && j < _size - 1){
                    webCross.setNeighbour(Direction.east(), getWebCross(i, j+1));
                }
                if (j > 0){
                    webCross.setNeighbour(Direction.west(), getWebCross(i, j-1));
                }

            }
        }
    }

    public WebCross getWebCross(WebCrossPosition position){
        return _webCrosses.get(position);
    }

    public WebCross getWebCross(int row, int column){
        return _webCrosses.get(new WebCrossPosition(row, column));
    }

    // Сделал public для тестов
    public void setPlayer(PlayerSpider spider, WebCross webCross){
        if (_playerSpider == null){
            webCross.setAnimal(spider);
            _playerSpider = spider;
        }
    }

    // Сделал public для тестов
    public void addBotSpider(BotSpider botSpider,WebCross webCross){
        if (!_botSpidersList.contains(botSpider)){
            webCross.setAnimal(botSpider);
            _botSpidersList.add(botSpider);
        }
    }

    // Сделал public для тестов
    public void addInsect(Insect insect, WebCross webCross){
        if (!_insectsList.contains(insect)){
            webCross.setAnimal(insect);
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

    boolean removeInsect(Insect insect){
        if (_insectsList.contains(insect)){
            insect.clear();
            return _insectsList.remove(insect);
        }
        return false;
    }

    boolean removeBotSpider(BotSpider spider){
        if (_botSpidersList.contains(spider)){
            spider.clear();
            return _botSpidersList.remove(spider);
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
        Iterator<WebCross> webCrossIterator =  new WebIterator(this);
        while(webCrossIterator.hasNext()){
            WebCross webCross = webCrossIterator.next();
            if (webCross.getAnimal() == null)
            {
                emptyWebCrossList.add(webCross);
            }
        }
        return emptyWebCrossList;
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

    // --- Iterator for webCrosses ---
    private class WebIterator implements Iterator<WebCross>{

        private WebCross _webCross = null;
        private final Web _web;

        public WebIterator(Web web){
            _web = web;
        }

        @Override
        public boolean hasNext() {
            return nextWebCross(_webCross) != null;
        }

        @Override
        public WebCross next() {
            _webCross = nextWebCross(_webCross);
            return _webCross;
        }

        private WebCross nextWebCross(WebCross webCross){
            WebCross nextWebCross = null;

            if (webCross == null){
                nextWebCross = _web.getWebCross(new WebCrossPosition(0,0));
            }
            else{
                nextWebCross = webCross.neighbour(Direction.east());
                if (nextWebCross == null && webCross.getPosition().row() < _web.getSize()){
                    nextWebCross = _web.getWebCross(new WebCrossPosition(webCross.getPosition().row()+1, 0));
                }
            }

            return nextWebCross;
        }
    }

}
