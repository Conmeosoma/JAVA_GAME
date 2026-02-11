package tile_interactive;

import Enity.Entity;
import Main.GamePanel;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author dieu hoang
 */
public class InteractiveTile extends Entity {

    GamePanel gp;
    public boolean destructible = false;

    public InteractiveTile(GamePanel gp, int col, int row) {
        super(gp);
        this.gp = gp;
    }

    public boolean isCorrectItem(Entity entity) {
        boolean isCorrectItem = false;
        return isCorrectItem;
    }

    public void playSE() {

    }

    public InteractiveTile getDestroyedForm() {
        InteractiveTile tile = null;
        return tile;
    }

    public void update() {
        if (invincible == true) {
            invincibleCounter++;
            if (invincibleCounter > 20) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2) {
        int screenX = World_X - gp.player.World_X + gp.player.screenX;
        int screenY = World_Y - gp.player.World_Y + gp.player.screenY;
        if (World_X + gp.tileSize > gp.player.World_X - gp.player.screenX
                && World_X - gp.tileSize < gp.player.World_X + gp.player.screenX
                && World_Y + gp.tileSize > gp.player.World_Y - gp.player.screenY
                && World_Y - gp.tileSize < gp.player.World_Y + gp.player.screenY) {
            g2.drawImage(down1, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }
}
