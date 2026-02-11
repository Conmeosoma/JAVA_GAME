package Enity;

import Main.GamePanel;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author dieu hoang
 */
public class Particle extends Entity {

    Entity generator;
    Color color;
    int size;
    int xd;
    int yd;

    public Particle(GamePanel gp, Entity generator, Color color, int size, int speed, int maxLife, int xd, int yd) {
        super(gp);
        this.generator = generator;
        this.color = color;
        this.size = size;
        this.speed = speed;
        this.maxLife = maxLife;
        this.xd = xd;
        this.yd = yd;

        life = maxLife;
        int offset = (gp.tileSize / 2) - (size / 2);
        World_X = generator.World_X + offset;
        World_Y = generator.World_Y + offset;
    }

    public void update() {
        life--;
        
        if (life < maxLife / 3) {
            yd++;
        }
        
        World_X += xd * speed;
        World_Y += speed * yd;
        if (life == 0) {
            alive = false;
        }
    }

    public void draw(Graphics2D g2) {
        int screenX = World_X - gp.player.World_X + gp.player.screenX;
        int screenY = World_Y - gp.player.World_Y + gp.player.screenY;

        g2.setColor(color);
        g2.fillRect(screenX, screenY, size, size);
    }
}
