import Setting.*;

public class Game {
    private Web _web;
    private Flora _flora;

    public Game(){

    }

    public void startGame(){
        _web = new Web(8);
        _flora = new Flora(_web);
    }

    public void endGame(){

    }

    public void createWeb(int size){
        _web = new Web(size);
    }

    public boolean moveAllBots(){

        return false;
    }
}
