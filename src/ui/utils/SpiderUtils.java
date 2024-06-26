package ui.utils;

import java.awt.*;
import java.awt.image.BufferedImage;

import static ui.cell.WebCrossWidget.CELL_SIZE;

public class SpiderUtils {

    public static Color healthTextColor(int health){
        Color textColor;

        if (health >= 25){
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

    public static BufferedImage spiderWithHealthImage(BufferedImage spiderImage, int spiderHealth){

        BufferedImage img = new BufferedImage(spiderImage.getWidth(), CELL_SIZE, BufferedImage.TYPE_INT_ARGB);
        Graphics g = img.getGraphics();
        g.drawImage(spiderImage, 0, 0, null);

        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.setColor(healthTextColor(spiderHealth));
        drawSpiderHealth(g, spiderHealth);

        return img;
    }

}
