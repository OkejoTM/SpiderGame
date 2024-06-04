package ui.cell;

import Setting.BotSpider;
import ui.utils.SpiderUtils;
import java.awt.image.BufferedImage;

import static ui.cell.WebCrossWidget.CELL_SIZE;

/**
 * Класс виджета Паука-Бота
 */
public class BotSpiderWidget extends AnimalWidget {

    private final BotSpider _botSpider;

    public BotSpiderWidget(BotSpider botSpider) {
        super("images/bot.png", CELL_SIZE / 2, CELL_SIZE - 24);
        _botSpider = botSpider;

    }

    @Override
    protected BufferedImage getImage() {
        return SpiderUtils.spiderWithHealthImage(super.getImage(), _botSpider.getHealth());
    }
}
