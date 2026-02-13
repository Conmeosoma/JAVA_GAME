// CodeByConMeoSoMa
// /\_/\  
//( o.o ) 
// > ^ <
package Object;

import Enity.Entity;
import Main.GamePanel;

//public class OBJ_Door extends SuperObject {
public class OBJ_Door extends Entity {

    // GamePanel gp;
    public OBJ_Door(GamePanel gp) {
        super(gp);
        name = "Door";
        down1 = setup("/res/Object/door", gp.tileSize, gp.tileSize);
        // this.gp = gp;
        // try {
        // image = ImageIO.read(getClass().getResourceAsStream("/res/Object/door.png"));
        // image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        // } catch (IOException e) {
        // // TODO: handle exception
        // e.printStackTrace();
        // }
        collision = true;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}
