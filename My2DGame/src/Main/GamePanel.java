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
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

import javax.swing.JPanel;
import Enity.Player;
import Tile.TileManager;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import tile_interactive.InteractiveTile;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    final int originalTileSize = 16; // kich thuoc o ban dau 16 * 16
    final int scale = 3; // ti le phong to 3 lan
    public final int tileSize = originalTileSize * scale; // kich thuoc o hien tai 48 * 48
    public final int maxScreenCol = 20; // gioi han so cot cua man hinh
    public final int maxScreenRow = 12; // gioi han so hang cua man hinh
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixel
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixel

    // WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    // FOR FULL SCREEN
    int screenWidth2 = screenWidth;
    int screenHeight2 = screenHeight;
    BufferedImage tempScreen;
    Graphics2D g2;
    public boolean fullScreenOn = false;

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
    public Entity obj[] = new Entity[20];
    public Entity npc[] = new Entity[10];
    public Entity monster[] = new Entity[20];
    public InteractiveTile iTile[] = new InteractiveTile[50];
    public ArrayList<Entity> projectileList = new ArrayList<>();
    public ArrayList<Entity> particleList = new ArrayList<>();
    ArrayList<Entity> entityList = new ArrayList<>();

    // GAME STATES
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int characterState = 4;
    public final int optionsState = 5;

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
        aSetter.setMonster();
        aSetter.setInteractiveTile();
        // playMusic(0);
        gameState = titleState;
        tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
        g2 = (Graphics2D) tempScreen.getGraphics();
        // Apply fullscreen only if enabled in settings
        if (fullScreenOn) {
            applyFullScreen();
        }

    }

    public void setFullScreen() {
        // GET LOCAL SCREEN SIZE
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        gd.setFullScreenWindow(Main.window);
        // GET FULL SCREEN SIZE
        screenWidth2 = Main.window.getWidth();
        screenHeight2 = Main.window.getHeight();

    }

    public void applyFullScreen() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();

        if (fullScreenOn) {
            // Enter full screen
            Main.window.dispose();
            Main.window.setUndecorated(true);
            Main.window.setVisible(true);
            gd.setFullScreenWindow(Main.window);
            screenWidth2 = Main.window.getWidth();
            screenHeight2 = Main.window.getHeight();
        } else {
            // Exit full screen
            gd.setFullScreenWindow(null);
            Main.window.dispose();
            Main.window.setUndecorated(false);
            Main.window.setVisible(true);
            Main.window.setSize(screenWidth, screenHeight);
            Main.window.setLocationRelativeTo(null);
            screenWidth2 = screenWidth;
            screenHeight2 = screenHeight;
        }
    }

    public void startGameThread() {
        gamThread = new Thread(this);// khoi tao thread
        gamThread.start();// bat dau thread
    }

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
                drawToTempScreen();
                drawToScreen();
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
            for (int i = 0; i < monster.length; i++) {
                if (monster[i] != null) {
                    if (monster[i].alive == true && monster[i].dying == false) {
                        monster[i].update();
                    }
                    if (monster[i].alive == false) {
                        monster[i].checkDrop();
                        monster[i] = null;
                    }
                }
            }
            for (int i = 0; i < projectileList.size(); i++) {
                if (projectileList.get(i) != null) {
                    if (projectileList.get(i).alive == true) {
                        projectileList.get(i).update();
                    }
                    if (projectileList.get(i).alive == false) {
                        projectileList.remove(i);
                    }
                }
            }
            for (int i = 0; i < particleList.size(); i++) {
                if (particleList.get(i) != null) {
                    if (particleList.get(i).alive == true) {
                        particleList.get(i).update();
                    }
                    if (particleList.get(i).alive == false) {
                        particleList.remove(i);
                    }
                }
            }
            for (int i = 0; i < iTile.length; i++) {
                if (iTile[i] != null) {
                    iTile[i].update();
                }
            }
        }
        if (gameState == pauseState) {
            // nothing
            music.pause(); // Pause nhac khi tam dung
        }
        if (gameState == dialogueState) {
            // Xu ly Exit dialogue state khi nhan Enter
            if (keyH.enterPressed == true) {
                gameState = playState;
                keyH.enterPressed = false;
            }
        }

    }

    public void drawToTempScreen() {
        // DEBUG
        long drawStart = 0; // bien luu thoi gian bat dau ve
        if (keyH.showDebugText == true) {
            drawStart = System.nanoTime();
        }
        // TILE SCREEN
        if (gameState == titleState) {
            ui.draw(g2);

        } // OTHERS
        else {
            // TILE
            tileM.draw(g2);
            // INTERACTIVE TILE
            for (int i = 0; i < iTile.length; i++) {
                if (iTile[i] != null) {
                    iTile[i].draw(g2);
                }
            }

            entityList.add(player);

            // ADD ENTITIES THE LIST
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    entityList.add(npc[i]);
                }
            }

            for (int i = 0; i < obj.length; i++) {
                if (obj[i] != null) {
                    entityList.add(obj[i]);
                }
            }

            for (int i = 0; i < monster.length; i++) {
                if (monster[i] != null) {
                    entityList.add(monster[i]);
                }
            }

            for (int i = 0; i < projectileList.size(); i++) {
                if (projectileList.get(i) != null) {
                    entityList.add(projectileList.get(i));
                }
            }

            for (int i = 0; i < particleList.size(); i++) {
                if (particleList.get(i) != null) {
                    entityList.add(particleList.get(i));
                }
            }

            // SORT
            Collections.sort(entityList, new Comparator<Entity>() {
                @Override
                public int compare(Entity e1, Entity e2) {
                    int result = Integer.compare(e1.World_Y, e2.World_Y);
                    return 0;
                }
            });

            // DRAW ENTITIES
            for (int i = 0; i < entityList.size(); i++) {
                entityList.get(i).draw(g2);
            }
            // EMPTY ENTITY LIST
            entityList.clear();

            // // NPC
            // for (int i = 0; i < npc.length; i++) {
            // if (npc[i] != null) {
            // npc[i].draw(g2);
            // }
            // }
            //
            // // PLAYER
            // player.draw(g2);
            //
            // // OBJECT
            // for (int i = 0; i < obj.length; i++) {
            // if (obj[i] != null) {
            // obj[i].draw(g2, this);
            // }
            // }
            // UI
            ui.draw(g2);
        }

        // DEBUG
        // if (keyH.checkDrawTime == true) {
        // long drawEnd = System.nanoTime(); // bien luu thoi gian ket thu ve
        // long passed = drawEnd - drawStart; // thoi gian ve
        // g2.setColor(Color.white);
        // g2.drawString("Draw time: " + passed, 10, 400);
        // System.out.println("Draw time: " + passed);
        // }
        //
        if (keyH.showDebugText == true) {
            long drawEnd = System.nanoTime(); // bien luu thoi gian ket thu ve
            long passed = drawEnd - drawStart; // thoi gian ve

            g2.setFont(new Font("Arial", Font.PLAIN, 20));
            g2.setColor(Color.white);
            int x = 10;
            int y = 400;
            int lineHeight = 20;
            g2.drawString("WorldX " + player.World_X, x, y);
            y += lineHeight;
            g2.drawString("WorldY " + player.World_Y, x, y);
            y += lineHeight;
            g2.drawString("Col " + (player.World_X + player.solidArea.x) / tileSize, x, y);
            y += lineHeight;
            g2.drawString("Row " + (player.World_Y + player.solidArea.y) / tileSize, x, y);
            y += lineHeight;

            g2.drawString("Draw time: " + passed, x, y);
            System.out.println("Draw time: " + passed);
        }
    }

    public void drawToScreen() {
        Graphics g = getGraphics();
        g.drawImage(tempScreen, 0, 0, screenWidth2, screenHeight2, null);
        g.dispose();
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
