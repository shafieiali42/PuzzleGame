package View;

import Controller.GameController;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyKeyListener implements KeyListener {


    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

        if (keyEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
            GameController.getInstance().moveRight();
        } else if (keyEvent.getKeyCode() == KeyEvent.VK_LEFT) {
            GameController.getInstance().moveLeft();
        } else if (keyEvent.getKeyCode() == KeyEvent.VK_UP) {
            GameController.getInstance().moveUp();
        } else if (keyEvent.getKeyCode() == KeyEvent.VK_DOWN) {
            GameController.getInstance().moveDown();
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}