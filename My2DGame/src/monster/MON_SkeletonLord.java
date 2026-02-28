package Monster;

import Entity.Entity;
import Main.GamePanel;
import Object.OBJ_Coin_Bronze;
import Object.OBJ_Door_Iron;
import Object.OBJ_Heart;
import Object.OBJ_ManaCrystal;

import java.util.Random;

import Data.Progress;

public class MON_SkeletonLord extends Entity {
    GamePanel gp; // cuz of different package
    public static final String monName = "Skeleton Lord";

    public MON_SkeletonLord(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = type_monster;
        boss = true;
        name = monName;
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxLife = 40;
        life = maxLife;
        attack = 8;
        defense = 2;
        exp = 40;
        knockBackPower = 5;
        sleep = true;

        int size = gp.tileSize * 5;
        solidArea.x = 48;
        solidArea.y = 48;
        solidArea.width = size - 48 * 2;
        solidArea.height = size - 48;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        attackArea.width = 170;
        attackArea.height = 170;
        motion1_duration = 25;
        motion2_duration = 50;

        getImage();
        getAttackImage();
        setDialogue();
    }

    public void getImage() {

        int i = 5;
        if (inRage == false) {
            up1 = setup("/res/monster/skeletonlord_up_1", gp.tileSize * i, gp.tileSize * i);
            up2 = setup("/res/monster/skeletonlord_up_2", gp.tileSize * i, gp.tileSize * i);
            down1 = setup("/res/monster/skeletonlord_down_1", gp.tileSize * i, gp.tileSize * i);
            down2 = setup("/res/monster/skeletonlord_down_2", gp.tileSize * i, gp.tileSize * i);
            left1 = setup("/res/monster/skeletonlord_left_1", gp.tileSize * i, gp.tileSize * i);
            left2 = setup("/res/monster/skeletonlord_left_2", gp.tileSize * i, gp.tileSize * i);
            right1 = setup("/res/monster/skeletonlord_right_1", gp.tileSize * i, gp.tileSize * i);
            right2 = setup("/res/monster/skeletonlord_right_2", gp.tileSize * i, gp.tileSize * i);
        }
        if (inRage == true) {
            up1 = setup("/res/monster/skeletonlord_phase2_up_1", gp.tileSize * i, gp.tileSize * i);
            up2 = setup("/res/monster/skeletonlord_phase2_up_2", gp.tileSize * i, gp.tileSize * i);
            down1 = setup("/res/monster/skeletonlord_phase2_down_1", gp.tileSize * i, gp.tileSize * i);
            down2 = setup("/res/monster/skeletonlord_phase2_down_2", gp.tileSize * i, gp.tileSize * i);
            left1 = setup("/res/monster/skeletonlord_phase2_left_1", gp.tileSize * i, gp.tileSize * i);
            left2 = setup("/res/monster/skeletonlord_phase2_left_2", gp.tileSize * i, gp.tileSize * i);
            right1 = setup("/res/monster/skeletonlord_phase2_right_1", gp.tileSize * i, gp.tileSize * i);
            right2 = setup("/res/monster/skeletonlord_phase2_right_2", gp.tileSize * i, gp.tileSize * i);
        }
    }

    public void getAttackImage() {

        int i = 5;

        if (inRage == false) {
            attackUp1 = setup("/res/monster/skeletonlord_attack_up_1", gp.tileSize * i, gp.tileSize * 2 * i);
            attackUp2 = setup("/res/monster/skeletonlord_attack_up_2", gp.tileSize * i, gp.tileSize * 2 * i);
            attackDown1 = setup("/res/monster/skeletonlord_attack_down_1", gp.tileSize * i, gp.tileSize * 2 * i);
            attackDown2 = setup("/res/monster/skeletonlord_attack_down_2", gp.tileSize * i, gp.tileSize * 2 * i);
            attackLeft1 = setup("/res/monster/skeletonlord_attack_left_1", gp.tileSize * 2 * i, gp.tileSize * i);
            attackLeft2 = setup("/res/monster/skeletonlord_attack_left_2", gp.tileSize * 2 * i, gp.tileSize * i);
            attackRight1 = setup("/res/monster/skeletonlord_attack_right_1", gp.tileSize * 2 * i, gp.tileSize * i);
            attackRight2 = setup("/res/monster/skeletonlord_attack_right_2", gp.tileSize * 2 * i, gp.tileSize * i);
        }
        if (inRage == true) {
            attackUp1 = setup("/res/monster/skeletonlord_phase2_attack_up_1", gp.tileSize * i, gp.tileSize * 2 * i);
            attackUp2 = setup("/res/monster/skeletonlord_phase2_attack_up_2", gp.tileSize * i, gp.tileSize * 2 * i);
            attackDown1 = setup("/res/monster/skeletonlord_phase2_attack_down_1", gp.tileSize * i, gp.tileSize * 2 * i);
            attackDown2 = setup("/res/monster/skeletonlord_phase2_attack_down_2", gp.tileSize * i, gp.tileSize * 2 * i);
            attackLeft1 = setup("/res/monster/skeletonlord_phase2_attack_left_1", gp.tileSize * 2 * i, gp.tileSize * i);
            attackLeft2 = setup("/res/monster/skeletonlord_phase2_attack_left_2", gp.tileSize * 2 * i, gp.tileSize * i);
            attackRight1 = setup("/res/monster/skeletonlord_phase2_attack_right_1", gp.tileSize * 2 * i,
                    gp.tileSize * i);
            attackRight2 = setup("/res/monster/skeletonlord_phase2_attack_right_2", gp.tileSize * 2 * i,
                    gp.tileSize * i);
        }
    }

    public void setDialogue() {
        dialogues[0][0] = "No one can steal my treasure.";
        dialogues[0][1] = "You will die here.";
        dialogues[0][2] = "WELCOME TO YOUR DOOM!";

    }

    public void setAction() {

        if (inRage == false && life < maxLife / 2) {
            inRage = true;
            getImage();
            getAttackImage();
            defaultSpeed += 2;
            speed = defaultSpeed;
            attack *= 2; // 8 -> 16

            // Summon 3 GreenSlime minions
            summonMinions(3);
        }
        if (getTileDistance(gp.player) < 10) {
            // More aggressive chase in rage mode
            moveTowardPlayer(inRage ? 30 : 60);
        } else {
            getRandomDirection(120);
        }

        // Check if it attacks - more aggressive in rage
        if (attacking == false) {
            if (inRage) {
                checkAttackOrNot(30, gp.tileSize * 8, gp.tileSize * 6); // Very aggressive
            } else {
                checkAttackOrNot(60, gp.tileSize * 7, gp.tileSize * 5);
            }
        }
    }

    public void damageReaction() {
        actionLockCounter = 0;
    }

    // Summon minions around the boss
    private void summonMinions(int count) {
        int spawned = 0;
        for (int i = 0; i < gp.monster[gp.currentMap].length && spawned < count; i++) {
            if (gp.monster[gp.currentMap][i] == null) {
                gp.monster[gp.currentMap][i] = new MON_GreenSlime(gp);
                // Spawn around boss position with offset
                int offsetX = (spawned - 1) * gp.tileSize * 3; // -3, 0, +3 tiles
                int offsetY = gp.tileSize * 4;
                gp.monster[gp.currentMap][i].worldX = worldX + offsetX;
                gp.monster[gp.currentMap][i].worldY = worldY + offsetY;
                spawned++;
            }
        }
    }

    @Override
    public void attacking() {
        spriteCounter++;

        if (spriteCounter <= motion1_duration) {
            spriteNum = 1;
        }
        if (spriteCounter > motion1_duration && spriteCounter <= motion2_duration) {
            spriteNum = 2;

            // Save the current worldX, worldY, solidArea
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            // Use the ACTUAL boss sprite size (5 tiles) instead of 1 tile
            int bossSize = gp.tileSize * 5;

            // Adjust worldX/worldY for the attackArea
            switch (direction) {
                case "up":
                    worldY -= attackArea.height;
                    break;
                case "down":
                    worldY += bossSize;
                    break;
                case "left":
                    worldX -= attackArea.width;
                    break;
                case "right":
                    worldX += bossSize;
                    break;
            }

            // attackArea becomes solidArea
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            if (gp.cChecker.checkPlayer(this) == true) {
                // Cap swing damage to prevent instant kill
                int cappedAttack = attack;
                int rawDamage = cappedAttack - gp.player.defense;
                int maxSwingDamage = gp.player.maxLife * 2 / 3; // Cap at 2/3 of player max HP
                if (rawDamage > maxSwingDamage) {
                    cappedAttack = maxSwingDamage + gp.player.defense;
                }
                damagePlayer(cappedAttack);
            }

            // After checking collision, restore the original data
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }
        if (spriteCounter > motion2_duration) {
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }

    public void checkDrop() {
        gp.bossBattleOn = false;
        Progress.skeletonLordDefeated = true;

        // Restore the previous music
        gp.stopMusic();
        gp.playMusic(19);

        // Remove the iron doors
        for (int i = 0; i < gp.obj[1].length; i++) {
            if (gp.obj[gp.currentMap][i] != null && gp.obj[gp.currentMap][i].name.equals(OBJ_Door_Iron.objName)) {
                gp.playSE(21);
                gp.obj[gp.currentMap][i] = null;
            }
        }

        // CAST A DIE
        int i = new Random().nextInt(100) + 1;

        // SET THE MONSTER DROP
        if (i < 50) {
            dropItem(new OBJ_Coin_Bronze(gp));
        }
        if (i >= 50 && i < 75) {
            dropItem(new OBJ_Heart(gp));
        }
        if (i >= 75 && i < 100) {
            dropItem(new OBJ_ManaCrystal(gp));
        }
    }
}
