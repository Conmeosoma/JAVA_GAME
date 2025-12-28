// CodeByConMeoSoMa
// /\_/\  
//( o.o ) 
// > ^ <
package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable { // khai bao lop GamePanel ke thua JPanel
  // Screen settings
  final int originalTileSize = 16; // kich thuoc o ban dau 16 * 16
  final int scale = 3; // ti le phong to 3 lan
  final int tileSize = originalTileSize * scale; // kich thuoc o hien tai 48 * 48
  final int maxScreenCol = 16; // gioi han so cot cua man hinh
  final int maxScreenRow = 12; // gioi han so hang cua man hinh
  final int screenWidth = tileSize * maxScreenCol; // 768 pixel
  final int screenHeight = tileSize * maxScreenRow; // 576 pixel
  KeyHander keyH = new KeyHander();// khoi tao doi tuong KeyHandler de bat su kien phim
  // FPS
  int FPS = 60;

  Thread gamThread; // khai bao doi tuong thread cho game
  // Set nhan vat toa do mac dinh
  int playerX = 100;
  int playerY = 100;
  int playerSpeed = 4;

  public GamePanel() {
    this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // dat kich thuoc panel
    this.setBackground(Color.black); // dat mau nen den
    this.setDoubleBuffered(true); // cai dat double buffering de giam nhieu lan ve
    this.addKeyListener(keyH); // them doi tuong keyH vao de lang nghe su kien phim
    this.setFocusable(true); // panel co the nhan duoc su kien tu ban phim
  }

  public void startGameThread() {
    gamThread = new Thread(this);// khoi tao thread
    gamThread.start();// bat dau thread
  }

  @Override
  public void run() { // tuong tu trong unity thi co ham Update

    double drawInterval = 1000000000 / FPS; // thoi gian giua 2 lan ve lien tiep ( nano giay) 0.16666667 giay
    double nextDrawTime = System.nanoTime() + drawInterval; // thoi gian ve lan tiep theo

    // phuong thuc chay cua thread
    while (gamThread != null) {
      // long currentTime = System.nanoTime(); // 1000000000 nan giay = 1 giay
      // long currentTime2 = System.currentTimeMillis(); // 1000 miligiay = 1 giay
      // System.out.println("NanoTime: " + currentTime);
      // xu ly game theo FPS 2 cach
      // cach 1: "Sleep" method
      /*
      
      */

      // 1.Update: Update information such as character positions || Nâng cấp: Cập
      // nhật vị trí nhân vật
      updated(); // goi pt update
      // 2. Draw: Draw the screen with the updated information || Vẽ : Vẽ màn hình với
      // thông tin đã cập nhật
      repaint(); // goi phuong thuc repaint goi den paintComponent

    }
  }

  public void updated() {// phuong thuc update
    if (keyH.upPressed == true) {
      playerY -= playerSpeed;// di chuyen len tren
    } else if (keyH.downPressed == true) {
      playerY += playerSpeed; // di chuyen xuong duoi
    } else if (keyH.leftPressed == true) {
      playerX -= playerSpeed; // di chuyen sang trai
    } else if (keyH.rightPressed == true) {
      playerX += playerSpeed; // di chuyen sang phai
    }
    // tang 4 pixel sau moi lan update neu phim duoc nhan

  }

  public void paintComponent(Graphics g) { // Phuong thuc ve len panel
    super.paintComponent(g); // goi phuong thuc paintComponent cua lop cha JPanel
    Graphics2D g2 = (Graphics2D) g; // ep kieu doi tuong g thanh Graphics2D de su dung cac tinh nang nang cao
    g2.setColor(Color.white); // dat mau ve la trang
    // g2.fillRect(100, 100, tileSize, tileSize);// ve hinh chu nhat o vi tri
    // (100,100) voi kich thuoc tileSize * tileSize
    g2.fillRect(playerX, playerY, tileSize, tileSize); // ve hinh chu nhat o vi tri (playerX,playerY) voi kich
                                                       // thuoctileSize * tileSize
    g2.dispose();// giai phong bo nho cho doi tuong g2

  }

}
