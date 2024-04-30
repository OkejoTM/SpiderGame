package ui.utils;

import java.awt.*;

public class GameWidgetsUtils {

    public static Color healthTextColor(int health){
        Color textColor;

        if (health > 25){
            textColor = Color.GREEN;
        }
        else if (health > 10 && health <= 25){
            textColor = Color.ORANGE;
        }
        else {
            textColor = Color.RED;
        }

        return textColor;
    }
}
