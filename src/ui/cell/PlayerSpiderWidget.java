package ui.cell;

import Setting.PlayerSpider;
import Utils.Direction;
import ui.utils.GameWidgetsUtils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import static ui.cell.WebCrossWidget.CELL_SIZE;

public class PlayerSpiderWidget extends WebCrossItemWidget {

    private final PlayerSpider _playerSpider;

    public PlayerSpiderWidget(PlayerSpider playerSpider) {
        super("images/playerSpider.png", CELL_SIZE / 2, CELL_SIZE - 24);
        _playerSpider = playerSpider;
        setFocusable(true);
        addKeyListener(new KeyController());
    }

    @Override
    protected BufferedImage getImage() {
        return GameWidgetsUtils.spiderWithHealthImage(_image, _playerSpider.getHealth());
    }

    @Override
    protected Dimension getDimension() {
        return new Dimension(CELL_SIZE / 2, CELL_SIZE);
    }

    private class KeyController implements KeyListener {

        @Override
        public void keyTyped(KeyEvent arg0) {
        }

        @Override
        public void keyPressed(KeyEvent ke) {
            int keyCode = ke.getKeyCode();

            moveAction(keyCode);

        }

        @Override
        public void keyReleased(KeyEvent arg0) {
        }

        private void moveAction(int keyCode) {
            Direction direction = directionByKeyCode(keyCode);
            if (direction != null) {
                _playerSpider.makeMove(direction);
                requestFocus();
            }
        }

        private Direction directionByKeyCode(int keyCode) {
            Direction direction = null;
            switch (keyCode) {
                case KeyEvent.VK_UP:
                    direction = Direction.north();
                    break;
                case KeyEvent.VK_DOWN:
                    direction = Direction.south();
                    break;
                case KeyEvent.VK_LEFT:
                    direction = Direction.west();
                    break;
                case KeyEvent.VK_RIGHT:
                    direction = Direction.east();
                    break;
            }
            return direction;
        }
    }

}
