import javax.swing.*;
import java.awt.*;

public class JeuDeBalle extends JFrame {

    GamePanel panel = new GamePanel();
    JButton startButton = new JButton("START");
    JButton pauseButton = new JButton("PAUSE");
    JButton restartButton = new JButton("RESTART");

    public JeuDeBalle() {
        super("Projet 2 : Jeu de balle");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        GameController controller = new GameController(panel, startButton, pauseButton, restartButton);
        panel.addKeyListener(controller);

        JPanel buttons = new JPanel();
        buttons.setBackground(new Color(40, 0, 0));
        startButton.setFocusable(false);
        pauseButton.setFocusable(false);
        restartButton.setFocusable(false);
        startButton.addActionListener(controller);
        pauseButton.addActionListener(controller);
        restartButton.addActionListener(controller);
        buttons.add(startButton);
        buttons.add(pauseButton);
        buttons.add(restartButton);

        JPanel credits = new JPanel(new GridLayout(3, 1));
        credits.setBackground(new Color(40, 0, 0));
        credits.add(makeLabel("Développé par : EL KIHEL Riyad et HALOUBI Ahmed"));
        credits.add(makeLabel("Encadré par: M. Mustapha OUJAOURA - ENSA Safi"));

        JPanel south = new JPanel(new BorderLayout());
        south.add(buttons, BorderLayout.NORTH);
        south.add(credits, BorderLayout.SOUTH);

        add(panel, BorderLayout.CENTER);
        add(south, BorderLayout.SOUTH);

        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        panel.requestFocus();

        Thread t = new Thread(panel);
        t.start();
    }

    JLabel makeLabel(String text) {
        JLabel label = new JLabel(text, JLabel.CENTER);
        label.setForeground(Color.LIGHT_GRAY);
        label.setFont(new Font("SansSerif", Font.PLAIN, 11));
        return label;
    }

    public static void main(String[] args) {
        new JeuDeBalle();
    }
}
