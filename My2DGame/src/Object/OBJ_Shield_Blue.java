package Object;

import Enity.Entity;
import Main.GamePanel;

public class OBJ_Shield_Blue extends Entity {

  public OBJ_Shield_Blue(GamePanel gp) {
    super(gp);
    type = type_shield;
    name = "Blue Shield";
    down1 = setup("/res/Object/shield_blue", gp.tileSize, gp.tileSize);
    defenseValue = 1;
    description = "[" + name + "]\nLàm bằng HDPE.";

  }

}
