package ui.cell;

import Setting.BotSpider;
import ui.utils.GameWidgetsUtils;
import ui.utils.ImageUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static ui.cell.WebCrossWidget.CELL_SIZE;

public class BotSpiderWidget extends WebCrossItemWidget {

    private final BotSpider _botSpider;

    public BotSpiderWidget(BotSpider botSpider) {
        super("images/spider.png", CELL_SIZE / 2, CELL_SIZE - 24);
        _botSpider = botSpider;
    }

    @Override
    protected BufferedImage getImage() {
        return botWithHealth(_image);
    }

    @Override
    protected Dimension getDimension() {
        return new Dimension(CELL_SIZE / 2, CELL_SIZE);
    }

    private BufferedImage botWithHealth(BufferedImage botImage) {
        BufferedImage img = new BufferedImage(botImage.getWidth(), 120, BufferedImage.TYPE_INT_ARGB);
        Graphics g = img.getGraphics();
        g.drawImage(botImage, 0, 0, null);


        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.setColor(GameWidgetsUtils.healthTextColor(_botSpider.getHealth()));
        GameWidgetsUtils.drawSpiderHealth(g, _botSpider.getHealth());


        return img;
    }


}
