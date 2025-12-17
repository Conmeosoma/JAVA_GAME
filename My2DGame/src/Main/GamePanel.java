// CodeByConMeoSoMa
// /\_/\  
//( o.o ) 
// > ^ <
package Main;

import java.awt.Dimension;

import javax.swing.JPanel;

public class GamePanel extends JPanel { // khai bao lop GamePanel ke thua JPanel
  // Screen settings
  final int originalTileSize = 16; // kich thuoc o ban dau 16 * 16
  final int scale = 3; // ti le phong to 3 lan
  final int tileSize = originalTileSize * scale; // kich thuoc o hien tai 48 * 48
  final int maxScreenCol = 16; // gioi han so cot cua man hinh
  final int maxScreenRow = 12; // gioi han so hang cua man hinh
  final int screenWidth = tileSize * maxScreenCol; // 768 pixel
  final int screenHeight = tileSize * maxScreenRow; // 576 pixel

  public GamePanel() {
    this.setPreferredSize(new Dimension(screenWidth, screenHeight));
  }

}
