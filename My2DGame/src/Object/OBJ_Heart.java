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

    GamePanel gp;

    public OBJ_Heart(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_pickupOnly;
        value = 2;
        name = "Heart";
        down1 = setup("/res/Object/heart_full", gp.tileSize, gp.tileSize);
        image = setup("/res/Object/heart_full", gp.tileSize, gp.tileSize);
        image2 = setup("/res/Object/heart_half", gp.tileSize, gp.tileSize);
        image3 = setup("/res/Object/heart_blank", gp.tileSize, gp.tileSize);

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

    public void use(Entity entity) {
        gp.playSE(2);
        gp.ui.addMessage("Life +" + value);
        entity.life += value;
//        if (entity.life > entity.maxLife) {
//            entity.life = entity.maxLife;
//        }
    }
}
