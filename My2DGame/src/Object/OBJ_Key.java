// CodeByConMeoSoMa
// /\_/\  
//( o.o ) 
// > ^ <
package Object;

import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class OBJ_Key extends SuperObject {
  GamePanel gp;

  public OBJ_Key(GamePanel gp) {
    name = "Key";
    this.gp = gp;
    try {
      image = ImageIO.read(getClass().getResourceAsStream("/res/Object/key.png"));
      image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
    } catch (IOException e) {
      e.printStackTrace();
    }
    solidArea.x = 5;
    solidArea.y = 14;

  }
}
