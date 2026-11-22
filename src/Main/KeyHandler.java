package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

    public boolean  leftKeyPressed, rightKeyPressed, downKeyPressed, shiftKeyPressed, jumpKeyPressed;
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        int code = e.getKeyCode();
        
        if(code == KeyEvent.VK_A) {
            leftKeyPressed = true;
        }
        if(code == KeyEvent.VK_S) {
            downKeyPressed = true;
        }
        if(code == KeyEvent.VK_D) {
            rightKeyPressed = true;
        }
        if(code == KeyEvent.VK_SHIFT){
            shiftKeyPressed = true;
        }
        if(code == KeyEvent.VK_SPACE){
            jumpKeyPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
        int code = e.getKeyCode();
        
        if(code == KeyEvent.VK_A) {
            leftKeyPressed = false;
        }
        if(code == KeyEvent.VK_S) {
            downKeyPressed = false;
        }
        if(code == KeyEvent.VK_D) {
            rightKeyPressed = false;
        }
        if(code == KeyEvent.VK_SHIFT){
            shiftKeyPressed = false;
        }
        if(code == KeyEvent.VK_SPACE){
            jumpKeyPressed = false;
        }
    }
}