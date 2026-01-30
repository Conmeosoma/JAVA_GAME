// CodeByConMeoSoMa
// /\_/\  
//( o.o ) 
// > ^ <
package Object;

import Enity.Entity;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;

//public class OBJ_Heart extends SuperObject {
public class OBJ_Heart extends Entity {

//  GamePanel gp;

  public OBJ_Heart(GamePanel gp) {
    super(gp);
    name = "Heart";
    image = setup("/res/Object/heart_full");    
    image2 = setup("/res/Object/heart_half");
    image3 = setup("/res/Object/heart_blank");

//    this.gp = gp;
//    try {
//      image = ImageIO.read(getClass().getResourceAsStream("/res/Object/heart_full.png"));
//      image2 = ImageIO.read(getClass().getResourceAsStream("/res/Object/heart_half.png"));
//      image3 = ImageIO.read(getClass().getResourceAsStream("/res/Object/heart_blank.png"));
//      image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
//      image2 = uTool.scaleImage(image2, gp.tileSize, gp.tileSize);
//      image3 = uTool.scaleImage(image3, gp.tileSize, gp.tileSize);
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
    collision = true;
  }

}
