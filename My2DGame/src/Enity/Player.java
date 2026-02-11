// CodeByConMeoSoMa
// /\_/\  
//( o.o ) 
// > ^ <
package Enity;

import java.awt.Graphics2D;
import Main.GamePanel;
import Main.KeyHandler;
import Object.OBJ_FireBall;
import Object.OBJ_Key;
import Object.OBJ_Shield_Normal;
import Object.OBJ_Weapon_Normal;

import java.awt.AlphaComposite;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends Entity {

    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    int standCounter = 0;
    public boolean attackCanceled = false;
    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int maxInventorySize = 20;

    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);
        this.keyH = keyH;
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
        getPlayerAttackImage();
        setItems();
    }

    public void setDefaultValues() {
        World_X = gp.tileSize * 23;
        World_Y = gp.tileSize * 21;
        speed = 4;
        direction = "down";
        // PLAYER STATUS
        level = 1;
        maxLife = 8; // 1 trai tim = 2 max life
        life = maxLife;
        strength = 1;
        dexterity = 1;
        exp = 0;
        nextLevelExp = 5;
        coin = 0;
        currentWeapon = new OBJ_Weapon_Normal(gp);
        currentShield = new OBJ_Shield_Normal(gp);
        projectile = new OBJ_FireBall(gp);
        attack = getAttack();
        defense = getDefense();
    }

    public void setItems() {
        inventory.add(currentWeapon);
        inventory.add(currentShield);
        inventory.add(new OBJ_Key(gp));
    }

    public int getAttack() {
        attackArea = currentWeapon.attackArea;
        return attack = strength * currentWeapon.attackValue;
    }

    public int getDefense() {
        return defense = dexterity * currentShield.defenseValue;
    }

    public void getPlayerImage() {
        up1 = setup("/res/Player/Walking_sprites/boy_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/res/Player/Walking_sprites/boy_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("/res/Player/Walking_sprites/boy_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/res/Player/Walking_sprites/boy_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/res/Player/Walking_sprites/boy_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/res/Player/Walking_sprites/boy_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("/res/Player/Walking_sprites/boy_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/res/Player/Walking_sprites/boy_right_2", gp.tileSize, gp.tileSize);

    }

    public void getPlayerAttackImage() {
        if (currentWeapon.type == type_sword) {
            attackUp1 = setup("/res/Player/Attacking_sprites/boy_attack_up_1", gp.tileSize, gp.tileSize * 2);
            attackUp2 = setup("/res/Player/Attacking_sprites/boy_attack_up_2", gp.tileSize, gp.tileSize * 2);
            attackDown1 = setup("/res/Player/Attacking_sprites/boy_attack_down_1", gp.tileSize, gp.tileSize * 2);
            attackDown2 = setup("/res/Player/Attacking_sprites/boy_attack_down_2", gp.tileSize, gp.tileSize * 2);
            attackLeft1 = setup("/res/Player/Attacking_sprites/boy_attack_left_1", gp.tileSize * 2, gp.tileSize);
            attackLeft2 = setup("/res/Player/Attacking_sprites/boy_attack_left_2", gp.tileSize * 2, gp.tileSize);
            attackRight1 = setup("/res/Player/Attacking_sprites/boy_attack_right_1", gp.tileSize * 2, gp.tileSize);
            attackRight2 = setup("/res/Player/Attacking_sprites/boy_attack_right_2", gp.tileSize * 2, gp.tileSize);
        }
        if (currentWeapon.type == type_axe) {
            attackUp1 = setup("/res/Player/Attacking_sprites/boy_axe_up_1", gp.tileSize, gp.tileSize * 2);
            attackUp2 = setup("/res/Player/Attacking_sprites/boy_axe_up_2", gp.tileSize, gp.tileSize * 2);
            attackDown1 = setup("/res/Player/Attacking_sprites/boy_axe_down_1", gp.tileSize, gp.tileSize * 2);
            attackDown2 = setup("/res/Player/Attacking_sprites/boy_axe_down_2", gp.tileSize, gp.tileSize * 2);
            attackLeft1 = setup("/res/Player/Attacking_sprites/boy_axe_left_1", gp.tileSize * 2, gp.tileSize);
            attackLeft2 = setup("/res/Player/Attacking_sprites/boy_axe_left_2", gp.tileSize * 2, gp.tileSize);
            attackRight1 = setup("/res/Player/Attacking_sprites/boy_axe_right_1", gp.tileSize * 2, gp.tileSize);
            attackRight2 = setup("/res/Player/Attacking_sprites/boy_axe_right_2", gp.tileSize * 2, gp.tileSize);
        }
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void update() {
        // Kiem tra game state - chi di chuyen khi game dang choi
        if (gp.gameState == gp.pauseState) {
            return; // khong xu ly di chuyen khi pause
        }
        if (attacking == true) {
            attacking();

        } // chi di chuyen neu co phim duoc nhan
        else if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true
                || keyH.rightPressed == true || keyH.enterPressed == true) {

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

            // KIEM TRA VA CHAM NPC
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            // KIEM TRA VA CHAM VOI MONSTER
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);

            // KIEM TRA SU KIEN
            gp.eHander.checkEvent();

            // NEU VA CHAM LA FALSE, PLAYER CO THE DI CHUYEN
            if (collisionOn == false && keyH.enterPressed == false) {
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

            gp.keyH.enterPressed = false;

            if (keyH.enterPressed == true && attackCanceled == false) {
                gp.playSE(7);
                spiteCounter = 0;
            }
            attackCanceled = false;
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
            gp.keyH.enterPressed = false;
        }
        // CHECK EVENT
        gp.eHander.checkEvent();

        if (gp.keyH.shotKeyPressed == true && projectile.alive == false && shotAvailableCounter == 30) {
            // THIET LAP TOA DO, HUONG VA NGUOI DUNG MAC DINH
            projectile.set(World_X, World_Y, direction, true, this);

            // THEM VAO DANH SACH
            gp.projectileList.add(projectile);
            shotAvailableCounter = 0;
            gp.playSE(10);
        }

        // Cái này cần ở ngoài câu lệnh if
        if (invincible == true) {
            invincibleCounter++;
            if (invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }

        // SAU 30 FRAMES THI MOI BAN DUOC DAN KHAC
        if (shotAvailableCounter < 30) {
            shotAvailableCounter++;
        }
    }

    public void attacking() {
        spiteCounter++;
        if (spiteCounter <= 5) {
            spiteNum = 1;
        }
        if (spiteCounter > 5 && spiteCounter <= 25) {
            spiteNum = 2;
            // Save the current World_X and World_Y
            int currentWorldX = World_X;
            int currentWorldY = World_Y;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            // Adjust worldX/Y for the attackArea
            switch (direction) {
                case "up":
                    World_Y -= attackArea.height;
                    break;
                case "down":
                    World_Y += attackArea.height;
                    break;
                case "left":
                    World_X -= attackArea.width;
                    break;
                case "right":
                    World_X += attackArea.width;
                    break;
            }
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            damageMonster(monsterIndex, attack);

            World_X = currentWorldX;
            World_Y = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;

        }
        if (spiteCounter > 25) {
            spiteNum = 1;
            spiteCounter = 0;
            attacking = false;
        }
    }

    public void damageMonster(int i, int attack) {
        if (i != 999) {
            if (gp.monster[i].invincible == false) {
                // gp.playSE(5);

                int damage = attack - gp.monster[i].defense;
                if (damage < 0) {
                    damage = 0;
                }

                System.out.println("HIT");
                // Dieu khong hieu sao sat thuong yeu qua nen nhan damage voi 5
                gp.monster[i].life -= damage * 5;
                gp.ui.addMessage(damage + " damage!");
                System.out.println("HIT ");
                // gp.monster[i].life -= 20;
                // Anh Minh goi y: Them class de quan ly chi so: mau, toc do, tan cong
                gp.monster[i].invincible = true;
                gp.monster[i].damageReaction();
                if (gp.monster[i].life <= 0) {
                    gp.monster[i].dying = true;
                    gp.ui.addMessage("Killed the " + gp.monster[i].name + "!");
                    gp.ui.addMessage("Exp " + gp.monster[i].exp);
                    exp += gp.monster[i].exp;
                    checkLevelUp();
                }
            }
        }
    }

    public void checkLevelUp() {
        if (exp >= nextLevelExp) {
            level++;
            nextLevelExp = nextLevelExp * 2;
            maxLife += 2;
            strength++;
            dexterity++;
            attack = getAttack();
            defense = getDefense();

            gp.playSE(8);
            gp.gameState = gp.dialogueState;
            gp.ui.addMessage("Level up!");
            gp.ui.currentDialogue = "You are level " + level + " now!\n"
                    + "You feel stronger!";
        }
    }

    public void selectItem() {
        int itemIndex = gp.ui.getItemIndexOnSlot();
        if (itemIndex < inventory.size()) {
            Entity selectedItem = inventory.get(itemIndex);
            if (selectedItem.type == type_sword || selectedItem.type == type_axe) {
                currentWeapon = selectedItem;
                attack = getAttack();
                getPlayerAttackImage();
            }
            if (selectedItem.type == type_shield) {
                currentShield = selectedItem;
                defense = getDefense();
            }
            if (selectedItem.type == type_consumable) {
                selectedItem.use(this);
                inventory.remove(itemIndex);

            }

        }
    }

    public void pickUpObject(int i) {
        if (i != 999) {
            String text;
            if (inventory.size() != maxInventorySize) {
                inventory.add(gp.obj[i]);
                gp.playSE(1);
                text = "Got a " + gp.obj[i].name + "!";
            } else {
                text = "You can't carry any more!";
            }
            gp.ui.addMessage(text);
            gp.obj[i] = null;
        }
    }

    public void interactNPC(int i) {
        // if (gp.keyH.enterPressed == true) {
        // if (i != 999) {
        // attackCanceled = true;
        // gp.gameState = gp.dialogueState;
        // gp.npc[i].speak();
        // }
        // }

        if (gp.keyH.enterPressed == true) {
            if (i != 999) {
                // attackCanceled = true;
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            } else {
                attacking = true;

            }
        }
    }

    public void contactMonster(int i) {
        if (i != 999) {
            if (invincible == false && gp.monster[i].dying == false) {
                gp.playSE(6);

                int damage = gp.monster[i].attack - defense;
                if (damage < 0) {
                    damage = 0;
                }

                life -= damage;
                gp.monster[i].life -= 1;
                gp.monster[i].invincible = true;
                if (gp.monster[i].life <= 0) {
                    gp.monster[i] = null;
                }
            }
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;
        // xu ly animation nhan vat
        switch (direction) {
            case "up":
                if (attacking == false) {
                    if (spiteNum == 1) {
                        image = up1;
                    }
                    if (spiteNum == 2) {
                        image = up2;
                    }
                }
                if (attacking == true) {
                    tempScreenY = screenY - gp.tileSize;
                    if (spiteNum == 1) {
                        image = attackUp1;
                    }
                    if (spiteNum == 2) {
                        image = attackUp2;
                    }
                }
                break;
            case "down":
                if (attacking == false) {
                    if (spiteNum == 1) {
                        image = down1;
                    }
                    if (spiteNum == 2) {
                        image = down2;
                    }
                }
                if (attacking == true) {
                    if (spiteNum == 1) {
                        image = attackDown1;
                    }
                    if (spiteNum == 2) {
                        image = attackDown2;
                    }
                }
                break;
            case "left":
                if (attacking == false) {
                    if (spiteNum == 1) {
                        image = left1;
                    }
                    if (spiteNum == 2) {
                        image = left2;
                    }
                }
                if (attacking == true) {
                    tempScreenX = screenX - gp.tileSize;
                    if (spiteNum == 1) {
                        image = attackLeft1;
                    }
                    if (spiteNum == 2) {
                        image = attackLeft2;
                    }

                }
                break;
            case "right":
                if (attacking == false) {
                    if (spiteNum == 1) {
                        image = right1;
                    }
                    if (spiteNum == 2) {
                        image = right2;
                    }
                }
                if (attacking == true) {
                    if (spiteNum == 1) {
                        image = attackRight1;
                    }
                    if (spiteNum == 2) {
                        image = attackRight2;
                    }
                }
                break;
            default:
                break;
        }
        if (invincible == true) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
        }
        g2.drawImage(image, tempScreenX, tempScreenY, null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }

}
