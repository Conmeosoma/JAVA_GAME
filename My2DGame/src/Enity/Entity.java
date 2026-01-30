// CodeByConMeoSoMa
// /\_/\  
//( o.o ) 
// > ^ <
package Enity; // 1 goi package de to chuc ma nguon

import Main.GamePanel;
import Main.UtilityTool;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage; // them lop BufferedIamge de xu ly hinh anh
import java.awt.Rectangle;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Entity { // lop Entity chua cac thuoc tinh va phuong thuc chung cho tat ca doi tuong
    // trong game

    GamePanel gp;

    public int World_X, World_Y;// toa do the gioi cua doi tuong

    public int speed;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction = "down";
    // ======= XU LY ANIMATION NHAN VAT =======
    public int spiteCounter = 0;
    public int spiteNum = 1;
    // ========================================

    // XAC DINH VUNG VA CHAM
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);// hinh chu nhat vung va cha
    public boolean collisionOn = false; // bien kiem tra va cham
    // Toa do mac dinh cua vung va cham
    public int solidAreaDefaultX, solidAreaDefaultY;

    public int actionLockCounter = 0;
    String dialogues[] = new String[20];
    String speechBubble[] = new String[20];
    int dialogueIndex = 0;
    int speechBubbleIndex = 0;

    // SPEECH BUBBLE
    public boolean isSpeaking = false;
    public int speechBubbleCounter = 0;
    public int speechBubbleDuration = 200; // 200 frames ~3.3 seconds

    public BufferedImage image, image2, image3; // hinh anh cua doi tuong
    public String name;
    public boolean collision = false;

    // CHARATER STATUS
    public int maxLife;
    public int life;

    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public void setAction() {
    }

    public void speak() {
        // dialogues
        if (dialogues[dialogueIndex] == null) {
            dialogueIndex = 0;
        }
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;
        // speechBubble
        if (speechBubble[speechBubbleIndex] == null) {
            speechBubbleIndex = 0;
        }
        gp.ui.speechBubbleText = speechBubble[speechBubbleIndex];
        speechBubbleIndex++;

        switch (gp.player.direction) {
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
        }
    }

    // SPEECH BUBBLE METHOD
    public void showSpeechBubble(String text) {
        isSpeaking = true;
        speechBubbleCounter = 0;
        gp.ui.setSpeechBubble(text, this);
    }

    public void update() {

        setAction();

        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkPlayer(this);

        if (collisionOn == false) {
            switch (direction) {
                case "up" ->
                    World_Y -= speed;// di chuyen len tren
                case "down" ->
                    World_Y += speed; // di chuyen xuong duoi
                case "left" ->
                    World_X -= speed; // di chuyen sang trai
                case "right" ->
                    World_X += speed; // di chuyen sang phai
            }
        }

        // tang 4 pixel sau moi lan update neu phim duoc nhan
        // xu ly aniamtion nhan vat
        spiteCounter++;
        if (spiteCounter > 12) {// moi 12 lan update thi doi hinh
            if (spiteNum == 1) { // neu spitenum = 1 thi dat spitenum = 2 de lan sau ve hinh 2
                spiteNum = 2;
            } else if (spiteNum == 2) { // neu spitenum = 2 thi dat spitenum = 1 de lan sau ve hinh 1
                spiteNum = 1;
            }
            spiteCounter = 0;
        }

        // UPDATE SPEECH BUBBLE
        if (isSpeaking) {
            speechBubbleCounter++;
            if (speechBubbleCounter >= speechBubbleDuration) {
                isSpeaking = false;
                speechBubbleCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        int screenX = World_X - gp.player.World_X + gp.player.screenX;
        // tọa độ cam màn hình X = tọa độ thế giới X - tọa độ thế giới nhân vật X + tọa
        // độ

        int screenY = World_Y - gp.player.World_Y + gp.player.screenY;
        // tọa độ cam màn hình Y = tọa độ thế giới Y - tọa độ thế giới nhân vật Y + tọa
        // độ
        if (World_X + gp.tileSize > gp.player.World_X - gp.player.screenX
                && World_X - gp.tileSize < gp.player.World_X + gp.player.screenX
                && World_Y + gp.tileSize > gp.player.World_Y - gp.player.screenY
                && World_Y - gp.tileSize < gp.player.World_Y + gp.player.screenY) {

            switch (direction) {
                case "up":// neu huong di chuyen la len tren thi ve hinh len tren
                    // neu spitenulm = 1 thi ve hinh up1 va dat spiteNum = 2 de lan sau ve hinh up2
                    if (spiteNum == 1) {
                        image = up1;
                    }
                    if (spiteNum == 2) {
                        image = up2;
                    }

                    break;
                case "down": // neu huong di chuyen la xuong duoi thi ve hinh xuong duoi
                    // neu spitenum = 1 thi ve hinh up1 va dat spiteNum = 2 de lan sau ve hinh up2
                    if (spiteNum == 1) {
                        image = down1;
                    }
                    if (spiteNum == 2) {
                        image = down2;

                    }
                    break;
                case "left": // neu huong di chuyen la trai thi ve hinh trai
                    if (spiteNum == 1) { // neu spitenum = 1 thi ve hinh left1 va dat spiteNum = 2 de lan sau ve hinh
                        // left2
                        image = left1;
                    }
                    if (spiteNum == 2) {
                        image = left2;
                    }
                    break;
                case "right":// neu huong di chuyen la phai thi ve hinh phai
                    if (spiteNum == 1) {
                        image = right1;
                    }
                    if (spiteNum == 2) {
                        image = right2;
                    }
                    break;
                default:
                    break;
            }

            g2.drawImage(image, screenX, screenY, gp.tileSize,
                    gp.tileSize, null);
        }
    }

    public BufferedImage setup(String imagePath) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
