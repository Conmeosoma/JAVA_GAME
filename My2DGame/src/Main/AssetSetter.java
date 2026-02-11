// meoconsoma
package Main;

import Enity.NPC_OldMan;
import Object.OBJ_Axe;
import Object.OBJ_Coin_Bronze;
import Object.OBJ_Heart;
import Object.OBJ_Key;
import Object.OBJ_ManaCrystal;
import Object.OBJ_Potion_Red;
import Object.OBJ_Shield_Blue;
import monster.MON_GreenSlime;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        int i = 0;
        gp.obj[i] = new OBJ_Coin_Bronze(gp);
        gp.obj[i].World_X = gp.tileSize * 25;
        gp.obj[i].World_Y = gp.tileSize * 23;
        i++;
        gp.obj[i] = new OBJ_Coin_Bronze(gp);
        gp.obj[i].World_X = gp.tileSize * 21;
        gp.obj[i].World_Y = gp.tileSize * 19;
        i++;
        gp.obj[i] = new OBJ_Coin_Bronze(gp);
        gp.obj[i].World_X = gp.tileSize * 26;
        gp.obj[i].World_Y = gp.tileSize * 21;
        i++;
        gp.obj[i] = new OBJ_Axe(gp);
        gp.obj[i].World_X = gp.tileSize * 33;
        gp.obj[i].World_Y = gp.tileSize * 21;
        i++;
        gp.obj[i] = new OBJ_Shield_Blue(gp);
        gp.obj[i].World_X = gp.tileSize * 35;
        gp.obj[i].World_Y = gp.tileSize * 21;
        i++;
        gp.obj[i] = new OBJ_Potion_Red(gp);
        gp.obj[i].World_X = gp.tileSize * 22;
        gp.obj[i].World_Y = gp.tileSize * 27;
        i++;
        gp.obj[i] = new OBJ_Heart(gp);
        gp.obj[i].World_X = gp.tileSize * 22;
        gp.obj[i].World_Y = gp.tileSize * 29;
        i++;
        gp.obj[i] = new OBJ_ManaCrystal(gp);
        gp.obj[i].World_X = gp.tileSize * 22;
        gp.obj[i].World_Y = gp.tileSize * 31;
        i++;
    }

    public void setNPC() {

        gp.npc[0] = new NPC_OldMan(gp);
        gp.npc[0].World_X = gp.tileSize * 21;
        gp.npc[0].World_Y = gp.tileSize * 21;
        // gp.npc[0] = new NPC_OldMan(gp);
        // gp.npc[0].World_X = gp.tileSize * 9;
        // gp.npc[0].World_Y = gp.tileSize * 10;

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

        // gp.monster[0] = new MON_GreenSlime(gp);
        // gp.monster[0].World_X = gp.tileSize * 11;
        // gp.monster[0].World_Y = gp.tileSize * 10;
        //
        // gp.monster[1] = new MON_GreenSlime(gp);
        // gp.monster[1].World_X = gp.tileSize * 11;
        // gp.monster[1].World_Y = gp.tileSize * 11;
    }
}
