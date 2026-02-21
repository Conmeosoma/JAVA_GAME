package Enity;

import Main.GamePanel;
import Object.OBJ_Axe;
import Object.OBJ_Key;
import Object.OBJ_Potion_Red;
import Object.OBJ_Shield_Blue;
import Object.OBJ_Shield_Normal;
import Object.OBJ_Weapon_Normal;
import java.awt.Rectangle;

public class NPC_Merchant extends Entity {

    public NPC_Merchant(GamePanel gp) {
        super(gp);
        direction = "down";
        speed = 1;

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        getImage();
        setDialogue();
        setItems();
    }

    public void getImage() {
        up1 = setup("/res/NPC/merchant_down_1", gp.tileSize, gp.tileSize);
        up2 = setup("/res/NPC/merchant_down_2", gp.tileSize, gp.tileSize);
        down1 = setup("/res/NPC/merchant_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/res/NPC/merchant_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/res/NPC/merchant_down_1", gp.tileSize, gp.tileSize);
        left2 = setup("/res/NPC/merchant_down_2", gp.tileSize, gp.tileSize);
        right1 = setup("/res/NPC/merchant_down_1", gp.tileSize, gp.tileSize);
        right2 = setup("/res/NPC/merchant_down_2", gp.tileSize, gp.tileSize);

    }

    public void setDialogue() {
        dialogues[0] = "He he, vậy là ngươi đã tìm được ta.\nTa có một số món đồ thú vị.\nNgươi có muốn giao dịch không?";
    }

    public void setItems() {
        inventory.add(new OBJ_Potion_Red(gp));
        inventory.add(new OBJ_Key(gp));
        inventory.add(new OBJ_Weapon_Normal(gp));
        inventory.add(new OBJ_Axe(gp));
        inventory.add(new OBJ_Shield_Normal(gp));
        inventory.add(new OBJ_Shield_Blue(gp));
    }
    
    @Override
    public void speak() {
        super.speak();
        gp.gameState = gp.tradeState;  
        gp.ui.npc = this;
    }
}
