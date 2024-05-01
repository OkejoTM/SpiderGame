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
        SwingUtilities.invokeLater(GamePanel::new);
    }

    static class GamePanel extends JFrame {
        private WidgetFactory _widgetFactory;
        private JLabel _stepsLabel;
        private JLabel _insectsLabel;
        private int _steps = 0;
        private int _insects = 0;

        public GamePanel() throws HeadlessException {
            super("Spider Game"); // Заголовок окна

            _widgetFactory = new WidgetFactory();
            Game _game = new Game(5, Game.GameLevel.MIDDLE);

            _game.addGameActionListener(new GameController());

            JPanel content = (JPanel) this.getContentPane();
            content.setLayout(new BorderLayout()); // Используем BorderLayout для управления компонентами

            // Добавляем JLabel'ы для сводки
            _stepsLabel = new JLabel("Количество шагов: ");
            _insectsLabel = new JLabel("Съедено насекомых: ");
            JPanel summaryPanel = new JPanel();
            summaryPanel.add(_stepsLabel);
            summaryPanel.add(_insectsLabel);
            content.add(summaryPanel, BorderLayout.NORTH);

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
                _stepsLabel.setText("Количество шагов: " + _steps);
                _insectsLabel.setText("Съедено насекомых: " + _insects);
            }

            @Override
            public void insectsCreated(GameActionEvent event) {

            }

        }
    }

}
