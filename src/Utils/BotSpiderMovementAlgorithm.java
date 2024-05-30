package Utils;

import Setting.Animal;
import Interfaces.IPrey;
import Setting.Web;
import Setting.WebCross;
import java.awt.*;
import java.util.ArrayList;

public class BotSpiderMovementAlgorithm {
    private Web _web;

    public BotSpiderMovementAlgorithm(Web web) {
        _web = web;
    }

    public Direction findDirectionToNearest(WebCross startWebCross) {
        ArrayList<IPrey> preyList = _web.getAllPreys();
        if (startWebCross == null || preyList.isEmpty()) return null;

        WebCrossPosition webCrossPosition = ((Animal) preyList.get(0)).getWebCross().getPosition();
        Point nearestPoint = new Point(webCrossPosition.row(), webCrossPosition.column());

        WebCrossPosition startWebCrossPosition = startWebCross.getPosition();
        Point startPosition = new Point(startWebCrossPosition.row(), startWebCrossPosition.column());
        double minDistance = startPosition.distance(nearestPoint);

        for (IPrey prey : preyList) {
            WebCrossPosition preyPosition = ((Animal) prey).getWebCross().getPosition();
            Point point = new Point(preyPosition.row(), preyPosition.column());
            double distance = startPosition.distance(point);
            if (distance < minDistance) {
                minDistance = distance;
                nearestPoint = point;
            }
        }
        return getDirection(startPosition, nearestPoint);
    }

    private Direction getDirection(Point start, Point nearestPoint) {
        int deltaX = nearestPoint.x - start.x;
        int deltaY = nearestPoint.y - start.y;

        ArrayList<Direction> directionList = new ArrayList<>();
        if (deltaY > 0) {
            directionList.add(Direction.east());
        }
        if (deltaY < 0) {
            directionList.add(Direction.west());
        }
        if (deltaX > 0) {
            directionList.add(Direction.south());
        }
        if (deltaX < 0) {
            directionList.add(Direction.north());
        }
        int randomIndex = (int) (Math.random() * directionList.size());
        return directionList.get(randomIndex);
    }
}
