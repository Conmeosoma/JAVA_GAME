// CodeByConMeoSoMa
// /\_/\  
//( o.o ) 
// > ^ <
package Object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Chest extends SuperObject {
  public OBJ_Chest() {
    name = " Chest";
    try {
      image = ImageIO.read(getClass().getResource("/res/Object/Chest.png"));
    } catch (IOException e) {
      // TODO: handle exception
      e.printStackTrace();
    }
  }
}
