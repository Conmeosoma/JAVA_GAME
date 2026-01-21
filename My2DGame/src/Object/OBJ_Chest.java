// CodeByConMeoSoMa
// /\_/\  
//( o.o ) 
// > ^ <
package Object;

import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class OBJ_Chest extends SuperObject {
  GamePanel gp;

  public OBJ_Chest(GamePanel gp) {
    name = "Chest";
    this.gp = gp;
    try {
      image = ImageIO.read(getClass().getResourceAsStream("/res/Object/Chest.png"));
      image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
    } catch (IOException e) {
      // TODO: handle exception
      e.printStackTrace();
    }
  }
}
