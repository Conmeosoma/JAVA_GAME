// CodeByConMeoSoMa
// /\_/\  
//( o.o ) 
// > ^ <
package Object;

import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class OBJ_Door extends SuperObject {
  GamePanel gp;

  public OBJ_Door(GamePanel gp) {
    name = "Door";
    this.gp = gp;
    try {
      image = ImageIO.read(getClass().getResourceAsStream("/res/Object/door.png"));
      image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
    } catch (IOException e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    collision = true;
  }

}
