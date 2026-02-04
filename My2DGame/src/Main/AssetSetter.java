// meoconsoma
package Main;

import Enity.NPC_OldMan;
import Object.OBJ_Chest;
import Object.OBJ_Door;
import Object.OBJ_Key;
import Object.OBJ_Boots;
import monster.MON_GreenSlime;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {

        // tao doi tuong key
        // dat toa do cho doi tuong key
        // KEY 1
        // gp.obj[0] = new OBJ_Key(gp);
        // gp.obj[0].worldX = gp.tileSize * 23;
        // gp.obj[0].worldY = gp.tileSize * 7;
        // // KEY 2
        // gp.obj[1] = new OBJ_Key(gp);
        // gp.obj[1].worldX = gp.tileSize * 23;
        // gp.obj[1].worldY = gp.tileSize * 40;
        // gp.obj[2] = new OBJ_Key(gp);
        // gp.obj[2].worldX = gp.tileSize * 38;
        // gp.obj[2].worldY = gp.tileSize * 8;
        // gp.obj[3] = new OBJ_Door(gp);
        // gp.obj[3].worldX = gp.tileSize * 10;
        // gp.obj[3].worldY = gp.tileSize * 11;
        // gp.obj[4] = new OBJ_Door(gp);
        // gp.obj[4].worldX = gp.tileSize * 8;
        // gp.obj[4].worldY = gp.tileSize * 28;
        // gp.obj[5] = new OBJ_Door(gp);
        // gp.obj[5].worldX = gp.tileSize * 12;
        // gp.obj[5].worldY = gp.tileSize * 22;
        // gp.obj[6] = new OBJ_Chest(gp);
        // gp.obj[6].worldX = gp.tileSize * 10;
        // gp.obj[6].worldY = gp.tileSize * 7;
        // // gp.obj[7] = new OBJ_Key();
        // // gp.obj[7].worldX = gp.tileSize * 23;
        // // gp.obj[7].worldY = gp.tileSize * 7;
        // gp.obj[7] = new OBJ_Boots(gp);
        // gp.obj[7].worldX = gp.tileSize * 37;
        // gp.obj[7].worldY = gp.tileSize * 42;
    }

    public void setNPC() {

        gp.npc[0] = new NPC_OldMan(gp);
        gp.npc[0].World_X = gp.tileSize * 21;
        gp.npc[0].World_Y = gp.tileSize * 21;
//        gp.npc[0] = new NPC_OldMan(gp);
//        gp.npc[0].World_X = gp.tileSize * 9;
//        gp.npc[0].World_Y = gp.tileSize * 10;

    }

    public void setMonster() {
        int i = 0;
        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].World_X = gp.tileSize * 21;
        gp.monster[i].World_Y = gp.tileSize * 38;
        i++;
        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].World_X = gp.tileSize * 23;
        gp.monster[i].World_Y = gp.tileSize * 42;
        i++;
        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].World_X = gp.tileSize * 24;
        gp.monster[i].World_Y = gp.tileSize * 37;
        i++;
        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].World_X = gp.tileSize * 34;
        gp.monster[i].World_Y = gp.tileSize * 42;
        i++;
        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].World_X = gp.tileSize * 38;
        gp.monster[i].World_Y = gp.tileSize * 42;
        i++;


//        gp.monster[0] = new MON_GreenSlime(gp);
//        gp.monster[0].World_X = gp.tileSize * 11;
//        gp.monster[0].World_Y = gp.tileSize * 10;
//
//        gp.monster[1] = new MON_GreenSlime(gp);
//        gp.monster[1].World_X = gp.tileSize * 11;
//        gp.monster[1].World_Y = gp.tileSize * 11;
    }
}
