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
        fireInsectDiedController(this._webCross);
        super.die();
        fireInsectWasEaten();
    }

    @Override
    protected void die() {
        fireInsectDiedController(this._webCross);
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

    // ------------------ Listeners for Widgets -----------------------

    private ArrayList<InsectControllerActionListener> _insectControllerListenersList = new ArrayList<>();

    public void addInsectControllerActionListener(InsectControllerActionListener listener) {
        _insectControllerListenersList.add(listener);
    }

    public void removeInsectControllerActionListener(InsectControllerActionListener listener) {
        _insectControllerListenersList.remove(listener);
    }

    protected void fireInsectDiedController(WebCross from){
        for(InsectControllerActionListener listener : _insectControllerListenersList){
            InsectControllerActionEvent event = new InsectControllerActionEvent(listener);
            event.setInsect(this);
            event.setFrom(from);
            listener.insectDied(event);
        }
    }

}
