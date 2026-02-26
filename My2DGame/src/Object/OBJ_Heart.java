package Object;

import Entity.Entity;
import Main.GamePanel;
public class OBJ_Heart extends Entity {
    GamePanel gp;
    public static final String objName = "Heart";

    public OBJ_Heart(GamePanel gp)
    {
        super(gp);
        this.gp = gp;
        type = type_pickupOnly;
        name = objName;
        value = 2;
        down1  = setup("/res/objects/heart_full",gp.tileSize,gp.tileSize); //Entity's draw method will draw it.
        image = setup("/res/objects/heart_full",gp.tileSize,gp.tileSize);
        image2 = setup("/res/objects/heart_half",gp.tileSize,gp.tileSize);
        image3 = setup("/res/objects/heart_blank",gp.tileSize,gp.tileSize);
        price = 175;
    }
    public boolean use(Entity entity)
    {
        gp.playSE(2);
        gp.ui.addMessage("Life +" + value);
        entity.life += value;
        return true;
    }
}
