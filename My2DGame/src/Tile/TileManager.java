package Tile;

import Main.GamePanel;

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
        tile = new Tile[10];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/res/maps/world01.txt");
        // My2DGame\src\res\maps\world01.txt
    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/grass.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/wall.png"));
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/water.png"));
            tile[2].collision = true;

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/earth.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/tree.png"));
            tile[4].collision = true;

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/sand.png"));

        } catch (IOException e) {
            e.printStackTrace();
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