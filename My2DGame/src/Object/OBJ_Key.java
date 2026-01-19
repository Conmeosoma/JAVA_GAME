// CodeByConMeoSoMa
// /\_/\  
//( o.o ) 
// > ^ <
package Object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Key extends SuperObject {
  public OBJ_Key() {
    name = "Key";
    try {
      image = ImageIO.read(getClass().getResourceAsStream("/res/Object/key.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    solidArea.x = 5;
    solidArea.y = 14;

  }
}
