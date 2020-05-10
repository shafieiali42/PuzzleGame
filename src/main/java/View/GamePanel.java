package View;

import Controller.GameController;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GamePanel extends JPanel {

    private static GamePanel panelInstance;

    public static GamePanel getInstance() {
        if (panelInstance == null) {
            panelInstance = new GamePanel();
            return panelInstance;
        }
        return panelInstance;
    }

    private GamePanel() {
        int screenWidth, screenHeight;
        screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        setPreferredSize(new Dimension(screenWidth / 3 - 20, screenHeight / 3 - 20));
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        GameController.getInstance().drawBoard( graphics2D);
    }
}
