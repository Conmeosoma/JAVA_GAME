package Object;

import Enity.Entity;
import Main.GamePanel;

public class OBJ_Weapon_Normal extends Entity {

  public OBJ_Weapon_Normal(GamePanel gp) {
    super(gp);
    name = "Normal Weapon";
    down1 = setup("/res/Object/sword_normal", gp.tileSize, gp.tileSize);
    attackValue = 1;
  }
}
