package Object;

import Entity.Entity;
import Main.GamePanel;
public class OBJ_Sword_Normal extends Entity {

    public static final String objName = "Normal Sword";

    public OBJ_Sword_Normal(GamePanel gp) {
        super(gp);

        type = type_sword;
        name = objName;
        down1 = setup("/res/objects/sword_normal",gp.tileSize, gp.tileSize);
        attackValue = 1;
        attackArea.width = 36;
        attackArea.height= 36;
        description = "[" + name + "]\nAn old sword.";
        price = 30;
        knockBackPower = 3;
        motion1_duration = 5;
        motion2_duration = 25;
    }
}
