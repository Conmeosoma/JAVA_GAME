package Main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class UtilityTool {
  // Class tao ra de xu ly nhung cong viec phuc tap nhu phong to thu nho anh
  // tao ra nhung ham tien ich de su dung cho cac class khac
  public BufferedImage scaleImage(BufferedImage original, int width, int height) {
    BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
    // tao anh moi voi kich thuoc moi
    Graphics2D g2 = scaledImage.createGraphics();
    // tao doi tuong graphic2d de ve tren anh moi
    g2.drawImage(original, 0, 0, width, height, null);
    // ve anh goc len anh moi voi kich thuoc moi
    g2.dispose(); // giai phong bo nho
    return scaledImage;
  }
}
