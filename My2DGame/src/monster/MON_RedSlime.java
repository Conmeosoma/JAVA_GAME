package Monster;

import Entity.Entity;
import Main.GamePanel;
import Object.OBJ_Coin_Bronze;
import Object.OBJ_Heart;
import Object.OBJ_ManaCrystal;
import Object.OBJ_Rock;

import java.util.Random;

public class MON_RedSlime extends Entity {

    GamePanel gp; // cuz of different package

    public MON_RedSlime(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = type_monster;
        name = "Red Slime";
        defaultSpeed = 2;
        speed = defaultSpeed;
        maxLife = 8;
        life = maxLife;
        attack = 2;
        defense = 0;
        exp = 4;
        projectile = new OBJ_Rock(gp);

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage() {
        up1 = setup("/res/monster/redslime_down_1", gp.tileSize, gp.tileSize);
        up2 = setup("/res/monster/redslime_down_2", gp.tileSize, gp.tileSize);
        down1 = setup("/res/monster/redslime_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/res/monster/redslime_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/res/monster/redslime_down_1", gp.tileSize, gp.tileSize);
        left2 = setup("/res/monster/redslime_down_2", gp.tileSize, gp.tileSize);
        right1 = setup("/res/monster/redslime_down_1", gp.tileSize, gp.tileSize);
        right2 = setup("/res/monster/redslime_down_2", gp.tileSize, gp.tileSize);
    }

    public void setAction() {
        if (onPath == true) {

            // Check if it stops chasing
            checkStopChasingOrNot(gp.player, 15, 100);

            // Search the direction to go
            searchPath(getGoalCol(gp.player), getGoalRow(gp.player));

            // Check if it shoots a projectile
            checkShootOrNot(200, 30);
        } else {
            // Check if it starts chasing
            checkStartChasingOrNot(gp.player, 5, 100);

            // Get a random direction
            getRandomDirection(120);
        }
    }

    public void damageReaction() {
        actionLockCounter = 0;
        // direction = gp.player.direction;
        onPath = true; // gets aggro
    }

    public void checkDrop() {
        // CAST A DIE
        int i = new Random().nextInt(100) + 1;

        // SET THE MONSTER DROP
        if (i < 40) {
            dropItem(new OBJ_Coin_Bronze(gp));
        }
        if (i >= 40 && i < 80) {
            dropItem(new OBJ_Heart(gp));
        }
        if (i >= 80 && i < 100) {
            dropItem(new OBJ_ManaCrystal(gp));
        }
    }
}
