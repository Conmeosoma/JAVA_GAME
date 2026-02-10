/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Object;

import Enity.Entity;

import Main.GamePanel;

/**
 *
 * @author dieu hoang
 */
//public class OBJ_Boots extends SuperObject {
public class OBJ_Boots extends Entity {

//  GamePanel gp;
    public OBJ_Boots(GamePanel gp) {
        super(gp);
        name = "Boots";
        down1 = setup("/res/Object/boots", gp.tileSize, gp.tileSize);
//    this.gp = gp;
//    try {
//      image = ImageIO.read(getClass().getResourceAsStream("/res/Object/boots.png"));
//      image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
//    } catch (IOException e) {
//      // TODO: handle exception
//      e.printStackTrace();
//    }
    }
}
