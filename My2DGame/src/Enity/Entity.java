// CodeByConMeoSoMa
// /\_/\  
//( o.o ) 
// > ^ <
package Enity; // 1 goi package de to chuc ma nguon

import Main.GamePanel;
import Main.UtilityTool;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage; // them lop BufferedIamge de xu ly hinh anh
import java.awt.Rectangle;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Entity { // lop Entity chua cac thuoc tinh va phuong thuc chung cho tat ca doi tuong
    // trong game

    GamePanel gp;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1,
            attackRight2;
    public BufferedImage image, image2, image3; // hinh anh cua doi tuong
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);// hinh chu nhat vung va cha
    public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
    public int solidAreaDefaultX, solidAreaDefaultY; // Toa do mac dinh cua vung va cham
    String dialogues[] = new String[20];
    String speechBubble[] = new String[20];

    // STATE
    public int World_X, World_Y;// toa do the gioi cua doi tuong
    public String direction = "down";
    public int spiteNum = 1;
    int dialogueIndex = 0;
    int speechBubbleIndex = 0;
    public boolean collisionOn = false; // bien kiem tra va cham
    public boolean invincible = false;
    public boolean attacking = false;
    public boolean alive = true;
    public boolean dying = false;
    public boolean hpBarOn = false;

    // COUNTER
    public int spiteCounter = 0;
    public int actionLockCounter = 0;
    public int invincibleCounter = 0;
    public int shotAvailableCounter = 0;
    int dyingCounter = 0;
    int hpBarCounter = 0;

    // SPEECH BUBBLE
    public boolean isSpeaking = false;
    public int speechBubbleCounter = 0;
    public int speechBubbleDuration = 200; // 200 frames ~3.3 seconds
    public boolean collision = false;

    // CHARATER ATTRIBUTES
    public int speed;
    public int maxLife;
    public int life;
    public int maxMana;
    public int mana;
    public int ammo;
    public String name;
    public int level;
    public int strength;
    public int defense;
    public int exp;
    public int dexterity;
    public int attack;
    public int nextLevelExp;
    public int coin;
    public Entity currentWeapon;
    public Entity currentShield;
    public Projectile projectile;
    // ITEM
    public int value;
    public int attackValue;
    public int defenseValue;
    public String description = "";
    public int useCost;
    // TYPE
    public int type; // 0 = player, 1 = npc, 2 = monster
    public final int type_player = 0;
    public final int type_npc = 1;
    public final int type_monster = 2;
    public final int type_sword = 3;
    public final int type_axe = 4;
    public final int type_shield = 5;
    public final int type_consumable = 6;
    public final int type_pickupOnly = 7;

    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public void use(Entity entity) {
    }

    public void setAction() {

    }

    public void damageReaction() {

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

    public void checkDrop() {
    }

    public void dropItem(Entity droppedItem) {
        for (int i = 0; i < gp.obj[1].length; i++) {
            if (gp.obj[gp.currentMap][i] == null) {
                gp.obj[gp.currentMap][i] = droppedItem;
                gp.obj[gp.currentMap][i].World_X = World_X; // Quai vat chet tai World_X
                gp.obj[gp.currentMap][i].World_Y = World_Y;
                break;
            }
        }
    }

    public Color getParticleColor() {
        Color color = null;
        return color;
    }

    public int getParticleSize() {
        int size = 0;
        return size;
    }

    public int getParticleSpeed() {
        int speed = 0;
        return speed;
    }

    public int getParticleMaxLife() {
        int maxLife = 0;
        return maxLife;
    }

    public void generateParticle(Entity generator, Entity target) {
        Color color = generator.getParticleColor();
        int size = generator.getParticleSize();
        int speed = generator.getParticleSpeed();
        int maxLife = generator.getParticleMaxLife();

        Particle p1 = new Particle(gp, target, color, size, speed, maxLife, -2, -1);
        Particle p2 = new Particle(gp, target, color, size, speed, maxLife, 2, -1);
        Particle p3 = new Particle(gp, target, color, size, speed, maxLife, -2, 1);
        Particle p4 = new Particle(gp, target, color, size, speed, maxLife, 2, 1);
        gp.particleList.add(p1);
        gp.particleList.add(p2);
        gp.particleList.add(p3);
        gp.particleList.add(p4);
    }

    public void update() {

        setAction();

        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.monster);
        gp.cChecker.checkEntity(this, gp.iTile);
        boolean contactPlayer = gp.cChecker.checkPlayer(this);

        if (this.type == type_monster && contactPlayer == true) {
            if (gp.player.invincible == false) {
                damagePlayer(attack);
                // gp.playSE(6);
                //
                // int damage = attack - gp.player.defense;
                // if (damage < 0) {
                // damage = 0;
                // }
                // gp.player.life -= damage;
                //

                //// gp.player.life -= 1;
                // gp.player.invincible = true;
            }
        }

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
        if (invincible == true) {
            invincibleCounter++;
            if (invincibleCounter > 40) {
                invincible = false;
                invincibleCounter = 0;
            }
        }

        // UPDATE SPEECH BUBBLE
        if (isSpeaking) {
            speechBubbleCounter++;
            if (speechBubbleCounter >= speechBubbleDuration) {
                isSpeaking = false;
                speechBubbleCounter = 0;
            }
        }
        if (shotAvailableCounter < 30) {
            shotAvailableCounter++;
        }
    }

    public void damagePlayer(int attack) {
        gp.playSE(6);

        int damage = attack - gp.player.defense;
        if (damage < 0) {
            damage = 0;
        }
        gp.player.life -= damage;

        // gp.player.life -= 1;
        gp.player.invincible = true;
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
            }
            // MONTERS HP BAR
            if (type == 2 && hpBarOn == true) {
                double oneScale = (double) gp.tileSize / maxLife;
                double hpBaraValue = oneScale * life;

                g2.setColor(new Color(35, 35, 35));
                g2.fillRect(screenX - 1, screenY - 16, gp.tileSize + 2, 12);
                g2.setColor(new Color(255, 0, 30));
                g2.fillRect(screenX, screenY - 15, (int) hpBaraValue, 10);

                hpBarCounter++;
                if (hpBarCounter > 600) {
                    hpBarCounter = 0;
                    hpBarOn = false;
                }
            }

            if (invincible == true) {
                hpBarOn = true;
                hpBarCounter = 0;
                changeAlpha(g2, 0.4f);
            }
            if (dying == true) {
                dyingAnimation(g2);
            }
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            changeAlpha(g2, 1f);
        }
    }

    public void dyingAnimation(Graphics2D g2) {
        dyingCounter++;
        int i = 5;
        if (dyingCounter <= i) {
            changeAlpha(g2, 0f);
        }
        if (dyingCounter > i && dyingCounter <= i * 2) {
            changeAlpha(g2, 1f);
        }
        if (dyingCounter > i * 2 && dyingCounter <= i * 3) {
            changeAlpha(g2, 0f);
        }
        if (dyingCounter > i * 3 && dyingCounter <= i * 4) {
            changeAlpha(g2, 1f);
        }
        if (dyingCounter > i * 4 && dyingCounter <= i * 5) {
            changeAlpha(g2, 0f);
        }
        if (dyingCounter > i * 5 && dyingCounter <= i * 6) {
            changeAlpha(g2, 1f);
        }
        if (dyingCounter > i * 6 && dyingCounter <= i * 7) {
            changeAlpha(g2, 0f);
        }
        if (dyingCounter > i * 7 && dyingCounter <= i * 8) {
            changeAlpha(g2, 1f);
        }
        if (dyingCounter > i * 8) {

            alive = false;
        }
    }

    public void changeAlpha(Graphics2D g2, float alphaValue) {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
    }

    public BufferedImage setup(String imagePath, int width, int height) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            image = uTool.scaleImage(image, width, height);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
