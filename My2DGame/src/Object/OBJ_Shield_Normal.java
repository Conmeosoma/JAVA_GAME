package Object;

import Enity.Entity;
import Main.GamePanel;

public class OBJ_Shield_Normal extends Entity {

  public OBJ_Shield_Normal(GamePanel gp) {
    super(gp);
    name = "Normal Shield";
    down1 = setup("/res/Object/shield_wood", gp.tileSize, gp.tileSize);
    defenseValue = 1;
  }

}
