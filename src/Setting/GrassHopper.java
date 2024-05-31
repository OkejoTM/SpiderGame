package Setting;

import Events.Controllers.GrassHopperControllerActionEvent;
import Events.Controllers.GrassHopperControllerActionListener;
import Utils.Direction;

import java.util.*;

import static java.lang.Math.max;

public class GrassHopper extends Insect{
    public static final double _probabilityOfDisappearance = 0.4;
    public static final double _probabilityOfAppearance = 0.6;
    private double _probabilityToJump = 0.6;

    public GrassHopper(int health, WebCross webCross, int size) {
        super(health, webCross, size);
    }

    @Override
    public void jumpOff() {
        super.jumpOff();
        if (Math.round(Math.random() * 10)/10.0 <= _probabilityToJump)
        {
            jumpToOtherWebCross();
            _probabilityToJump = max(0, _probabilityToJump - 0.2);
        }
    }

    protected void jumpToOtherWebCross(){
        WebCross webCross = findEmptyWebCross();
        if (webCross != null){
            WebCross oldWebCross = this.getWebCross();
            webCross.setAnimal(this);
            fireGrassHopperJumpedController(oldWebCross, webCross);
        }
    }

    private WebCross findEmptyWebCross(){
        if (_webCross == null) return null;
        Set<WebCross> visited = new HashSet<>();
        Queue<WebCross> queue = new LinkedList<>();

        queue.add(_webCross);
        visited.add(_webCross);

        List<Direction> directions = new ArrayList<>(Arrays.asList(Direction.east(), Direction.west(), Direction.south(), Direction.north()));

        while(!queue.isEmpty()){
            WebCross current = queue.poll();
            if (!current.isOccupied())
                return current;

            for (Direction direction : directions){
                WebCross neighbour = current.neighbour(direction);
                if (neighbour != null && !visited.contains(neighbour)){
                    queue.add(neighbour);
                    visited.add(neighbour);
                }
            }
        }
        return null;
    }

    @Override
    public double getProbabilityDisAppearance() {
        return _size/10.0 * _probabilityOfDisappearance;
    }

    // Listeners

    private ArrayList<GrassHopperControllerActionListener> _grassHopperListeners = new ArrayList<>();

    public void addGrassHopperActionListener(GrassHopperControllerActionListener listener) {
        _grassHopperListeners.add(listener);
    }

    public void removeGrassHopperActionListener(GrassHopperControllerActionListener listener) {
        _grassHopperListeners.remove(listener);
    }

    protected void fireGrassHopperJumpedController(WebCross from, WebCross to){
        for(GrassHopperControllerActionListener listener : _grassHopperListeners){
            GrassHopperControllerActionEvent event = new GrassHopperControllerActionEvent(listener);
            event.setTo(to);
            event.setFrom(from);
            event.setGrassHopper(this);
            listener.jumpedTo(event);
        }
    }

}
