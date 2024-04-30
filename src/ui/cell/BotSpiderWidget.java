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
        return GameWidgetsUtils.spiderWithHealthImage(_image, _botSpider.getHealth());
    }

    @Override
    protected Dimension getDimension() {
        return new Dimension(CELL_SIZE / 2, CELL_SIZE);
    }

}
