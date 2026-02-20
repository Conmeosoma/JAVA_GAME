package Main;

public class EventHander {

    GamePanel gp;
    EventRect eventRect[][][];
    int previousEventX, previousEventY;
    boolean canTouchEvent = true;

    public EventHander(GamePanel gp) {
        this.gp = gp;
        eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
        int map = 0;
        int col = 0;
        int row = 0;
        while (map < gp.maxMap && col < gp.maxWorldCol && row < gp.maxWorldRow) {
            eventRect[map][col][row] = new EventRect();
            eventRect[map][col][row].x = 23;
            eventRect[map][col][row].y = 23;
            eventRect[map][col][row].width = 2;
            eventRect[map][col][row].height = 2;
            eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
            eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;
            col++;
            if (col == gp.maxWorldCol) {
                col = 0;
                row++;

                if (row == gp.maxWorldRow) {
                    row = 0;
                    map++;
                }
            }
        }
    }

    public void checkEvent() {
        // CHECK IF THE PLAYER CHARATER IS MORE THAN 1 TILE AWAY FROM THE THE LAST
        // EVENT
        int xDistance = Math.abs(gp.player.World_X - previousEventX);
        int yDistance = Math.abs(gp.player.World_Y - previousEventY);
        int distance = Math.max(xDistance, yDistance);

        if (distance > gp.tileSize) {
            canTouchEvent = true;
        }
        if (canTouchEvent == true) {
            if ((hit(0, 27, 16, "right") == true)) {
                damegePit(gp.dialogueState);
                damegePit(gp.dialogueState);
            }
            else if ((hit(0, 10, 39, "any") == true)) {
                teleport(1, 12, 13);
            }
            else if ((hit(1, 12, 13, "any") == true)) {
                teleport(0, 10, 39);
            }
            else if ((hit(0, 23, 12, "up") == true)) {
                healingPool(gp.dialogueState);
            }
        }
    }

    public void damegePit(int gameState) {
        gameState = gameState;
        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = "You fall into a pit!";
        gp.player.life--;
        // eventRect[col][row].eventDone = true;
        canTouchEvent = false;
    }

    public void healingPool(int gameState) {
        if (gp.keyH.enterPressed == true) {
            gp.gameState = gameState;
            gp.player.attackCanceled = true;
            gp.playSE(2);
//      gp.ui.currentDialogue = "You are healed!You drink the water";
            gp.ui.currentDialogue = "Bạn đã uống nước!\nLife và Mana của bạn được hồi phục.";

            gp.player.life = gp.player.maxLife;
            gp.player.mana = gp.player.maxMana;

            gp.aSetter.setMonster();
        }
    }

    public boolean hit(int map, int col, int row, String reqDirection) {
        boolean hit = false;

        if (map == gp.currentMap) {
            gp.player.solidArea.x = gp.player.World_X + gp.player.solidArea.x;
            gp.player.solidArea.y = gp.player.World_Y + gp.player.solidArea.y;
            eventRect[map][col][row].x = col * gp.tileSize + eventRect[map][col][row].x;
            eventRect[map][col][row].y = row * gp.tileSize + eventRect[map][col][row].y;
            if (gp.player.solidArea.intersects(eventRect[map][col][row]) && eventRect[map][col][row].eventDone == false) {
                if (gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                    hit = true;
                    previousEventX = gp.player.World_X;
                    previousEventY = gp.player.World_Y;

                }
            }
            gp.player.solidArea.x = gp.player.solidAreaDefaultX;
            gp.player.solidArea.y = gp.player.solidAreaDefaultY;
            eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
            eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
        }
        return hit;
    }
    
    public void teleport(int map, int col, int row){
        gp.currentMap = map;
        gp.player.World_X = gp.tileSize * col;        
        gp.player.World_Y = gp.tileSize * row;
        
        previousEventX = gp.player.World_X;
        previousEventY = gp.player.World_Y;
        canTouchEvent = false;
        gp.playSE(13);
    }
}
