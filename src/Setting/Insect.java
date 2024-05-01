package Setting;

import Events.*;
import Interfaces.IPrey;
import Setting.Animal;
import Setting.WebCross;

import java.util.ArrayList;

public abstract class Insect extends Animal implements IPrey {

    public Insect(int health, WebCross webCross) {
        super(health, webCross);

    }

    @Override
    public void getsEaten() {
        super.die();
        fireInsectWasEaten();
    }

    @Override
    protected void die() {
        super.die();
        fireInsectDied();
    }

    public abstract void jumpOff();

    // ------------------ Listeners for Game -----------------------

    private ArrayList<InsectActionListener> _insectListenersList = new ArrayList<>();

    public void addInsectActionListener(InsectActionListener listener) {
        _insectListenersList.add(listener);
    }

    public void removeInsectActionListener(InsectActionListener listener) {
        _insectListenersList.remove(listener);
    }

    protected void fireInsectDied(){
        for(InsectActionListener listener : _insectListenersList){
            InsectActionEvent event = new InsectActionEvent(listener);
            event.setInsect(this);
            listener.insectDied(event);
        }
    }

    protected void fireInsectWasEaten(){
        for(InsectActionListener listener : _insectListenersList){
            InsectActionEvent event = new InsectActionEvent(listener);
            event.setInsect(this);
            listener.insectWasEaten(event);
        }
    }

}
