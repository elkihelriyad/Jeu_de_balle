import javax.swing.*;
import java.awt.event.*;

public class GameController implements ActionListener, KeyListener {

    GamePanel panel;
    JButton startButton;
    JButton pauseButton;
    JButton restartButton;

    public GameController(GamePanel panel, JButton startButton, JButton pauseButton, JButton restartButton) {
        this.panel = panel;
        this.startButton = startButton;
        this.pauseButton = pauseButton;
        this.restartButton = restartButton;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            if (panel.state == GamePanel.WAITING || panel.state == GamePanel.PAUSED) {
                panel.state = GamePanel.RUNNING;
            }
        } else if (e.getSource() == pauseButton) {
            if (panel.state == GamePanel.RUNNING) {
                panel.state = GamePanel.PAUSED;
            }
        } else if (e.getSource() == restartButton) {
            panel.resetGame();
            panel.state = GamePanel.RUNNING;
        }
        panel.requestFocus();
        panel.repaint();
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            panel.leftPressed = true;
        } else if (key == KeyEvent.VK_RIGHT) {
            panel.rightPressed = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            panel.leftPressed = false;
        } else if (key == KeyEvent.VK_RIGHT) {
            panel.rightPressed = false;
        }
    }

    public void keyTyped(KeyEvent e) {
    }
}
