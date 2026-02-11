package Tile;

import Main.GamePanel;
import Main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp, Tile[] tile) {
        this.gp = gp;
        this.tile = tile;
    }

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[50];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/res/maps/worldV2.txt");
        // My2DGame\src\res\maps\world01.txt
    }

    public void getTileImage() {

        // BufferedImage scaledImage = new BufferedImage(gp.tileSize, gp.tileSize,
        // tile[0].image.getType());
        // // tao anh moi voi kich thuoc la tileSize
        // Graphics2D g2 = scaledImage.createGraphics(); // tao doi tuong grapic2d de ve
        // tren anh moi
        // g2.drawImage(tile[0].image, 0, 0, gp.tileSize, gp.tileSize, null); // ve anh
        // vao anh moi voi kich thuoc moi
        // tile[0].image = scaledImage; // gan lai anh da duoc phong to cho tile[0]
        /*
         * Viec lam tren giup anh duoc phong to hoac thu nho ve kich thuoc mang la voi
         * kich thuoc tileSize de tranh viec anh bi mo hoac bi cat khi ve tren man hinh.
         */
        // PLACEHOLDER FOR TILES
        setup(0, "grass00", false);
        setup(1, "grass00", false);
        setup(2, "grass00", false);
        setup(3, "grass00", false);
        setup(4, "grass00", false);
        setup(5, "grass00", false);
        setup(6, "grass00", false);
        setup(7, "grass00", false);
        setup(8, "grass00", false);
        setup(9, "grass00", false);

        setup(10, "grass00", false);
        setup(11, "grass01", false);
        setup(12, "water00", true);
        setup(13, "water01", true);
        setup(14, "water02", true);
        setup(15, "water03", true);
        setup(16, "water04", true);
        setup(17, "water05", true);
        setup(18, "water06", true);
        setup(19, "water07", true);
        setup(20, "water08", true);
        setup(21, "water09", true);
        setup(22, "water10", true);
        setup(23, "water11", true);
        setup(24, "water12", true);
        setup(25, "water13", true);
        setup(26, "road00", false);
        setup(27, "road01", false);
        setup(28, "road02", false);
        setup(29, "road03", false);
        setup(30, "road04", false);
        setup(31, "road05", false);
        setup(32, "road06", false);
        setup(33, "road07", false);
        setup(34, "road08", false);
        setup(35, "road09", false);
        setup(36, "road10", false);
        setup(37, "road11", false);
        setup(38, "road12", false);
        setup(39, "earth", false);
        setup(40, "wall", true);
        setup(41, "tree", true);

    }

    public void setup(int index, String imageName, boolean collision) {
        UtilityTool uTool = new UtilityTool();
        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/" + imageName + ".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;

        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();// in ra loi neu co
        }

    }

    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath); // mo file
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;
            while (col < gp.maxWorldCol && row < gp.maxWorldRow) { // doc tung hang va cot. so sanh voi gioi han cua mao
                String line = br.readLine();
                // xu ly map theo tung cot trong hang
                while (col < gp.maxWorldCol) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol) { // dm loi cccc sai moi maxWorldCol voi maxScreenCol vcl chiu
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int worldCol = 0; // cot hien tai trong the gioi

        int worldRow = 0; // hang hien tai trong the gioi

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileNum = mapTileNum[worldCol][worldRow];
            // tinh toán vị trí vẽ tile bằng tọa độ thế giới
            int worldX = worldCol * gp.tileSize; // tọa độ x thế giới
            int worldY = worldRow * gp.tileSize; // tọa độ y thế giới
            int screenX = worldX - gp.player.World_X + gp.player.screenX;
            // tọa độ cam màn hình X = tọa độ thế giới X - tọa độ thế giới nhân vật X + tọa
            // độ

            int screenY = worldY - gp.player.World_Y + gp.player.screenY;
            // tọa độ cam màn hình Y = tọa độ thế giới Y - tọa độ thế giới nhân vật Y + tọa
            // do
            if (worldX + gp.tileSize > gp.player.World_X - gp.player.screenX &&
                    worldX - gp.tileSize < gp.player.World_X + gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.World_Y - gp.player.screenY &&
                    worldY - gp.tileSize < gp.player.World_Y + gp.player.screenY)
            // chi ve nhung tile trong vung nhin thay cua nhan vat
            {

                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize,
                        gp.tileSize, null);
            }
            worldCol++;

            // đầu tiên check nếu ô đó là ô [0][0] thì nó sẽ là 0 * 48
            // nếu ô [1][0] hì nó sẽ là 1 * 48
            // .........
            // nếu ô [15][0] thì nó sẽ là 15 * 48 = 768
            // tương tự với hàng

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}