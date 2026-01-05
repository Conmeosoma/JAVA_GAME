// CodeByConMeoSoMa
// /\_/\  
//( o.o ) 
// > ^ <
package Enity;

import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

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
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
        direction = "up";
        direction = "left";
        direction = "right";
    }

    public void getPlayerImage() {
        // load hinh anh nhan vat o day
        // cho 1 hinh vao 1 bien BufferedImage
        // su dung try catch de bat loi neu co
        // gan bien up1 bang hinh anh duoc tai ve

        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/img.player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/img.player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/img.player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/img.player/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/img.player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/img.player/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/img.player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/img.player/boy_right_2.png"));

        } catch (IOException e) {
            // bo qua loi in ra man hinh

            e.printStackTrace();
        }
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void update() {
        if (keyH.upPressed == true) {
            direction = "up"; // set huong quay len tren
            y -= speed;// di chuyen len tren
        } else if (keyH.downPressed == true) {
            direction = "down"; // set
            y += speed; // di chuyen xuong duoi
        } else if (keyH.leftPressed == true) {
            direction = "left";
            x -= speed; // di chuyen sang trai
        } else if (keyH.rightPressed == true) {
            direction = "right";
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
