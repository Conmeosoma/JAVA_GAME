/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import Object.OBJ_Key;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

/**
 *
 * @author dieu hoang
 */
public class UI {

    GamePanel gp;
    Font arial_40, arial_80B;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;  // dem so luong thong bao
    public boolean gameFinished = false;

    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp) {
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);  // khoi tao font chu ngoai gameloop => tiet kiem bo nho
        arial_80B = new Font("Arial", Font.BOLD, 80);
        OBJ_Key key = new OBJ_Key();
        keyImage = key.image;
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {

        if (gameFinished == true) {

            g2.setFont(arial_40);
            g2.setColor(Color.WHITE);

            String text;
            int textlength;
            int x;
            int y;

            text = "You found the treasure!";
            textlength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth / 2 - textlength / 2; // can chinh de text o giua khung hinh theo chieu rong
            y = gp.screenHeight / 2 - (gp.tileSize * 3);  // can chinh de text o ben tren khung hinh
            g2.drawString(text, x, y);
            
            text = "Your Time is: " + dFormat.format(playTime) + "!";
            textlength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth / 2 - textlength / 2; // can chinh de text o giua khung hinh theo chieu rong
            y = gp.screenHeight / 2 + (gp.tileSize * 4);  // can chinh de text o ben tren khung hinh
            g2.drawString(text, x, y);

            g2.setFont(arial_80B);
            g2.setColor(Color.YELLOW);
            text = "Congratulations!";
            textlength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth / 2 - textlength / 2;  // can chinh de text o giua khung hinh theo chieu rong
            y = gp.screenHeight / 2 + (gp.tileSize * 2); // can chinh de text o ben duoi khung hinh
            g2.drawString(text, x, y);

            gp.gamThread = null;
        } else {
            g2.setFont(arial_40);
            g2.setColor(Color.WHITE);
            g2.drawImage(keyImage, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);  // tai hinh anh len
            g2.drawString("x " + gp.player.hasKey, 74, 65); // ve chu voi (74, 65) la toa do goc duoi ben trai cua chu

            // TIME
            playTime += (double) 1 / 60;
            g2.drawString("Time: " + dFormat.format(playTime), gp.tileSize * 11, 65);

            // MESSAGE
            if (messageOn == true) {

                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message, gp.tileSize / 2, gp.tileSize * 5);
                messageCounter++;

                if (messageCounter > 120) { // Thong bao se tat sau 2s
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }
    }
}
