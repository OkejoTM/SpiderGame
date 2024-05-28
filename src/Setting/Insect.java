package Setting;

import Events.InsectActionEvent;
import Events.InsectActionListener;
import Interfaces.IPrey;
import Utils.InsectUtils;

import java.lang.reflect.InvocationTargetException;
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

    public int getSize() {
        return _size;
    }
    public static <T extends Insect> T create(Class<T> insectClass){
        int size = (int) (Math.random() * 10);
        if (Math.round(Math.random() * 10) / 10.0 < InsectUtils.getProbabilityToAppear(insectClass) * (size / 10.0)) {
            try {
                return insectClass.getConstructor(int.class, WebCross.class, int.class).newInstance(InsectUtils.getInitialHealth(insectClass), null, size);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
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
