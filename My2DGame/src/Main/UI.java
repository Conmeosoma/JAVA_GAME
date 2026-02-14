/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import Object.OBJ_Heart;
import Enity.Entity;
import Object.OBJ_ManaCrystal;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.ArrayList;

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
    BufferedImage heart_full, heart_half, heart_blank, crystal_full, crystal_blank;
    // BUFFERED IMAGE;
    public boolean messageOn = false;
    // public String message = "";
    // int messageCounter = 0; // dem so luong thong bao
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();
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

    // INVENTORY
    public int slotCol = 0;
    public int slotRow = 0;
    int subState = 0;

    public UI(GamePanel gp) {
        this.gp = gp;
        try {
            InputStream is = getClass().getResourceAsStream("/res/Font/x12y16pxMaruMonica.ttf");
            maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
            is = getClass().getResourceAsStream("/res/Font/Purisa Bold.ttf");
            purisaB = Font.createFont(Font.TRUETYPE_FONT, is);

        } catch (Exception e) {
            e.printStackTrace();
        }
        initializeUnicodeFonts();
        Entity heart = new OBJ_Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;
        Entity crystal = new OBJ_ManaCrystal(gp);
        crystal_full = crystal.image;
        crystal_blank = crystal.image2;
    }

    private void initializeUnicodeFonts() {
        // Try to use Dialog font which supports Unicode better
        unicodeFont_40 = new Font("Dialog", Font.PLAIN, 40);
        unicodeFont_80B = new Font("Dialog", Font.BOLD, 80);
        unicodeBubbleFont = new Font("Dialog", Font.PLAIN, 14);

        if (unicodeFont_40 == null) {
            unicodeFont_40 = new Font("SansSerif", Font.PLAIN, 40);
            unicodeFont_80B = new Font("SansSerif", Font.BOLD, 80);
            unicodeBubbleFont = new Font("SansSerif", Font.PLAIN, 14);
        }
    }

    public void addMessage(String text) {
        message.add(text);
        messageCounter.add(0);
    }

    // SPEECH BUBBLE METHOD
    public void setSpeechBubble(String text, Entity entity) {
        speechBubbleText = text;
        speechBubbleEntity = entity;
        showSpeechBubble = true;
    }

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
            drawMessage();
        }

        // PAUSE STATE
        if (gp.gameState == gp.pauseState) {
            drawPlayerLife();
            drawPauseSceen();
        }

        // DIALOGUE
        if (gp.gameState == gp.dialogueState) {
            drawPlayerLife();
            drawDialogueScreen();
        }
        // CHARACTER
        if (gp.gameState == gp.characterState) {
            drawCharacterScreen();
            drawInventory();
        }
        if (gp.gameState == gp.optionsState) {
            drawOptionsScreen();
        }
    }

    public void drawOptionsScreen() {
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(26F));
        int frameX = gp.tileSize * 6;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize * 8;
        int frameHeight = gp.tileSize * 10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);
        switch (subState) {
            case 0:
                options_top(frameX, frameY);
                break;
            case 1:
                options_fullScreenCheckBox(frameX, frameY);
                break;
            case 2:
                options_control(frameX, frameY);
                break;
            case 3:
                options_endGame(frameX, frameY);
        }
        gp.keyH.enterPressed = false; // Reset enter key state after handling options input
    }

    public void options_endGame(int frameX, int frameY) {
        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize * 3;
        currentDialogue = "Quit the game and\nreturn to the \ntitle screen?";
        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, textX, textY);
            textY += 40;
        }
        // YES
        String text = "Yes";
        textX = getXforCenteredText(text);
        textY += gp.tileSize * 3;
        g2.drawString(text, textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            g2.drawString("<", (int) (textX + gp.tileSize * 3.5), textY);
            if (gp.keyH.enterPressed == true) {
                subState = 0;
                gp.gameState = gp.titleState;
            }
        }
        // NO
        text = "No";
        textX = getXforCenteredText(text);
        textY += gp.tileSize;
        g2.drawString(text, textX, textY);
        if (commandNum == 1) {
            g2.drawString(">", textX - 25, textY);
            g2.drawString("<", (int) (textX + gp.tileSize * 3.5), textY);
            if (gp.keyH.enterPressed == true) {
                subState = 0;
                commandNum = 0;
            }
        }

    }

    public void options_top(int frameX, int frameY) {
        int textX;
        int textY;
        String text = "Option";
        textX = getXforCenteredText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        // FULL SCREEN ON/OFF
        textX = frameX + gp.tileSize;
        textY += gp.tileSize * 2;
        g2.drawString("Full Screen", textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            g2.drawString("<", textX + 170, textY);
            if (gp.keyH.enterPressed == true) {
                if (gp.fullScreenOn == false) {
                    gp.fullScreenOn = true;
                    gp.applyFullScreen();
                } else if (gp.fullScreenOn == true) {
                    gp.fullScreenOn = false;
                    gp.applyFullScreen();
                }
                subState = 1;
            }
        }

        // MUSIC
        textY += gp.tileSize;
        g2.drawString("Music", textX, textY);
        if (commandNum == 1) {
            g2.drawString(">", textX - 25, textY);
            g2.drawString("<", textX + 80, textY);
        }

        // SE
        textY += gp.tileSize;
        g2.drawString("SE", textX, textY);
        if (commandNum == 2) {
            g2.drawString(">", textX - 25, textY);
            g2.drawString("<", textX + 35, textY);
        }
        // CONTROL
        textY += gp.tileSize;
        g2.drawString("Control", textX, textY);
        if (commandNum == 3) {
            g2.drawString(">", textX - 25, textY);
            g2.drawString("<", textX + 125, textY);
            if (gp.keyH.enterPressed == true) {
                subState = 2; // CONTROL()
                commandNum = 0; // Reset commandNum for control options
            }
        }

        // END GAME
        textY += gp.tileSize * 2;
        g2.drawString("End Game", textX, textY);
        if (commandNum == 4) {
            g2.drawString(">", textX - 25, textY);
            g2.drawString("<", textX + 165, textY);
            if (gp.keyH.enterPressed == true) {
                subState = 3;
                commandNum = 0;
            }
        }

        // FULL SCREEN CHECK BOX
        textX = frameX + (int) (gp.tileSize * 5);
        textY = frameY + gp.tileSize * 2 + 24;
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(textX, textY, 24, 24);
        if (gp.fullScreenOn == true) {
            g2.fillRect(textX, textY, 24, 24);
        }

        // MUSIC VOLUME
        textY += gp.tileSize;
        g2.drawRect(textX, textY, 120, 24);
        int volumeWidth = 24 * gp.music.volumeScale;
        g2.fillRect(textX, textY, volumeWidth, 24);

        // SE
        textY += gp.tileSize;
        g2.drawRect(textX, textY, 120, 24);
        int seVolumeWidth = 24 * gp.se.volumeScale;
        g2.fillRect(textX, textY, seVolumeWidth, 24);
    }

    public void options_control(int frameX, int frameY) {
        int textX;
        int textY;
        String text = "Control";
        textX = getXforCenteredText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        textX = frameX + gp.tileSize;
        textY += gp.tileSize;
        g2.drawString("Move Up", textX, textY += gp.tileSize);
        g2.drawString("Confirm/Attack", textX, textY += gp.tileSize);
        g2.drawString("Shoot/Cast", textX, textY += gp.tileSize);
        g2.drawString("Character Screen", textX, textY += gp.tileSize);
        g2.drawString("Pause", textX, textY += gp.tileSize);
        g2.drawString("Options", textX, textY += gp.tileSize);
        textX = frameX + gp.tileSize * 9;
        textY = frameY + gp.tileSize * 2;
        g2.drawString("ADWS/Arrow Keys", textX, textY += gp.tileSize);
        g2.drawString("Enter", textX, textY += gp.tileSize);
        g2.drawString("F", textX, textY += gp.tileSize);
        g2.drawString("C", textX, textY += gp.tileSize);
        g2.drawString("P", textX, textY += gp.tileSize);
        g2.drawString("ESC", textX, textY += gp.tileSize);
        // BACK
        textX = frameX + gp.tileSize;
        textY += gp.tileSize * 9;
        g2.drawString("Back", textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            g2.drawString("<", textX + 35, textY);
            if (gp.keyH.enterPressed == true) {
                subState = 0; // BACK TO OPTIONS_TOP
                commandNum = 3; // Reset commandNum for options menu
            }
        }
    }

    public void drawPlayerLife() {
        // gp.player.life = 3;
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

        // DRAW MAX MANA
        x = gp.tileSize / 2 - 5;
        y = (int) (gp.tileSize * 1.5);
        i = 0;
        while (i < gp.player.maxMana) {
            g2.drawImage(crystal_blank, x, y, null);
            i++;
            x += 35;
        }

        // DRAW MANA
        x = gp.tileSize / 2 - 5;
        y = (int) (gp.tileSize * 1.5);
        i = 0;
        while (i < gp.player.mana) {
            g2.drawImage(crystal_full, x, y, null);
            i++;
            x += 35;
        }
    }

    public void drawMessage() {
        int messageX = gp.tileSize;
        int messageY = gp.tileSize * 4;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));

        for (int i = 0; i < message.size(); i++) {
            if (message.get(i) != null) {
                g2.setColor(Color.black);
                g2.drawString(message.get(i), messageX + 2, messageY + 2);
                g2.setColor(Color.white);
                g2.drawString(message.get(i), messageX, messageY);

                int counter = messageCounter.get(i) + 1; // messageCounter++
                messageCounter.set(i, counter); // set the counter to the array
                messageY += 50;

                if (messageCounter.get(i) > 180) { // 3 giay
                    message.remove(i);
                    messageCounter.remove(i);
                }
            }
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
                g2.drawString("<", (int) (x + gp.tileSize * 5.9), y);
            }
            text = "OPTION";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 2) {
                g2.drawString(">", x - gp.tileSize, y);
                g2.drawString("<", (int) (x + gp.tileSize * 4.4), y);
            }
            text = "EXIT";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 3) {
                g2.drawString(">", x - gp.tileSize, y);
                g2.drawString("<", (int) (x + gp.tileSize * 2.7), y);
            }
        } else if (titleSceenState == 1) {
            // COLOR BACKGROUND
            g2.setColor(new Color(255, 200, 160, 255));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

            // CLASS SELECTION SCREEN
            g2.setColor(new Color(78, 52, 46, 255));
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 42F));
            String text = "CHOOSE YOUR CLASS";
            int x = getXforCenteredText(text);
            int y = gp.tileSize * 2;
            g2.drawString(text, x, y);

            // CLASS MENU
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30F));
            text = "Warrior";
            x = getXforCenteredText(text);
            y += gp.tileSize * 3;
            g2.drawString(text, x, y);
            if (commandNum == 0) {
                g2.drawString(">", x - gp.tileSize, y);
                g2.drawString("<", (int) (x + gp.tileSize * 4.3), y);
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
                g2.drawString("<", (int) (x + gp.tileSize * 4.3), y);
            }

            text = "Assassin";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 3) {
                g2.drawString(">", x - gp.tileSize, y);
                g2.drawString("<", (int) (x + gp.tileSize * 5.2), y);
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

    public int getXforAlignToRightText(String text, int tailX) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = tailX - length;
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
        int[] xPoints = { bubbleX + bubbleWidth / 2, bubbleX + bubbleWidth / 2 - 8, bubbleX + bubbleWidth / 2 + 8 };
        int[] yPoints = { bubbleY + bubbleHeight, bubbleY + bubbleHeight + 12, bubbleY + bubbleHeight };

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

    public void drawCharacterScreen() {
        final int frameX = gp.tileSize * 2;
        final int frameY = gp.tileSize;
        final int frameWidth = gp.tileSize * 5;
        final int frameHeight = gp.tileSize * 10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        // TEXT
        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(28F));

        int textX = frameX + 20;
        int textY = frameY + gp.tileSize;
        final int lineHeight = 38;

        // Vị trí cột giá trị (căn phải)
        int valueX = frameX + frameWidth - 30;

        // NAMES và VALUES
        String[] labels = { "Level", "Life", "Mana", "Strength", "Dexterity", "Attack", "Defense", "EXP", "Next Level",
                "Coin",
                "Weapon", "Shield" };
        String[] values = {
                String.valueOf(gp.player.level),
                gp.player.life + "/" + gp.player.maxLife,
                gp.player.mana + "/" + gp.player.maxMana,
                String.valueOf(gp.player.strength),
                String.valueOf(gp.player.dexterity),
                String.valueOf(gp.player.attack),
                String.valueOf(gp.player.defense),
                String.valueOf(gp.player.exp),
                String.valueOf(gp.player.nextLevelExp),
                String.valueOf(gp.player.coin),
                "", // Weapon - sẽ vẽ icon
                "" // Shield - sẽ vẽ icon
        };

        for (int i = 0; i < labels.length; i++) {
            // Vẽ label
            // g2.setFont(unicodeFont_40.deriveFont(28F));
            g2.drawString(labels[i], textX, textY);

            // Vẽ value (nếu không phải weapon/shield)
            if (i < 10) {
                int valueTextX = getXforAlignToRightText(values[i], valueX);
                g2.drawString(values[i], valueTextX, textY);
            }

            textY += lineHeight;
        }

        // Vẽ Weapon và Shield icons
        int weaponY = frameY + gp.tileSize + (lineHeight * 9) - 10;
        int shieldY = frameY + gp.tileSize + (lineHeight * 10) - 5;

        g2.drawImage(gp.player.currentWeapon.down1, valueX - gp.tileSize + 5, weaponY, null);
        g2.drawImage(gp.player.currentShield.down1, valueX - gp.tileSize + 5, shieldY, null);
    }

    public void drawInventory() {
        // FRAME: KHUNG
        int frameX = gp.tileSize * 12;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize * 6;
        int frameHeight = gp.tileSize * 5;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        // SLOT
        final int slotXStart = frameX + 20;
        final int slotYStart = frameY + 20;
        int slotX = slotXStart;
        int slotY = slotYStart;
        int slotSize = gp.tileSize + 3;

        // CURSOR
        int cursorX = slotXStart + (slotSize * slotCol);
        int cursorY = slotYStart + (slotSize * slotRow);
        int cursorWidth = gp.tileSize;
        int cursorHeight = gp.tileSize;

        // DRAW PLAYER'S ITEMS
        for (int i = 0; i < gp.player.inventory.size(); i++) {

            // Equip cursor
            if (gp.player.inventory.get(i) == gp.player.currentWeapon
                    || gp.player.inventory.get(i) == gp.player.currentShield) {
                g2.setColor(new Color(240, 190, 90)); // Yellow with transparency
                g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
            }
            g2.drawImage(gp.player.inventory.get(i).down1, slotX, slotY, null);
            slotX += slotSize;
            if (i == 4 || i == 9 || i == 14) {
                slotX = slotXStart;
                slotY += slotSize;
            }
        }

        // DRAW CURSOR
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);

        // DESCRIPTION FRAME - Chỉ hiện khi có item
        int itemIndex = getItemIndexOnSlot();
        if (itemIndex < gp.player.inventory.size()) {
            int dFrameX = frameX;
            int dFrameY = frameY + frameHeight;
            int dFrameWidth = frameWidth;
            int dFrameHeight = gp.tileSize * 3;
            drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);

            // DRAW DESCRIPTION TEXT
            int textX = dFrameX + 20;
            int textY = dFrameY + gp.tileSize;
            // Dieu muon dung tieng Viet
            g2.setFont(unicodeFont_40.deriveFont(28F));

            for (String line : gp.player.inventory.get(itemIndex).description.split("\n")) {
                g2.drawString(line, textX, textY);
                textY += 32;
            }
        }
    }

    public void options_fullScreenCheckBox(int frameX, int frameY) {
        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize * 3;
        currentDialogue = "The change will take \neffect after you\nrestart the game.";
        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, textX, textY);
            textY += 40;
        }
        // BACk
        textY = frameY + gp.tileSize * 8;
        g2.drawString("Back", textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            g2.drawString("<", textX + 80, textY);
            if (gp.keyH.enterPressed == true) {
                // Return to options top instead of jumping back to play
                subState = 0;
                commandNum = 0;
            }
        }

    }

    public int getItemIndexOnSlot() {
        int itemIndex = slotCol + (slotRow * 5);
        return itemIndex;
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
