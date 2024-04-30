package ui.cell;

import Setting.PlayerSpider;
import Utils.Direction;
import ui.utils.GameWidgetsUtils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class PlayerSpiderWidget extends WebCrossItemWidget {

    private final PlayerSpider _playerSpider;

    public PlayerSpiderWidget(PlayerSpider playerSpider) {
        super("images/playerSpider.png", 60, 96);
        _playerSpider = playerSpider;
        setFocusable(true);
        addKeyListener(new KeyController());
    }

    @Override
    protected BufferedImage getImage() {
        return playerImageWithHealth(_image);
    }

    @Override
    protected Dimension getDimension() {
        return new Dimension(60, 120);
    }

    private BufferedImage playerImageWithHealth(BufferedImage playerImage) {
        BufferedImage img = new BufferedImage(playerImage.getWidth(), 120, BufferedImage.TYPE_INT_ARGB);
        Graphics g = img.getGraphics();
        g.drawImage(playerImage, 0, 0, null);

        if (cellItemState == State.DEFAULT){
            g.setFont(new Font("Arial", Font.PLAIN, 20));
            g.setColor(GameWidgetsUtils.healthTextColor(_playerSpider.getHealth()));
            GameWidgetsUtils.drawSpiderHealth(g, _playerSpider.getHealth());
        }

        return img;
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
