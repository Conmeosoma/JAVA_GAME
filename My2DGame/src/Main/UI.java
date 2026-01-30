/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import Object.OBJ_Heart;
//import Object.OBJ_Key;
//import Object.SuperObject;
import Enity.Entity;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.InputStream;
//import java.text.DecimalFormat;

/**
 *
 * @author dieu hoang
 */
public class UI {

    GamePanel gp;
    Graphics2D g2;
    // Font arial_40, arial_80B;
    Font maruMonica, purisaB;
    Font unicodeFont_40, unicodeFont_80B; // Unicode fonts for multilingual support
    Font unicodeBubbleFont; // Font for speech bubble
    BufferedImage heart_full, heart_half, heart_blank;
    // BUFFERED IMAGE;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0; // dem so luong thong bao
    public boolean gameFinished = false;
    public String currentDialogue = "";

    // SPEECH BUBBLE
    public String speechBubbleText = "";
    public Entity speechBubbleEntity = null;
    public boolean showSpeechBubble = false;
    int speechBubbleCounter = 0; // Counter for rainbow color effect

    // ANIMATION
    int spriteCounter = 0; // Counter for sprite animation
    int spriteNum = 1; // Current sprite (1 or 2)

    // COMMAND
    public int commandNum = 0;
    public int titleSceenState = 0; // 0: the firts sceen

    // double playTime;
    // DecimalFormat dFormat = new DecimalFormat("#0.00");
    public UI(GamePanel gp) {
        this.gp = gp;
        // arial_40 = new Font("Arial", Font.PLAIN, 40); // khoi tao font chu ngoai
        // gameloop => tiet kiem bo nho
        // arial_80B = new Font("Arial", Font.BOLD, 80);

        try {
            InputStream is = getClass().getResourceAsStream("/res/Font/x12y16pxMaruMonica.ttf");
            maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
            is = getClass().getResourceAsStream("/res/Font/Purisa Bold.ttf");
            purisaB = Font.createFont(Font.TRUETYPE_FONT, is);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Initialize Unicode fonts for multilingual support
        initializeUnicodeFonts();

        // OBJ_Key key = new OBJ_Key(gp);
        // keyImage = key.image;
        // CREATE HEART
//        SuperObject heart = new OBJ_Heart(gp);
        Entity heart = new OBJ_Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;
    }

    // Initialize fonts that support multiple languages
    private void initializeUnicodeFonts() {
        // Try to use Dialog font which supports Unicode better
        unicodeFont_40 = new Font("Dialog", Font.PLAIN, 40);
        unicodeFont_80B = new Font("Dialog", Font.BOLD, 80);
        unicodeBubbleFont = new Font("Dialog", Font.PLAIN, 14);

        // If Dialog is not available, fallback to SansSerif
        if (unicodeFont_40 == null) {
            unicodeFont_40 = new Font("SansSerif", Font.PLAIN, 40);
            unicodeFont_80B = new Font("SansSerif", Font.BOLD, 80);
            unicodeBubbleFont = new Font("SansSerif", Font.PLAIN, 14);
        }
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    // SPEECH BUBBLE METHOD
    public void setSpeechBubble(String text, Entity entity) {
        speechBubbleText = text;
        speechBubbleEntity = entity;
        showSpeechBubble = true;
    }

    // public void draw(Graphics2D g2) {
    // if (gameFinished == true) {
    // g2.setFont(arial_40);
    // g2.setColor(Color.WHITE);
    // String text;
    // int textlength;
    // int x;
    // int y;
    // text = "You found the treasure!";
    // textlength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
    // x = gp.screenWidth / 2 - textlength / 2; // can chinh de text o giua khung
    // hinh theo chieu rong
    // y = gp.screenHeight / 2 - (gp.tileSize * 3); // can chinh de text o ben tren
    // khung hinh
    // g2.drawString(text, x, y);
    // text = "Your Time is: " + dFormat.format(playTime) + "!";
    // textlength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
    // x = gp.screenWidth / 2 - textlength / 2; // can chinh de text o giua khung
    // hinh theo chieu rong
    // y = gp.screenHeight / 2 + (gp.tileSize * 4); // can chinh de text o ben tren
    // khung hinh
    // g2.drawString(text, x, y);
    // g2.setFont(arial_80B);
    // g2.setColor(Color.YELLOW);
    // text = "Congratulations!";
    // textlength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
    // x = gp.screenWidth / 2 - textlength / 2; // can chinh de text o giua khung
    // hinh theo chieu rong
    // y = gp.screenHeight / 2 + (gp.tileSize * 2); // can chinh de text o ben duoi
    // khung hinh
    // g2.drawString(text, x, y);
    // gp.gamThread = null;
    // } else {
    // g2.setFont(arial_40);
    // g2.setColor(Color.WHITE);
    // g2.drawImage(keyImage, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize,
    // gp.tileSize, null);
    // g2.drawString("x " + gp.player.hasKey, 74, 65); // ve chu voi (74, 65) la toa
    // playTime += (double) 1 / 60;
    // float hue = (float) (playTime * 0.5);
    // float saturation = 1.0f; // Độ đậm (1.0 là đậm nhất)
    // float brightness = 1.0f; // Độ sáng (1.0 là sáng nhất)
    // // Tạo màu từ HSB
    // Color rainbowColor = Color.getHSBColor(hue, saturation, brightness);
    // g2.setColor(rainbowColor);
    // g2.drawString("Time: " + dFormat.format(playTime), gp.tileSize * 11, 65);
    // // MESSAGE
    // if (messageOn == true) {
    // g2.setFont(g2.getFont().deriveFont(30F));
    // g2.drawString(message, gp.tileSize / 2, gp.tileSize * 5);
    // messageCounter++;
    // if (messageCounter > 120) { // Thong bao se tat sau 2s
    // messageCounter = 0;
    // messageOn = false;
    // }
    // }
    // }
    // }
    public void draw(Graphics2D g2) {
        this.g2 = g2;
        // g2.setFont(maruMonica);
        g2.setFont(purisaB);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(Color.white);

        // TITLE STATE
        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }

        // PLAY STATE
        if (gp.gameState == gp.playState) {
            // Draw speech bubble during play state
            if (showSpeechBubble && speechBubbleEntity != null) {
                drawSpeechBubble();
            }
            drawPlayerLife();
        }

        // PAUSE STATE
        if (gp.gameState == gp.pauseState) {
            drawPauseSceen();
        }

        // DIALOGUE
        if (gp.gameState == gp.dialogueState) {
            drawDialogueScreen();
            drawPlayerLife();
        }
    }

    public void drawPlayerLife() {
        //gp.player.life = 3;
        int x = gp.tileSize / 2;
        int y = gp.tileSize / 2;
        int i = 0;
        // DRAW MAX HEART
        while (i < gp.player.maxLife / 2) {
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x += gp.tileSize;
        }
        // RESET
        x = gp.tileSize / 2;
        y = gp.tileSize / 2;
        i = 0;
        // DRAW CURRENT LIFE
        while (i < gp.player.life) {
            if (i + 1 == gp.player.life) {
                // Số sống lẻ - vẽ nửa trái tim
                g2.drawImage(heart_half, x, y, null);
            } else {
                // Số sống chẵn - vẽ cả trái tim
                g2.drawImage(heart_full, x, y, null);
            }
            i += 2;
            x += gp.tileSize;
        }
    }

    public void drawTitleScreen() {
        if (titleSceenState == 0) {// COLOR BACKGROUND
            g2.setColor(new Color(255, 200, 160, 255));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

            // TITLE NAME
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 60F));
            String text = "HIT ADVENTURE";
            int x = getXforCenteredText(text);
            int y = gp.tileSize * 2;
            // MAIN COLOR
            g2.setColor(new Color(78, 52, 46, 255));
            g2.drawString(text, x, y);
            // SHADOW COLOR
            g2.setColor(new Color(0, 0, 0, 128));
            g2.drawString(text, x + 5, y + 5);
            // HIT BOY IMAGE with animation
            x = gp.screenWidth / 2 - (gp.tileSize * 2);
            y = gp.tileSize * 3;

            // Sprite animation counter
            spriteCounter++;
            if (spriteCounter > 45) { // Change sprite every 10 frames
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }

            // Draw the appropriate sprite
            if (spriteNum == 1) {
                g2.drawImage(gp.player.down1, x, y, gp.tileSize * 4, gp.tileSize * 4, null);
            } else {
                g2.drawImage(gp.player.down2, x, y, gp.tileSize * 4, gp.tileSize * 4, null);
            }

            // MENU
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
            text = "NEW GAME";
            x = getXforCenteredText(text);
            y += gp.tileSize * 5;
            g2.drawString(text, x, y);
            if (commandNum == 0) {
                g2.drawString(">", x - gp.tileSize, y);
                g2.drawString("<", (int) (x + gp.tileSize * 5.55), y);
            }

            text = "LOAD GAME";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 1) {
                g2.drawString(">", x - gp.tileSize, y);
                g2.drawString("<", (int) (x + gp.tileSize * 6.2), y);
            }

            text = "OPTIONS";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 2) {
                g2.drawString(">", x - gp.tileSize, y);
                g2.drawString("<", (int) (x + gp.tileSize * 4.9), y);
            }

            text = "QUIT GAME";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 3) {
                g2.drawString(">", x - gp.tileSize, y);
                g2.drawString("<", (int) (x + gp.tileSize * 6.1), y);
            }

        } else if (titleSceenState == 1) {
            g2.setColor(new Color(255, 200, 160, 255));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

            // CLASS SELECTION SCREEN
            g2.setColor(new Color(78, 52, 46, 255));
            g2.setColor(new Color(0, 0, 0, 128));

            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
            String text = "Select your class:";
            int x = getXforCenteredText(text);
            int y = gp.tileSize * 3;
            g2.drawString(text, x, y);

            text = "Warrior";
            x = getXforCenteredText(text);
            y += gp.tileSize * 2;
            g2.drawString(text, x, y);
            if (commandNum == 0) {
                g2.drawString(">", x - gp.tileSize, y);
                g2.drawString("<", (int) (x + gp.tileSize * 4), y);
            }

            text = "Mage";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 1) {
                g2.drawString(">", x - gp.tileSize, y);
                g2.drawString("<", (int) (x + gp.tileSize * 3), y);
            }

            text = "Gunner";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 2) {
                g2.drawString(">", x - gp.tileSize, y);
                g2.drawString("<", (int) (x + gp.tileSize * 3.8), y);
            }

            text = "Assassin";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 3) {
                g2.drawString(">", x - gp.tileSize, y);
                g2.drawString("<", (int) (x + gp.tileSize * 4.2), y);
            }

            text = "Back";
            x = getXforCenteredText(text);
            y += gp.tileSize * 2;
            g2.drawString(text, x, y);
            if (commandNum == 4) {
                g2.drawString(">", x - gp.tileSize, y);
                g2.drawString("<", (int) (x + gp.tileSize * 2.7), y);
            }
        }

    }

    public int getXforCenteredText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;
        return x;
    }

    // DRAW SPEECH BUBBLE ABOVE NPC
    public void drawSpeechBubble() {
        if (speechBubbleEntity == null || speechBubbleText == null || speechBubbleText.isEmpty()) {
            showSpeechBubble = false;
            return;
        }

        // Set font for text measurement - use Unicode font for multilingual support
        g2.setFont(unicodeBubbleFont);

        // Calculate text dimensions
        int textWidth = (int) g2.getFontMetrics().getStringBounds(speechBubbleText, g2).getWidth();
        int textHeight = g2.getFontMetrics().getHeight();

        // Calculate bubble dimensions with padding
        int padding = 12;
        int bubbleWidth = textWidth + (padding * 2);
        int bubbleHeight = textHeight + (padding * 2);

        // Minimum bubble size
        if (bubbleWidth < 60) {
            bubbleWidth = 60;
        }
        if (bubbleHeight < 35) {
            bubbleHeight = 35;
        }

        // Calculate screen position of NPC
        int screenX = speechBubbleEntity.World_X - gp.player.World_X + gp.player.screenX;
        int screenY = speechBubbleEntity.World_Y - gp.player.World_Y + gp.player.screenY;

        // Bubble position above NPC (centered)
        int bubbleX = screenX + gp.tileSize / 2 - bubbleWidth / 2;
        int bubbleY = screenY - bubbleHeight - 20;

        // Draw bubble background
        Color c = new Color(255, 255, 200, 240); // Light yellow with transparency
        g2.setColor(c);
        g2.fillRoundRect(bubbleX, bubbleY, bubbleWidth, bubbleHeight, 15, 15);

        // Draw bubble border
        c = new Color(0, 0, 0);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(bubbleX, bubbleY, bubbleWidth, bubbleHeight, 15, 15);

        // Draw text inside bubble with rainbow color effect
        g2.setFont(unicodeBubbleFont);

        // Create rainbow color effect
        float hue = (float) (speechBubbleCounter * 0.005) % 1.0f;
        Color rainbowColor = Color.getHSBColor(hue, 1.0f, 1.0f);
        g2.setColor(rainbowColor);

        speechBubbleCounter++;

        int textX = bubbleX + (bubbleWidth - textWidth) / 2;
        int textY = bubbleY + padding + textHeight - 3;

        g2.drawString(speechBubbleText, textX, textY);

        // Draw tail pointing down
        int[] xPoints = {bubbleX + bubbleWidth / 2, bubbleX + bubbleWidth / 2 - 8, bubbleX + bubbleWidth / 2 + 8};
        int[] yPoints = {bubbleY + bubbleHeight, bubbleY + bubbleHeight + 12, bubbleY + bubbleHeight};

        g2.setColor(new Color(255, 255, 200, 240));
        g2.fillPolygon(xPoints, yPoints, 3);

        g2.setColor(Color.BLACK);
        g2.drawPolygon(xPoints, yPoints, 3);
    }

    // DIALOGUE
    public void drawDialogueScreen() {
        // Background
        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 4;
        drawSubWindow(x, y, width, height);

        g2.setFont(unicodeFont_40.deriveFont(Font.PLAIN, 20F));
        x += gp.tileSize;
        y += gp.tileSize;

        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }
    }

    // DRAW SUB WINDOW
    public void drawSubWindow(int x, int y, int width, int height) {
        Color c = new Color(0, 0, 0, 210); // nen den (R, G, B, A)
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255); // chu trang
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }

    public void drawPauseSceen() {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight / 2;
        g2.drawString(text, x, y);
    }

}
