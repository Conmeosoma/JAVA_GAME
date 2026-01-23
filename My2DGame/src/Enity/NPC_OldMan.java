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
public class NPC_OldMan extends Entity {

    public NPC_OldMan(GamePanel gp) {
        super(gp);
        direction = "down";
        speed = 1;

        getImage();
        setDialogue();
        setSpeechBubble();
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

    // public BufferedImage setup(String imageName) {
    // UtilityTool uTool = new UtilityTool();
    // BufferedImage image = null;
    //
    // try {
    // image =
    // ImageIO.read(getClass().getResourceAsStream("/res/Player/Walking_sprites/" +
    // imageName + ".png"));
    // image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
    //
    // } catch (IOException e) {
    // e.printStackTrace();
    // }
    // return image;
    // }
    public void setAction() {
        actionLockCounter++;

        if (actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1; // ngau nhien tu 1 -> 100

            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
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

    // Random speech bubble feature
    @Override
    public void update() {
        super.update();
        updateSpeechBubble();
    }

    public void updateSpeechBubble() {
        if (actionLockCounter == 60 || actionLockCounter == 80) { // Speak at random times
            int speechIndex = new Random().nextInt(speechBubble.length);
            if (speechBubble[speechIndex] != null) {
                String shortSpeechBubble = speechBubble[speechIndex];
                // Limit text length for bubble
                if (shortSpeechBubble.length() > 15) {
                    shortSpeechBubble = shortSpeechBubble.substring(0, 15) + "...";
                }
                showSpeechBubble(shortSpeechBubble);
            }
        }
    }

    public void setDialogue() {
        dialogues[0] = "Xin chào, HITer!";
        dialogues[1] = "Vậy phải chăng bạn đến đây để \ntìm kho báu chứa cách lập trình mượt mà và không bug?";
        dialogues[2] = "Ta đã từng là một lập trình viên vĩ đại nhưng hiện tại.. \nTa đã quá già để đi tìm kho báu rồi.";
        dialogues[3] = "Chà, chúc !may mắn nhé!";
    }

    public void setSpeechBubble() {

        speechBubble[0] = "@kdieu4";
        speechBubble[1] = "@MeoConSoMa";
        speechBubble[2] = " MTP";
        speechBubble[3] = " Hiếu thứ 2";
        speechBubble[4] = "SOBIN";
        speechBubble[5] = "URAAAA";
        speechBubble[6] = "JACK";
        speechBubble[7] = "MIXI";
        speechBubble[8] = ":3";
        speechBubble[9] = "CUTE";
        speechBubble[10] = "APPLE MEO MEo";
    }

    public void speak() {
        // De nhan vat lam nhung viec dac biet ca nhan
        super.speak();
    }
}
