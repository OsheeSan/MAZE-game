package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Коли якась клавіша натискнута, то виконується якийсь рух
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W){
            upPressed = true;
        }
        if (code == KeyEvent.VK_A){
            leftPressed = true;

        }
        if (code == KeyEvent.VK_S){
            downPressed = true;

        }
        if (code == KeyEvent.VK_D){
            rightPressed = true;

        }
    }

    /**
     * Коли кнопка не натискнута, нічого не робимо
     * @param e
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W){
            upPressed = false;
        }
        if (code == KeyEvent.VK_A){
            leftPressed = false;

        }
        if (code == KeyEvent.VK_S){
            downPressed = false;

        }
        if (code == KeyEvent.VK_D){
            rightPressed = false;

        }
    }
}
