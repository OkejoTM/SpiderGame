package Setting;

import Events.InsectActionEvent;
import Events.InsectActionListener;
import Interfaces.IPrey;

import java.util.ArrayList;

public abstract class Insect extends Animal implements IPrey {

    protected final int _size;

    public Insect(int health, WebCross webCross, int size) {
        super(health, webCross);
        if (size > 10 || size < 1) throw new IllegalArgumentException("illegal size");
        _size = size;
    }

    @Override
    public void getsEaten() {
        this.die();
    }

    @Override
    protected void die() {
        super.die();
        fireInsectDied();
    }

    @Override
    public int getHealth(){
        return _health * (_size/5);
    }

    public abstract void jumpOff();
    public abstract double getProbabilityDisAppearance();

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

}
