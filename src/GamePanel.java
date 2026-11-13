import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    static final int WAITING = 0;
    static final int RUNNING = 1;
    static final int PAUSED = 2;
    static final int GAME_OVER = 3;

    static final int WIDTH = 600;
    static final int HEIGHT = 450;

    int paddleW = 100;
    int paddleH = 14;
    int paddleX;
    int paddleY = HEIGHT - 40;
    int paddleSpeed = 8;

    int ballSize = 20;
    int ballX, ballY;
    int ballDx, ballDy;

    int score = 0;
    int lives = 3;
    volatile int state = WAITING;

    boolean leftPressed = false;
    boolean rightPressed = false;

    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(new Color(20, 0, 0));
        setFocusable(true);
        resetGame();
    }

    void resetGame() {
        score = 0;
        lives = 3;
        paddleX = (WIDTH - paddleW) / 2;
        resetBall();
        state = WAITING;
    }

    void resetBall() {
        ballX = (WIDTH - ballSize) / 2;
        ballY = HEIGHT / 3;
        ballDx = 4;
        ballDy = 4;
        if (Math.random() < 0.5) {
            ballDx = -ballDx;
        }
    }

    void update() {
        if (leftPressed) {
            paddleX -= paddleSpeed;
        }
        if (rightPressed) {
            paddleX += paddleSpeed;
        }
        if (paddleX < 0) {
            paddleX = 0;
        }
        if (paddleX > WIDTH - paddleW) {
            paddleX = WIDTH - paddleW;
        }

        ballX += ballDx;
        ballY += ballDy;

        if (ballX <= 0) {
            ballX = 0;
            ballDx = -ballDx;
        }
        if (ballX >= WIDTH - ballSize) {
            ballX = WIDTH - ballSize;
            ballDx = -ballDx;
        }
        if (ballY <= 0) {
            ballY = 0;
            ballDy = -ballDy;
        }

        if (ballDy > 0
                && ballY + ballSize >= paddleY
                && ballY + ballSize <= paddleY + paddleH + ballDy
                && ballX + ballSize >= paddleX
                && ballX <= paddleX + paddleW) {
            ballDy = -ballDy;
            ballY = paddleY - ballSize;
            score++;
            if (score % 5 == 0 && Math.abs(ballDy) < 12) {
                ballDy = ballDy - 1;
                if (ballDx > 0) {
                    ballDx++;
                } else {
                    ballDx--;
                }
            }
        }

        if (ballY > HEIGHT) {
            lives--;
            if (lives <= 0) {
                lives = 0;
                state = GAME_OVER;
            } else {
                resetBall();
            }
        }
    }

    public void run() {
        while (true) {
            if (state == RUNNING) {
                update();
            }
            repaint();
            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted");
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(new Color(240, 240, 200));
        g.fillRect(paddleX, paddleY, paddleW, paddleH);

        g.setColor(new Color(255, 105, 180));
        g.fillOval(ballX, ballY, ballSize, ballSize);

        g.setColor(Color.WHITE);
        g.setFont(new Font("SansSerif", Font.BOLD, 16));
        g.drawString("Score : " + score, 15, 25);
        g.drawString("Lives : " + lives, WIDTH - 90, 25);

        g.setFont(new Font("SansSerif", Font.BOLD, 30));
        if (state == WAITING) {
            drawCentered(g, "Press START", Color.WHITE);
        } else if (state == PAUSED) {
            drawCentered(g, "PAUSED", Color.YELLOW);
        } else if (state == GAME_OVER) {
            drawCentered(g, "GAME OVER", Color.RED);
            g.setFont(new Font("SansSerif", Font.PLAIN, 16));
            g.setColor(Color.WHITE);
            g.drawString("Final score : " + score + "  -  press RESTART",
                    WIDTH / 2 - 125, HEIGHT / 2 + 30);
        }
    }

    void drawCentered(Graphics g, String text, Color c) {
        g.setColor(c);
        int w = g.getFontMetrics().stringWidth(text);
        g.drawString(text, (WIDTH - w) / 2, HEIGHT / 2);
    }
}
