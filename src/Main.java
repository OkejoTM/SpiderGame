import Events.GameActionEvent;
import Events.GameActionListener;
import Setting.Flora;
import Setting.Game;
import Utils.Direction;
import ui.WebWidget;
import ui.WidgetFactory;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args){
//        Web web = new Web(4);
//        Flora flora = new Flora();
//        flora.setWeb(web);
//        flora.createPlayerSpider(2);
//        web.getPlayer().makeMove(Direction.north());
//

//
//        Game game = new Game(3);
//        game.getWeb().getPlayer().makeMove(Direction.north());
//        game.getWeb().getPlayer().makeMove(Direction.east());
//        game.getWeb().getPlayer().makeMove(Direction.west());

        SwingUtilities.invokeLater(GamePanel::new);
    }

    static class GamePanel extends JFrame {
        private Game _game;
        public WidgetFactory _widgetFactory;

        public GamePanel() throws HeadlessException {
            setVisible(true);

            _widgetFactory = new WidgetFactory();
            _game = new Game(5);

            _game.addGameActionListener(new GameController());

            JPanel content = (JPanel) this.getContentPane();
            content.add(new WebWidget(_game.getWeb(), _widgetFactory, _game));

            _widgetFactory.getWidget(_game.getWeb().getPlayer()).requestFocus();

            pack();
            setResizable(false);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
        }

        private final class GameController implements GameActionListener {

            @Override
            public void gameEnded(GameActionEvent event) {
                JOptionPane.showMessageDialog(GamePanel.this, "Игра закончена");
            }

            @Override
            public void gameStepHappened(GameActionEvent event) {

            }

            @Override
            public void insectsCreated(GameActionEvent event) {

            }

        }
    }

}
