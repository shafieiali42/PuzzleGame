package View;

import Controller.GameController;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class MyKeyListener implements KeyListener {


    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

        if (keyEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
            try {
                GameController.getInstance().moveRight();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (keyEvent.getKeyCode() == KeyEvent.VK_LEFT) {
            try {
                GameController.getInstance().moveLeft();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (keyEvent.getKeyCode() == KeyEvent.VK_UP) {
            try {
                GameController.getInstance().moveUp();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (keyEvent.getKeyCode() == KeyEvent.VK_DOWN) {
            try {
                GameController.getInstance().moveDown();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}