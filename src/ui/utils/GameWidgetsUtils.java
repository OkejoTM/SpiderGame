package ui.utils;

import java.awt.*;

public class GameWidgetsUtils {

    public static Color healthTextColor(int health){
        Color textColor;

        if (health > 25){
            textColor = Color.GREEN;
        }
        else if (health >= 10){
            textColor = Color.ORANGE;
        }
        else {
            textColor = Color.RED;
        }

        return textColor;
    }

    public static void drawSpiderHealth(Graphics g, int health){
        if (health >= 100){
            g.drawString("[" + health + "]", 5, 112);
        }
        else if (health >= 10){
            g.drawString("[" + health + "]", 10, 112);
        }
        else{
            g.drawString("[" + health + "]", 17, 112);
        }
    }

}
