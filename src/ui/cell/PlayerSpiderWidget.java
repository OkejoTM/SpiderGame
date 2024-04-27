package ui.cell;

import Setting.PlayerSpider;
import Utils.Direction;
import ui.utils.ImageUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PlayerSpiderWidget extends WebCrossItemWidget {

    private final PlayerSpider _playerSpider;

    public PlayerSpiderWidget(PlayerSpider playerSpider) {
        super();
        _playerSpider = playerSpider;
        setFocusable(true);
        addKeyListener(new KeyController());
    }

    @Override
    protected BufferedImage getImage() {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("images/playerSpider.png"));
            image = ImageUtils.resizeImage(image, 60, 96);
            image = playerImageWithHealth(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    @Override
    public WebCrossWidget.Layer getLayer() {
        return WebCrossWidget.Layer.BOTTOM;
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
            g.drawString("[" + _playerSpider.getHealth() + "]", 5, 112);
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

            repaint();
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
