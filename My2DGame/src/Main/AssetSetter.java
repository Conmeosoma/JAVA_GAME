// meoconsoma
package Main;

import Enity.NPC_Merchant;
import Enity.NPC_OldMan;
import Object.OBJ_Axe;
import Object.OBJ_Coin_Bronze;
import Object.OBJ_Heart;
import Object.OBJ_Key;
import Object.OBJ_ManaCrystal;
import Object.OBJ_Potion_Red;
import Object.OBJ_Shield_Blue;
import monster.MON_GreenSlime;
import tile_interactive.IT_DryTree;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        int mapNum = 0;
        int i = 0;
        gp.obj[mapNum][i] = new OBJ_Coin_Bronze(gp);
        gp.obj[mapNum][i].World_X = gp.tileSize * 25;
        gp.obj[mapNum][i].World_Y = gp.tileSize * 23;
        i++;
        gp.obj[mapNum][i] = new OBJ_Coin_Bronze(gp);
        gp.obj[mapNum][i].World_X = gp.tileSize * 21;
        gp.obj[mapNum][i].World_Y = gp.tileSize * 19;
        i++;
        gp.obj[mapNum][i] = new OBJ_Coin_Bronze(gp);
        gp.obj[mapNum][i].World_X = gp.tileSize * 26;
        gp.obj[mapNum][i].World_Y = gp.tileSize * 21;
        i++;
        gp.obj[mapNum][i] = new OBJ_Axe(gp);
        gp.obj[mapNum][i].World_X = gp.tileSize * 33;
        gp.obj[mapNum][i].World_Y = gp.tileSize * 21;
        i++;
        gp.obj[mapNum][i] = new OBJ_Shield_Blue(gp);
        gp.obj[mapNum][i].World_X = gp.tileSize * 35;
        gp.obj[mapNum][i].World_Y = gp.tileSize * 21;
        i++;
        gp.obj[mapNum][i] = new OBJ_Potion_Red(gp);
        gp.obj[mapNum][i].World_X = gp.tileSize * 22;
        gp.obj[mapNum][i].World_Y = gp.tileSize * 27;
        i++;
        gp.obj[mapNum][i] = new OBJ_Heart(gp);
        gp.obj[mapNum][i].World_X = gp.tileSize * 22;
        gp.obj[mapNum][i].World_Y = gp.tileSize * 29;
        i++;
        gp.obj[mapNum][i] = new OBJ_ManaCrystal(gp);
        gp.obj[mapNum][i].World_X = gp.tileSize * 22;
        gp.obj[mapNum][i].World_Y = gp.tileSize * 31;
        i++;
    }

    public void setNPC() {
        int mapNum = 0;
        int i = 0;

        // MAP 0
        gp.npc[mapNum][i] = new NPC_OldMan(gp);
        gp.npc[mapNum][i].World_X = gp.tileSize * 21;
        gp.npc[mapNum][i].World_Y = gp.tileSize * 21;

        // MAP 1
        mapNum = 1;
        i = 0;
        gp.npc[mapNum][i] = new NPC_Merchant(gp);
        gp.npc[mapNum][i].World_X = gp.tileSize * 12;
        gp.npc[mapNum][i].World_Y = gp.tileSize * 7;
        i++;

    }

    public void setMonster() {
        int mapNum = 0;
        int i = 0;
        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].World_X = gp.tileSize * 21;
        gp.monster[mapNum][i].World_Y = gp.tileSize * 38;
        i++;
        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].World_X = gp.tileSize * 23;
        gp.monster[mapNum][i].World_Y = gp.tileSize * 42;
        i++;
        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].World_X = gp.tileSize * 24;
        gp.monster[mapNum][i].World_Y = gp.tileSize * 37;
        i++;
        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].World_X = gp.tileSize * 34;
        gp.monster[mapNum][i].World_Y = gp.tileSize * 42;
        i++;
        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].World_X = gp.tileSize * 38;
        gp.monster[mapNum][i].World_Y = gp.tileSize * 42;
        i++;

//        mapNum = 1;
//        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
//        gp.monster[mapNum][i].World_X = gp.tileSize * 38;
//        gp.monster[mapNum][i].World_Y = gp.tileSize * 42;
//        i++;
    }

    public void setInteractiveTile() {
        int mapNum = 0;
        int i = 0;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 27, 12);
        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 28, 12);
        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 29, 12);
        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 30, 12);
        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 31, 12);
        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 32, 12);
        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 33, 12);
        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 10, 40);
        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 10, 41);
        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 11, 41);
        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 12, 41);
        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 13, 41);
        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 13, 40);
        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 14, 40);
        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 15, 40);
        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 16, 40);
        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 17, 40);
        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 18, 40);
        i++;
    }
}
