package Controller;

import Controller.GameController;
import View.GamePanel;
import View.MyMainFrame;

import javax.swing.*;
import java.awt.*;

public class GraphicLoop implements Runnable {

    private Thread gameThread;

    public GraphicLoop() {
        gameThread = new Thread(this);
    }

    public void start() {
        gameThread.start();
    }

    @Override
    public void run() {
        MyMainFrame myMainFrame = new MyMainFrame();
        while (true) {
            try {
                gameThread.sleep(1000 / 60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            GamePanel.getInstance().setPreferredSize(
                    new Dimension(myMainFrame.getSize().width - 20, myMainFrame.getSize().height - 20));

            GamePanel.getInstance().repaint();
            myMainFrame.repaint();

            if (GameController.getInstance().isGameFinished()) {
                break;
            }
            if (GameController.getInstance().getGameState().equals("finished")) {
                JOptionPane.showMessageDialog(null, "You finished the game, congratulation", "game finished", JOptionPane.INFORMATION_MESSAGE);
                GameController.getInstance().setGameFinished(true);
            }

        }
    }
}
