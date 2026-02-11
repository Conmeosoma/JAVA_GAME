package tile_interactive;

import Enity.Entity;
import Main.GamePanel;

/**
 *
 * @author dieu hoang
 */
public class IT_DryTree extends InteractiveTile {

    GamePanel gp;

    public IT_DryTree(GamePanel gp, int col, int row) {
        super(gp, row, col);
        this.gp = gp;

        this.World_X = gp.tileSize * col;
        this.World_Y = gp.tileSize * row;

        down1 = setup("/res/tiles_interactive/drytree", gp.tileSize, gp.tileSize);
        destructible = true;
        life = 3;
    }

    public boolean isCorrectItem(Entity entity) {
        boolean isCorrectItem = false;

        if (entity.currentWeapon.type == type_axe) {
            isCorrectItem = true;
        }

        return isCorrectItem;
    }

    public void playSE() {
        gp.playSE(11);
    }

    public InteractiveTile getDestroyedForm() {
        InteractiveTile tile = new IT_Trunk(gp, World_X / gp.tileSize, World_Y / gp.tileSize);
        return tile;
    }
}
