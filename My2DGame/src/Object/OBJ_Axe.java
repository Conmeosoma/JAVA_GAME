package Object;

import Enity.Entity;
import Main.GamePanel;

public class OBJ_Axe extends Entity {

  public OBJ_Axe(GamePanel gp) {
    super(gp);
    type = type_axe;
    name = "Woodcutter's Axe";
    down1 = setup("/res/Object/axe", gp.tileSize, gp.tileSize);
    attackValue = 2;
    attackArea.width = 30;
    attackArea.height = 36;
    description = "[Woodcutter's Axe]\n1 chiếc rìu rỉ sét.\nCó thể chặt cây";
    price = 75;
  }
}
