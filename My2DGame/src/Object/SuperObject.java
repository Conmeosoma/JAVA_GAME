// CodeByConMeoSoMa
// /\_/\  
//( o.o ) 
// > ^ <
package Object;

import java.nio.Buffer;

import Main.GamePanel;
import Main.UtilityTool;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class SuperObject {

  public BufferedImage image; // hinh anh cua doi tuong
  public String name;
  public boolean collision = false; // bien check va cham
  public int worldX, worldY; // toa do the gioi cua doi tuong
  public Rectangle solidArea = new Rectangle(0, 0, 48, 48); // khoi tao vung va cham mac dinh
  public int solidAreaDefaultX = 0; // toa do mac dinh vung va cham
  public int solidAreaDefaultY = 0; // toa do mac dinh vung va cham
  UtilityTool uTool = new UtilityTool();
  
  public void draw(Graphics2D g2, GamePanel gp) {
    // g2.drawImage(image, worldX, worldY, tileSize, tileSize, null);
    int screenX = worldX - gp.player.World_X + gp.player.screenX;
    // tọa độ cam màn hình X = tọa độ thế giới X - tọa độ thế giới nhân vật X + tọa
    // độ

    int screenY = worldY - gp.player.World_Y + gp.player.screenY;
    // tọa độ cam màn hình Y = tọa độ thế giới Y - tọa độ thế giới nhân vật Y + tọa
    // độ
    if (worldX + gp.tileSize > gp.player.World_X - gp.player.screenX &&
        worldX - gp.tileSize < gp.player.World_X + gp.player.screenX &&
        worldY + gp.tileSize > gp.player.World_Y - gp.player.screenY &&
        worldY - gp.tileSize < gp.player.World_Y + gp.player.screenY) {

      g2.drawImage(image, screenX, screenY, gp.tileSize,
          gp.tileSize, null);
    }
  }

}