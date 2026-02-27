package Tile_interactive;

import Main.GamePanel;

/**
 *
 * @author dieu hoang
 */
public class IT_Trunk extends InteractiveTile {

    GamePanel gp;

    public IT_Trunk(GamePanel gp, int col, int row) {
        super(gp, row, col);
        this.gp = gp;

        this.worldX = gp.tileSize * col;
        this.worldY = gp.tileSize * row;

        down1 = setup("/res/tiles_interactive/trunk", gp.tileSize, gp.tileSize);

        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 0;
        solidArea.height = 0;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}
