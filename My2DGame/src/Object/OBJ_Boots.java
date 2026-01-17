/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Object;

import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author dieu hoang
 */
public class OBJ_Boots extends SuperObject{
    public OBJ_Boots() {
    name = "Boots";
    try {
      image = ImageIO.read(getClass().getResource("/res/Object/boots.png"));
    } catch (IOException e) {
      // TODO: handle exception 
      e.printStackTrace();
    }
  }
}
