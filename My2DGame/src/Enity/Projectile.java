package Enity;

import Main.GamePanel;

public class Projectile extends Entity {

    Entity user;

    public Projectile(GamePanel gp) {
        super(gp);
    }

    public void set(int worldX, int worldY, String direction, boolean alive, Entity user) {
        this.World_X = worldX;
        this.World_Y = worldY;
        this.direction = direction;
        this.alive = alive;
        this.user = user;
        this.life = this.maxLife;
    }

    public void update() {
        if (user == gp.player) {
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            if (monsterIndex != 999) {
                gp.player.damageMonster(monsterIndex, attack);
                generateParticle(user.projectile, gp.monster[monsterIndex]);
                alive = false;
            }
        }

        if (user != gp.player) {
            boolean contactPlayer = gp.cChecker.checkPlayer(this);
            if (gp.player.invincible == false && contactPlayer == true) {
                damagePlayer(attack);
                generateParticle(user.projectile, gp.player);
                alive = false;
            }
        }

        switch (direction) {
            case "up":
                World_Y -= speed;
                break;
            case "down":
                World_Y += speed;
                break;
            case "left":
                World_X -= speed;
                break;
            case "right":
                World_X += speed;
                break;
        }

        life--;
        if (life <= 0) {
            alive = false;
        }

        spiteCounter++;
        if (spiteCounter > 12) {
            if (spiteNum == 1) {
                spiteNum = 2;
            } else if (spiteNum == 2) {
                spiteNum = 1;
            }
            spiteCounter = 0;
        }
    }

    public boolean haveResource(Entity user) {
        boolean haveResource = false;
        return haveResource;
    }

    public void subtractResources(Entity user) {}
}
