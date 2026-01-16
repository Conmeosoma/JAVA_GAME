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

import Enity.Player;
import Object.SuperObject;
import Tile.Tile;
import Tile.TileManager;

// GamePanel co tac dung lam noi ve tat ca cac thanh phan trong game
// No se chua cac thanh phan nhu tile,player,entity,object,collision checker,asser setter ...
public class GamePanel extends JPanel implements Runnable { // khai bao lop GamePanel ke thua JPanel
    // Screen settings

    final int originalTileSize = 16; // kich thuoc o ban dau 16 * 16
    final int scale = 3; // ti le phong to 3 lan
    public final int tileSize = originalTileSize * scale; // kich thuoc o hien tai 48 * 48
    public final int maxScreenCol = 16; // gioi han so cot cua man hinh
    public final int maxScreenRow = 12; // gioi han so hang cua man hinh
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixel
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixel
    KeyHandler keyH = new KeyHandler();// khoi tao doi tuong KeyHandler de bat su kien phim

    // World settings
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxScreenCol;
    public final int worldHeight = tileSize * maxScreenRow;

    // FPS
    int FPS = 60;
    TileManager tileM = new TileManager(this);
    Thread gamThread; // khai bao doi tuong thread cho game
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[10];

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

    public void setupGame() { // Ham setupGame de cai dat ban dau cho game
        aSetter.setObject(); // dat cac doi tuong trong game
    }

    public void startGameThread() {
        gamThread = new Thread(this);// khoi tao thread
        gamThread.start();// bat dau thread
    }

    // @Override // Cach 1: Sleep method
    // public void run() {
    // double drawInterval = 1000000000 / FPS; // thoi gian giua 2 lan ve lien tiep
    // ( nano giay) 0.16666667 giay
    // double nextDrawTime = System.nanoTime() + drawInterval; // thoi gian ve lan
    // tiep theo
    // // phuong thuc chay cua thread
    // while (gamThread != null) {
    // // long currentTime = System.nanoTime(); // 1000000000 nan giay = 1 giay
    // // long currentTime2 = System.currentTimeMillis(); // 1000 miligiay = 1 giay
    // // System.out.println("NanoTime: " + currentTime);
    // // 1.Update: Update information such as character positions || Nâng cấp: Cập
    // // nhật vị trí nhân vật
    // updated(); // goi pt update
    // // 2. Draw: Draw the screen with the updated information || Vẽ : Vẽ màn hình
    // với
    // // thông tin đã cập nhật
    // repaint(); // goi phuong thuc repaint goi den paintComponent
    // try {
    // double remainingTime = nextDrawTime - System.nanoTime(); // tinh thoi gian
    // con lai de dat ngu
    // remainingTime = remainingTime / 1000000;// chuyen nanos sang milis
    // if (remainingTime < 0) {
    // remainingTime = 0; // neu thoi gian con lai am thi dat bang 0
    // }
    // Thread.sleep((long) (remainingTime)); // cho thread ngu trong phan thoi gian
    // con lai
    // nextDrawTime += drawInterval; // cap nhat thoi gian ve lan tiep theo
    // } catch (Exception e) {
    // }
    // }
    // }
    @Override // Cach 2: Delta/Accumulator method
    public void run() {
        double drawInterval = 1000000000 / FPS; // thoi gian giua 2 lan ve lien tiep
        double delta = 0; // bien tich luy
        long lastTime = System.nanoTime(); // thoi gian hien tai
        long currentTime; // thoi gian hien tai
        long timer = 0;
        int drawCount = 0;

        while (gamThread != null) {
            currentTime = System.nanoTime(); // cap nhat thoi gian hien tai
            delta += (currentTime - lastTime) / drawInterval; // tinh toan delta

            timer += (currentTime - lastTime); // cap nhat thoi gian dem
            lastTime = currentTime; // cap nhat lai thoi gian cu thanh thoi gian moi
            if (delta >= 1) { // neu delta lon hon hoac bang 1 thi co the update va ve
                updated();
                repaint();
                delta--;
                drawCount++;
            }
            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }

        }

    }

    public void updated() {// phuong thuc update
        player.update();

    }

    public void paintComponent(Graphics g) { // Phuong thuc ve len panel
        super.paintComponent(g); // goi phuong thuc paintComponent cua lop cha JPanel
        Graphics2D g2 = (Graphics2D) g; // ep kieu doi tuong g thanh Graphics2D de su dung cac tinh nang nang cao
        // tile
        tileM.draw(g2);
        // player
        player.draw(g2);
        // object
        for (int i = 0; i < obj.length; i++) {
            if (obj[i] != null) {
                obj[i].draw(g2, this);
            }
        }

        g2.dispose();// giai phong bo nho cho doi tuong g2

    }

}