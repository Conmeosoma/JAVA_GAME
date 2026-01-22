/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Enity;

import Main.GamePanel;
import java.util.Random;
//import Main.UtilityTool;
//import java.awt.image.BufferedImage;
//import java.io.IOException;
//import javax.imageio.ImageIO;

/**
 *
 * @author dieu hoang
 */
public class NPC_OldMan extends Entity{
    
    public NPC_OldMan(GamePanel gp) {
        super(gp);
        direction = "down";
        speed = 1;
        
        getImage();
    }
    
    public void getImage() {
        up1 = setup("/res/NPC/oldman_up_1");
        up2 = setup("/res/NPC/oldman_up_2");
        down1 = setup("/res/NPC/oldman_down_1");
        down2 = setup("/res/NPC/oldman_down_2");
        left1 = setup("/res/NPC/oldman_left_1");
        left2 = setup("/res/NPC/oldman_left_2");
        right1 = setup("/res/NPC/oldman_right_1");
        right2 = setup("/res/NPC/oldman_right_2");

    }

//    public BufferedImage setup(String imageName) {
//        UtilityTool uTool = new UtilityTool();
//        BufferedImage image = null;
//
//        try {
//            image = ImageIO.read(getClass().getResourceAsStream("/res/Player/Walking_sprites/" + imageName + ".png"));
//            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return image;
//    }
    public void setAction(){
        actionLockCounter++;
        
        if (actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1; // ngau nhien tu 1 -> 100

            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50){
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75 && i <= 100) {
                direction = "right";
            } 
        // simplest AI ever
            actionLockCounter = 0; 

        }
    }
}
