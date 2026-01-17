// CodeByConMeoSoMa
// /\_/\  
//( o.o ) 
// > ^ <
package Enity;

import java.awt.Graphics2D;
import java.io.IOException;
import javax.imageio.ImageIO;
import Main.GamePanel;
import Main.KeyHandler;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    public int hasKey = 0; // bien kiem tra so luong khoa ma nguoi choi co duoc

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        // cho player luon luon o giua man hinh
        // tuy nhien neu screenX, screenY / 2 thi tâm nó sẽ tính ở góc trên bên trái
        // nếu muốn xử lý nó là tâm điểm ảnh thì phải lấy chiều rộng trừ đi kích thước
        // nhân vật rôi chia 2

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle();
        solidArea.x = 8; // vung va cham bat dau tu 8 pixel ben pphai
        solidArea.y = 16; // vung va cham bat dau tu 16 pixel ben duoi
        solidArea.width = 32; // chieu rong vung va cham
        solidArea.height = 32; // chieu cao vung va cham
        solidAreaDefaultX = solidArea.x; // luu toa do mac dinh
        solidAreaDefaultY = solidArea.y; // luu toa do mac dinh

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        World_X = gp.tileSize * 23;
        World_Y = gp.tileSize * 21;
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
        // My2DGame\src\res\Player\Walking_sprites\boy_down_1.png
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/res/Player/Walking_sprites/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/Player/Walking_sprites/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/Player/Walking_sprites/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/Player/Walking_sprites/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/Player/Walking_sprites/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/Player/Walking_sprites/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/Player/Walking_sprites/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/Player/Walking_sprites/boy_right_2.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void update() {
        // chi di chuyen neu co phim duoc nhan
        if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true
                || keyH.rightPressed == true) {

            if (keyH.upPressed == true) {
                direction = "up"; // set huong quay len tren
                // World_Y -= speed;// di chuyen len tren
            } else if (keyH.downPressed == true) {
                direction = "down"; // set
                // World_Y += speed; // di chuyen xuong duoi
            } else if (keyH.leftPressed == true) {
                direction = "left";
                // World_X -= speed; // di chuyen sang trai
            } else if (keyH.rightPressed == true) {
                direction = "right";
                // World_X += speed; // di chuyen sang phai
            }

            // KIEM TRA TILE VA CHAM
            collisionOn = false;
            gp.cChecker.checkTile(this);
            // KIEM TRA VA CHAM OBJECT
            int objIndex = gp.cChecker.checkObject(this, true);
            if (objIndex != 999) {
                pickUpObject(objIndex);
            }

            // NEU VA CHAM LA FALSE, PLAYER CO THE DI CHUYEN
            if (collisionOn == false) {
                switch (direction) {
                    case "up" -> World_Y -= speed;// di chuyen len tren
                    case "down" -> World_Y += speed; // di chuyen xuong duoi
                    case "left" -> World_X -= speed; // di chuyen sang trai
                    case "right" -> World_X += speed; // di chuyen sang phai
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
        }

    }

    public void pickUpObject(int i) {
        if (i != 999) {
            // gp.obj[i] = null; // xoa object khoi mang neu da duoc nhan
            String objectName = gp.obj[i].name;
            switch (objectName) {
                case "Key":
                    hasKey++;
                    gp.obj[i] = null; // xoa object khoi mang neu da duoc nhan
                    System.out.println("So khoa hien tai: " + hasKey);
                    break;
                case "Door":
                    if (hasKey > 0) {
                        gp.obj[i] = null; // xoa object khoi mang neu da duoc nhan
                        hasKey--; // giam so khoa sau khi mo cua
                        System.out.println("So khoa hien tai: " + hasKey);
                    }

                default:
                    break;
            }
        }
    }

    public void draw(Graphics2D g2) {
        // g2.setColor(Color.white); // dat mau ve la trang
        // g2.fillRect(100, 100, tileSize, tileSize);// ve hinh chu nhat o vi tri
        // (100,100) voi kich thuoc tileSize * tileSize
        // g2.fillRect(x, y, gp.tileSize, gp.tileSize); // ve hinh chu nhat o vi tri
        // (playerX,playerY) voi kich
        // thuoctileSize * tileSize
        BufferedImage image = null;
        // xu ly animation nhan vat
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
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);// ve hinh anh nhan vat o vi tri

    }

}
