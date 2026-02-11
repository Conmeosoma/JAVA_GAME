package Object;

import Enity.Entity;
import Main.GamePanel;

public class OBJ_Potion_Red extends Entity {
  GamePanel gp;
//  int value = 5;

  public OBJ_Potion_Red(GamePanel gp) {
    super(gp);
    this.gp = gp;
    type = type_consumable;
    name = "HP Potion";
    value = 5;
    down1 = setup("/res/Object/potion_red", 48, 48);
    description = "[" + name + "]\nMột lọ thuốc hồi phục 5 HP.";
  }

  public void use(Entity entity) {
    gp.gameState = gp.dialogueState;
    gp.ui.currentDialogue = "Bạn uống \n" + name + ".\nHP của bạn đã được \nhồi phục " + value + "HP.";
    entity.life += value;
//    if (gp.player.life > gp.player.maxLife) {
//      gp.player.life = gp.player.maxLife;
//    }
    gp.playSE(2);
  }
}
