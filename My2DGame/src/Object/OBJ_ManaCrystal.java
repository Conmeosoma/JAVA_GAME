package Object;

import Enity.Entity;
import Main.GamePanel;

/**
 *
 * @author dieu hoang
 */
public class OBJ_ManaCrystal extends Entity {

    GamePanel gp;
    public OBJ_ManaCrystal(GamePanel gp){
        super(gp);
        this.gp = gp;
        
        name = "Mana Crystal";
        image = setup("/res/Object/manacrystal_full", gp.tileSize, gp.tileSize);
        image2 = setup("/res/Object/manacrystal_blank", gp.tileSize, gp.tileSize);
    }

}
