package Object;

import java.nio.Buffer;

import Main.GamePanel;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class SuperObject {

  public BufferedImage image;
  public String name;
  public boolean collision = false;
  public int worldX, worldY;

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