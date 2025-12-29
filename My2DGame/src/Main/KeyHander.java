// CodeByConMeoSoMa
// /\_/\  
//( o.o ) 
// > ^ <
package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHander implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed; // bien kiem tra phim di chuyen

    @Override
    public void keyTyped(KeyEvent e) { // phuong thuc xu ly su kien nhan phim
        // Sau khi nhan nhung truoc khi ngon tay ra khoi phim

    }

    @Override
    public void keyPressed(KeyEvent e) { // phuong thuc xu ly su kien nhan phim
        // Ngay sau khi ban nhan xuong phim
        int code = e.getKeyCode(); // lay ma phim vua nhan
        if (code == KeyEvent.VK_W) {
            upPressed = true;

        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {// phuong thuc xu ly su kien tha phim
        // Ngay sau khi ban tha ngon tay ra khoi phim
        int code = e.getKeyCode(); // lay ma phim vua nhan
        if (code == KeyEvent.VK_W) {
            upPressed = false;

        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
    }

}
