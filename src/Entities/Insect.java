package Entities;

import Events.InsectActionEvent;
import Events.InsectActionListener;
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
        this.die();
    }

    @Override
    public void die() {
        super.die();
        fireInsectDied();
    }

    public abstract void jumpOff();


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

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
