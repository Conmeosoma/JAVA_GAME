// CodeByConMeoSoMa
// /\_/\  
//( o.o ) 
// > ^ <
package Object;

import Enity.Entity;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;

//public class OBJ_Chest extends SuperObject {
public class OBJ_Chest extends Entity {

//  GamePanel gp;

  public OBJ_Chest(GamePanel gp) {
    super(gp);
    name = "Chest";
    down1 = setup("/res/Object/Chest");
//    this.gp = gp;
//    try {
//      image = ImageIO.read(getClass().getResourceAsStream("/res/Object/Chest.png"));
//      image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
//    } catch (IOException e) {
//      // TODO: handle exception
//      e.printStackTrace();
//    }
  }
}
