package Utils;

import Entities.Animal;
import Interfaces.IPrey;
import Setting.Web;
import Setting.WebCross;

import java.awt.*;
import java.util.ArrayList;

public class Algorithm {
    private Web _web;

    public Algorithm(Web web){
        _web = web;
    }

    public Direction findDirectionToNearest(WebCross startWebCross){
        ArrayList<IPrey> preyList = _web.getAllPreys();
        if (startWebCross == null || preyList.isEmpty()) return null;

        Point nearestPoint = ((Animal)preyList.get(0)).getWebCross().getPosition();
        double minDistance = startWebCross.getPosition().distance(nearestPoint);

        for (var prey : preyList) {
            var point = ((Animal)prey).getWebCross().getPosition();
            double distance = startWebCross.getPosition().distance(point);
            if (distance < minDistance) {
                minDistance = distance;
                nearestPoint = point;
            }
        }
        Direction direction = getDirection(startWebCross.getPosition(), nearestPoint);
        return direction;

    }

    private Direction getDirection(Point start, Point nearestPoint){
        int deltaX = nearestPoint.x - start.x;
        int deltaY = nearestPoint.y - start.y;

        ArrayList<Direction> directionList = new ArrayList<>();
        if (deltaY > 0){
            directionList.add(Direction.east());
        }
        if (deltaY < 0){
            directionList.add(Direction.west());
        }
        if (deltaX > 0){
            directionList.add(Direction.south());
        }
        if (deltaX < 0){
            directionList.add(Direction.north());
        }
        int randomIndex = (int)(Math.random() * directionList.size());
        return directionList.get(randomIndex);
    }

}
