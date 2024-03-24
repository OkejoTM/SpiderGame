import Setting.*;
import Entities.*;
import org.junit.Test;
import org.junit.Assert;

import java.awt.*;

public class GameTests {
    @Test
    public void ExampleTest(){
        Web web = new Web(3);
        Flora flora = new Flora(web);
        flora.createBotSpiders(1);
        flora.createPlayerSpider();
        web.getBotSpiders().get(0).setWebCross(web.getWebCross(new Point(0,1)));
        web.getWebCross(new Point(0,1)).setAnimal(web.getBotSpiders().get(0));
        web.getBotSpiders().get(0).makeOptimalMove();

    }
}
