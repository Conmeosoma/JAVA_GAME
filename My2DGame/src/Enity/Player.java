// CodeByConMeoSoMa
// /\_/\  
//( o.o ) 
// > ^ <
package Enity;

import java.awt.Graphics2D;

import Main.GamePanel;
import Main.KeyHander;
import java.awt.Color;

public class Player extends Entity {

    GamePanel gp;
    KeyHander keyH;

    public Player(GamePanel gp, KeyHander keyH) {
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
    }

    public void update() {
        if (keyH.upPressed == true) {
            y -= speed;// di chuyen len tren
        } else if (keyH.downPressed == true) {
            y += speed; // di chuyen xuong duoi
        } else if (keyH.leftPressed == true) {
            x -= speed; // di chuyen sang trai
        } else if (keyH.rightPressed == true) {
            x += speed; // di chuyen sang phai
        }
        // tang 4 pixel sau moi lan update neu phim duoc nhan

    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.white); // dat mau ve la trang
        // g2.fillRect(100, 100, tileSize, tileSize);// ve hinh chu nhat o vi tri
        // (100,100) voi kich thuoc tileSize * tileSize
        g2.fillRect(x, y, gp.tileSize, gp.tileSize); // ve hinh chu nhat o vi tri (playerX,playerY) voi kich
        // thuoctileSize * tileSize

    }

}
