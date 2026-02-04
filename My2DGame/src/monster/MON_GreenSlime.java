package monster;

import Enity.Entity;
import Main.GamePanel;
import java.util.Random;

/**
 *
 * @author dieu hoang
 */
public class MON_GreenSlime extends Entity {
    GamePanel gp;

    public MON_GreenSlime(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = 2;
        name = "Green Slime";
        speed = 1;
        maxLife = 20;
        life = maxLife;

        solidArea.x = 3;
        solidArea.y = 10;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage() {
        up1 = setup("/res/Monster/greenslime_down_1", gp.tileSize, gp.tileSize);
        up2 = setup("/res/Monster/greenslime_down_2", gp.tileSize, gp.tileSize);
        down1 = setup("/res/Monster/greenslime_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/res/Monster/greenslime_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/res/Monster/greenslime_down_1", gp.tileSize, gp.tileSize);
        left2 = setup("/res/Monster/greenslime_down_2", gp.tileSize, gp.tileSize);
        right1 = setup("/res/Monster/greenslime_down_1", gp.tileSize, gp.tileSize);
        right2 = setup("/res/Monster/greenslime_down_2", gp.tileSize, gp.tileSize);
    }

    // Cai dat hanh dong cho slime - AI NANG CAP
    public void setAction() {
        actionLockCounter++;

        if (actionLockCounter == 120) {
            Random random = new Random();

            // Tinh khoang cach den player
            int xDistance = Math.abs(World_X - gp.player.World_X);
            int yDistance = Math.abs(World_Y - gp.player.World_Y);
            int tileDistance = (xDistance + yDistance) / gp.tileSize;

            // CHẾ ĐỘ 1: NẾU PLAYER Ở GẦN (trong 5 tiles) - TRUY ĐUỔI
            if (tileDistance < 5) {
                // Tim duong den player
                int xDiff = gp.player.World_X - World_X;
                int yDiff = gp.player.World_Y - World_Y;

                // Ưu tiên hướng có khoảng cách lớn hơn
                if (Math.abs(xDiff) > Math.abs(yDiff)) {
                    // Di chuyển theo trục X trước
                    if (xDiff > 0) {
                        direction = "right";
                    } else {
                        direction = "left";
                    }
                    // 30% cơ hội đổi sang trục Y để tránh bị kẹt
                    if (random.nextInt(100) < 30) {
                        if (yDiff > 0) {
                            direction = "down";
                        } else {
                            direction = "up";
                        }
                    }
                } else {
                    // Di chuyển theo trục Y trước
                    if (yDiff > 0) {
                        direction = "down";
                    } else {
                        direction = "up";
                    }
                    // 30% cơ hội đổi sang trục X để tránh bị kẹt
                    if (random.nextInt(100) < 30) {
                        if (xDiff > 0) {
                            direction = "right";
                        } else {
                            direction = "left";
                        }
                    }
                }
            }
            // CHẾ ĐỘ 2: NẾU HP THẤP (dưới 40%) - CHẠY TRỐN
            else if (life < maxLife * 0.4) {
                // Chạy ngược hướng với player
                int xDiff = gp.player.World_X - World_X;
                int yDiff = gp.player.World_Y - World_Y;

                if (Math.abs(xDiff) > Math.abs(yDiff)) {
                    if (xDiff > 0) {
                        direction = "left"; // Chạy ngược lại
                    } else {
                        direction = "right";
                    }
                } else {
                    if (yDiff > 0) {
                        direction = "up"; // Chạy ngược lại
                    } else {
                        direction = "down";
                    }
                }
            }
            // CHẾ ĐỘ 3: PLAYER Ở XA - DI CHUYỂN NGẪU NHIÊN (patrol)
            else {
                int i = random.nextInt(100) + 1;

                if (i <= 25) {
                    direction = "up";
                } else if (i <= 50) {
                    direction = "down";
                } else if (i <= 75) {
                    direction = "left";
                } else {
                    direction = "right";
                }
            }

            actionLockCounter = 0;
        }
    }

    // PHẢN ỨNG KHI BỊ ĐÁNH - AI NÂNG CAO
    public void damageReaction() {
        actionLockCounter = 0;

        // Tính khoảng cách đến player
        int xDistance = Math.abs(World_X - gp.player.World_X);
        int yDistance = Math.abs(World_Y - gp.player.World_Y);

        // NẾU HP CÒN NHIỀU HƠN 50% - PHẢN CÔNG
        if (life > maxLife * 0.5) {
            // Lao về phía player
            int xDiff = gp.player.World_X - World_X;
            int yDiff = gp.player.World_Y - World_Y;

            if (Math.abs(xDiff) > Math.abs(yDiff)) {
                direction = xDiff > 0 ? "right" : "left";
            } else {
                direction = yDiff > 0 ? "down" : "up";
            }
        }
        // NẾU HP THẤP - LÙI LẠI
        else {
            // Lùi lại theo hướng ngược player
            int xDiff = gp.player.World_X - World_X;
            int yDiff = gp.player.World_Y - World_Y;

            if (Math.abs(xDiff) > Math.abs(yDiff)) {
                direction = xDiff > 0 ? "left" : "right"; // Ngược lại
            } else {
                direction = yDiff > 0 ? "up" : "down"; // Ngược lại
            }
        }
    }
}