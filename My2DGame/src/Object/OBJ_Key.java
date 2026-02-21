// CodeByConMeoSoMa
// /\_/\  
//( o.o ) 
// > ^ <
package Object;

import Enity.Entity;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class OBJ_Key extends Entity {

    public OBJ_Key(GamePanel gp) {
        super(gp);
        name = "Key";

        down1 = setup("/res/Object/key", gp.tileSize, gp.tileSize);
        solidArea.x = 5;
        solidArea.y = 14;
        description = "[" + name + "]\nDùng nó để mở cửa.";
        price = 100;
    }
}
