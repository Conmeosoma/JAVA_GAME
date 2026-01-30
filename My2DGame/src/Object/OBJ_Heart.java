// CodeByConMeoSoMa
// /\_/\  
//( o.o ) 
// > ^ <
package Object;

import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class OBJ_Heart extends SuperObject {
  GamePanel gp;

  public OBJ_Heart(GamePanel gp) {
    name = "Heart";
    this.gp = gp;
    try {
      image = ImageIO.read(getClass().getResourceAsStream("/res/Object/heart_full.png"));
      image2 = ImageIO.read(getClass().getResourceAsStream("/res/Object/heart_half.png"));
      image3 = ImageIO.read(getClass().getResourceAsStream("/res/Object/heart_blank.png"));
      image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
      image2 = uTool.scaleImage(image2, gp.tileSize, gp.tileSize);
      image3 = uTool.scaleImage(image3, gp.tileSize, gp.tileSize);
    } catch (IOException e) {
      e.printStackTrace();
    }
    collision = true;
  }

}
