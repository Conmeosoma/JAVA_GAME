// CodeByConMeoSoMa
// /\_/\  
//( o.o ) 
// > ^ <
package Object;

import Enity.Entity;
import Main.GamePanel;

//public class OBJ_Key extends SuperObject {
public class OBJ_Key extends Entity {

  // GamePanel gp;

  public OBJ_Key(GamePanel gp) {
    super(gp);
    name = "Key";
    // this.gp = gp;

    down1 = setup("/res/Object/key", gp.tileSize, gp.tileSize);
    // try {
    // image = ImageIO.read(getClass().getResourceAsStream("/res/Object/key.png"));
    // image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
    // } catch (IOException e) {
    // e.printStackTrace();
    // }
    solidArea.x = 5;
    solidArea.y = 14;

  }
}
