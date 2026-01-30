// CodeByConMeoSoMa
// /\_/\  
//( o.o ) 
// > ^ <
package Main;

import Enity.Entity;
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

    // SCREEN SETTINGS
    final int originalTileSize = 16; // kich thuoc o ban dau 16 * 16
    final int scale = 3; // ti le phong to 3 lan
    public final int tileSize = originalTileSize * scale; // kich thuoc o hien tai 48 * 48
    public final int maxScreenCol = 16; // gioi han so cot cua man hinh
    public final int maxScreenRow = 12; // gioi han so hang cua man hinh
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixel
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixel

    // WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    // public final int worldWidth = tileSize * maxScreenCol;
    // public final int worldHeight = tileSize * maxScreenRow;

    // FPS
    int FPS = 60;

    // SYSTEM
    TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);// khoi tao doi tuong KeyHandler de bat su kien phim
    Sound music = new Sound();
    Sound se = new Sound();
    public EventHander eHander = new EventHander(this);
    Thread gamThread; // khai bao doi tuong thread cho game
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);

    // ENITY AND OBJECT
    public Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[10];
    public Entity npc[] = new Entity[10];

    // GAME STATES
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;

    // PLAYER
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
        aSetter.setNPC();
        // playMusic(0);
        gameState = titleState;
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

    public void updated() {
        if (gameState == playState) {
            // player
            player.update();
            music.resume(); // Resume nhac khi choi

            // NPC
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    npc[i].update();
                }
            }
        }
        if (gameState == pauseState) {
            // nothing
            music.pause(); // Pause nhac khi tam dung
        }

    }

    public void paintComponent(Graphics g) { // Phuong thuc ve len panel
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // DEBUG
        long drawStart = 0; // bien luu thoi gian bat dau ve
        if (keyH.checkDrawTime == true) {
            drawStart = System.nanoTime();
        }
        // TILE SCREEN
        if (gameState == titleState) {
            ui.draw(g2);

        } // OTHER
        else {
            // TILE
            tileM.draw(g2);

            // NPC
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    npc[i].draw(g2);
                }
            }

            // PLAYER
            player.draw(g2);

            // OBJECT
            for (int i = 0; i < obj.length; i++) {
                if (obj[i] != null) {
                    obj[i].draw(g2, this);
                }
            }

            // UI
            ui.draw(g2);
        }

        // DEBUG
        if (keyH.checkDrawTime == true) {
            long drawEnd = System.nanoTime(); // bien luu thoi gian ket thu ve
            long passed = drawEnd - drawStart; // thoi gian ve
            g2.setColor(Color.white);
            g2.drawString("Draw time: " + passed, 10, 400);
            System.out.println("Draw time: " + passed);
        }

        g2.dispose();// giai phong bo nho cho doi tuong g2

    }

    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() {
        music.stop();
    }

    public void playSE(int i) {
        se.setFile(i);
        se.play();
    }
}