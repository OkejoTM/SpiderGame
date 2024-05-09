import Events.GameActionEvent;
import Events.GameActionListener;
import Setting.Game;
import ui.WebWidget;
import ui.WidgetFactory;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Main {
    public static void main(String[] args){
        SwingUtilities.invokeLater(GamePanel::new);
    }

    static class GamePanel extends JFrame {
        private final JLabel _stepsLabel;
        private final JLabel _insectsLabel;
        private int _steps = 0;
        private int _insects = 0;

        public GamePanel() throws HeadlessException {
            super("Spider Game"); // Заголовок окна

            WidgetFactory _widgetFactory = new WidgetFactory();
            Game _game = new Game(5, Game.GameLevel.MIDDLE);

            _game.addGameActionListener(new GameController());

            JPanel content = (JPanel) this.getContentPane();
            content.setLayout(new BorderLayout()); // Используем BorderLayout для управления компонентами

            // Добавляем JLabel'ы для сводки
            _stepsLabel = new JLabel(new ImageIcon(new ImageIcon("images/steps.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
            _insectsLabel = new JLabel(new ImageIcon(new ImageIcon("images/food.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
            _stepsLabel.setText(String.valueOf(_steps));
            _stepsLabel.setFont(_stepsLabel.getFont().deriveFont(20f));
            _insectsLabel.setText(String.valueOf(_insects));
            _insectsLabel.setFont(_insectsLabel.getFont().deriveFont(20f));

            JPanel summaryPanel = new JPanel();
            summaryPanel.add(_stepsLabel);
            summaryPanel.add(new JLabel("     "));
            summaryPanel.add(_insectsLabel);
            content.add(summaryPanel, BorderLayout.SOUTH);

            // Добавляем паутину
            content.add(new WebWidget(_game.getWeb(), _widgetFactory, _game), BorderLayout.CENTER);

            _widgetFactory.getWidget(_game.getWeb().getPlayer()).requestFocus();

            pack();
            setResizable(false);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setVisible(true); // Делаем окно видимым
        }

        private final class GameController implements GameActionListener {

            @Override
            public void gameEnded(GameActionEvent event) {
                JOptionPane.showMessageDialog(GamePanel.this, "Игра закончена");
                System.exit(0);
            }

            @Override
            public void gameStepHappened(GameActionEvent event) {
                _steps += 1;
                _stepsLabel.setText(String.valueOf(_steps));
                _insectsLabel.setText(String.valueOf(_insects));
            }

            @Override
            public void insectsCreated(GameActionEvent event) {

            }

            @Override
            public void playerAteInsect(GameActionEvent event) {
                _insects +=1 ;
            }

        }
    }

}
