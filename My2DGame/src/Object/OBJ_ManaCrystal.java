package Object;

import Enity.Entity;
import Main.GamePanel;

/**
 *
 * @author dieu hoang
 */
public class OBJ_ManaCrystal extends Entity {

    GamePanel gp;

    public OBJ_ManaCrystal(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_pickupOnly;
        value = 1;
        name = "Mana Crystal";
        down1 = setup("/res/Object/manacrystal_full", gp.tileSize, gp.tileSize);
        image = setup("/res/Object/manacrystal_full", gp.tileSize, gp.tileSize);
        image2 = setup("/res/Object/manacrystal_blank", gp.tileSize, gp.tileSize);
    }

    public void use(Entity entity) {
        gp.playSE(2);
        gp.ui.addMessage("Mana +" + value);
        entity.mana += value;
    }
}
