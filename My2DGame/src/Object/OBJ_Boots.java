/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Object;

import java.io.IOException;
import javax.imageio.ImageIO;

import Main.GamePanel;

/**
 *
 * @author dieu hoang
 */
public class OBJ_Boots extends SuperObject {
  GamePanel gp;

  public OBJ_Boots(GamePanel gp) {
    name = "Boots";
    this.gp = gp;
    try {
      image = ImageIO.read(getClass().getResourceAsStream("/res/Object/boots.png"));
      image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
    } catch (IOException e) {
      // TODO: handle exception
      e.printStackTrace();
    }
  }
}
