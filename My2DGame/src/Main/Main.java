package Main;

import javax.swing.*;

public class Main {

    public static JFrame window;

    public static void main(String[] args) {
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false); // Cant Resizable
        window.setTitle("Blue Boy Adventure\n"); // Window Name
        new Main().setIcon();
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        gamePanel.config.loadConfig();
        if (gamePanel.fullScreenOn == true) {
            window.setUndecorated(true);
        }

        window.pack(); // Resizes to prefered size and prevents overflow.

        window.setLocationRelativeTo(null); // Starts center of screen
        window.setVisible(true);

        gamePanel.setupGame(); // Setting up the game before starts
        gamePanel.startGameThread();
    }

    public void setIcon() {
        java.net.URL url = getClass().getResource("/res/player/boy_down_1.png");
        if (url == null) {
            url = getClass().getClassLoader().getResource("res/player/boy_down_1.png");
        }
        if (url != null) {
            ImageIcon icon = new ImageIcon(url);
            window.setIconImage(icon.getImage());
        } else {
            System.err.println("Warning: icon resource not found: /res/player/boy_down_1.png");
        }
    }
}
