package Main;

import java.awt.Rectangle;

public class EventHander {
  GamePanel gp;
  EventRect eventRect[][];
  int previousEventX, previousEventY;
  boolean canTouchEvent = true;

  public EventHander(GamePanel gp) {
    this.gp = gp;
    eventRect = new EventRect[gp.maxWorldCol][gp.maxWorldRow];
    int col = 0;
    int row = 0;
    while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
      eventRect[col][row] = new EventRect();
      eventRect[col][row].x = 23;
      eventRect[col][row].y = 23;
      eventRect[col][row].width = 2;
      eventRect[col][row].height = 2;
      eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
      eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;
      col++;
      if (col == gp.maxWorldCol) {
        col = 0;
        row++;
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
      if ((hit(27, 16, "right") == true)) {
        damegePit(27, 16, gp.dialogueState);
      }
      if ((hit(23, 19, "any") == true)) {
        damegePit(27, 16, gp.dialogueState);
      }

      if ((hit(23, 12, "up") == true)) {
        healingPool(23, 12, gp.dialogueState);
      }
    }
  }

  public void damegePit(int col, int row, int gameState) {
    gp.gameState = gp.dialogueState;
    gp.ui.currentDialogue = "You fall into a pit!";
    gp.player.life--;
    // eventRect[col][row].eventDone = true;
    canTouchEvent = false;
  }

  public void healingPool(int col, int row, int gameState) {
    if (gp.keyH.enterPressed == true) {
      gp.gameState = gameState;
      gp.ui.currentDialogue = "You are healed!You drink the water";
      gp.player.life = gp.player.maxLife;
    }
  }

  public boolean hit(int col, int row, String reqDirection) {
    boolean hit = false;
    gp.player.solidArea.x = gp.player.World_X + gp.player.solidArea.x;
    gp.player.solidArea.y = gp.player.World_Y + gp.player.solidArea.y;
    eventRect[col][row].x = col * gp.tileSize + eventRect[col][row].x;
    eventRect[col][row].y = row * gp.tileSize + eventRect[col][row].y;
    if (gp.player.solidArea.intersects(eventRect[col][row]) && eventRect[col][row].eventDone == false) {
      if (gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
        hit = true;
        previousEventX = gp.player.World_X;
        previousEventY = gp.player.World_Y;

      }
    }
    gp.player.solidArea.x = gp.player.solidAreaDefaultX;
    gp.player.solidArea.y = gp.player.solidAreaDefaultY;
    eventRect[col][row].x = eventRect[col][row].eventRectDefaultX;
    eventRect[col][row].y = eventRect[col][row].eventRectDefaultY;
    return hit;
  }

}
