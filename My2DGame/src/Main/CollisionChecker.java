/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import Enity.Entity;

/**
 *
 * @author dieu hoang
 */
public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {
        int entityLeftWorldX = entity.World_X + entity.solidArea.x;
        int entityRightWorldX = entity.World_X + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.World_Y + entity.solidArea.y;
        int entityBottomWorldY = entity.World_Y + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        int tileNum1, tileNum2;
        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
        }
    }

    // ================ CHECK OBJECT ===================
    public int checkObject(Entity entity, boolean player) {// neu la player thi tra ve index cua object
        int index = 999; // gia tri mac dinh neu khong co va cham vs object nao

        for (int i = 0; i < gp.obj.length; i++) { // lap qua tat ca object
            if (gp.obj[i] != null) { // neu object khac null thi kiem tra va cham
                // lay vi tri vung va cham cua entity
                entity.solidArea.x = entity.World_X + entity.solidArea.x;
                entity.solidArea.y = entity.World_Y + entity.solidArea.y;
                // lay vi tri vung va cham cua object
//                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
//                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

                gp.obj[i].solidArea.x = gp.obj[i].World_X + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].World_Y + gp.obj[i].solidArea.y;
                switch (entity.direction) { // di chuyen entity de kiem tra va cham
                    case "up":
                        entity.solidArea.y -= entity.speed; // di chuyen vung va cham len tren
//                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) { // kiem tra va cham cua 2 vung
//                            if (gp.obj[i].collision == true) { // neu object co va cham
//                                entity.collisionOn = true;
//                                System.out.println("Va cham object up");
//                            }
//                            if (player == true) { // neu entity la player
//                                index = i;
//                            }
//
//                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed; // di chuyen vung va cham xuong duoi
//                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) { // kiem tra va cham
//                            if (gp.obj[i].collision == true) { // neu object co va cham
//                                entity.collisionOn = true;
//                                System.out.println("Va cham object down");
//                            }
//                            if (player == true) { // neu entity la player
//                                index = i;
//                            }
//                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed; // di chuyen vung va cham sang trai
//                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) { // kiem tra va cham
//                            if (gp.obj[i].collision == true) { // neu object co va cham
//                                entity.collisionOn = true;
//                                System.out.println("Va cham object LEFT");
//                            }
//                            if (player == true) { // neu entity la player
//                                index = i;
//                            }
//                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed; // di chuyen vung va cham sang phai
//                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) { // kiem tra va cham
//                            if (gp.obj[i].collision == true) { // neu object co va cham
//                                entity.collisionOn = true;
//                                System.out.println("Va cham object RIGHT");
//                            }
//                            if (player == true) { // neu entity la player
//                                index = i;
//                            }
//                        }
                        break;
                }

                if (entity.solidArea.intersects(gp.obj[i].solidArea)) { // kiem tra va cham
                    if (gp.obj[i].collision == true) { // neu object co va cham
                        entity.collisionOn = true;
                    }
                    if (player == true) { // neu entity la player
                        index = i;
                    }
                }

                // reset lai vi tri vung va cham cua entity va object sau khi kiem tra
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
            }
        }
        // tra ve index cua object ma player va cham
        return index;
    }

    // NPC OR MONSTER
    public int checkEntity(Entity entity, Entity[] target) {
        int index = 999; // gia tri mac dinh neu khong co va cham vs object nao

        for (int i = 0; i < target.length; i++) { // lap qua tat ca object
            if (target[i] != null) { // neu object khac null thi kiem tra va cham
                // lay vi tri vung va cham cua entity
                entity.solidArea.x = entity.World_X + entity.solidArea.x;
                entity.solidArea.y = entity.World_Y + entity.solidArea.y;
                // lay vi tri vung va cham cua object
                target[i].solidArea.x = target[i].World_X + target[i].solidArea.x;
                target[i].solidArea.y = target[i].World_Y + target[i].solidArea.y;

                switch (entity.direction) { // di chuyen entity de kiem tra va cham
                    case "up":
                        entity.solidArea.y -= entity.speed; // di chuyen vung va cham len tren
//                        if (entity.solidArea.intersects(target[i].solidArea)) { // kiem tra va cham cua 2 vung
//                                entity.collisionOn = true; // tat ca NPC va monster la solid
//                                index = i;
//                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed; // di chuyen vung va cham xuong duoi
//                        if (entity.solidArea.intersects(target[i].solidArea)) { // kiem tra va cham
//                             entity.collisionOn = true; // tat ca NPC va monster la solid
//                                index = i;
//                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed; // di chuyen vung va cham sang trai
//                        if (entity.solidArea.intersects(target[i].solidArea)) { // kiem tra va cham
//                             entity.collisionOn = true; // tat ca NPC va monster la solid
//                                index = i;
//                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed; // di chuyen vung va cham sang phai
//                        if (entity.solidArea.intersects(target[i].solidArea)) { // kiem tra va cham
//                             entity.collisionOn = true; // tat ca NPC va monster la solid
//                                index = i;
//                        }
                        break;
                }
                if (entity.solidArea.intersects(target[i].solidArea)) { // kiem tra va cham cua 2 vung
                    if (target[i] != entity) {
                        entity.collisionOn = true; // tat ca NPC va monster la solid
                        index = i;
                    }
                }

                // reset lai vi tri vung va cham cua entity va object sau khi kiem tra
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target[i].solidArea.x = target[i].solidAreaDefaultX;
                target[i].solidArea.y = target[i].solidAreaDefaultY;
            }
        }
        // tra ve index cua object ma player va cham
        return index;
    }

    public boolean checkPlayer(Entity entity) {
        boolean contactPlayer = false;
        // lay vi tri vung va cham cua entity
        entity.solidArea.x = entity.World_X + entity.solidArea.x;
        entity.solidArea.y = entity.World_Y + entity.solidArea.y;
        // lay vi tri vung va cham cua object
        gp.player.solidArea.x = gp.player.World_X + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.World_Y + gp.player.solidArea.y;

        switch (entity.direction) { // di chuyen entity de kiem tra va cham
            case "up":
                entity.solidArea.y -= entity.speed; // di chuyen vung va cham len tren
//                if (entity.solidArea.intersects(gp.player.solidArea)) { // kiem tra va cham cua 2 vung
//                    entity.collisionOn = true; // tat ca NPC va monster la solid
//                }
                break;
            case "down":
                entity.solidArea.y += entity.speed; // di chuyen vung va cham xuong duoi
//                if (entity.solidArea.intersects(gp.player.solidArea)) { // kiem tra va cham
//                    entity.collisionOn = true; // tat ca NPC va monster la solid
//                }
                break;
            case "left":
                entity.solidArea.x -= entity.speed; // di chuyen vung va cham sang trai
//                if (entity.solidArea.intersects(gp.player.solidArea)) { // kiem tra va cham
//                    entity.collisionOn = true; // tat ca NPC va monster la solid
//                }
                break;
            case "right":
                entity.solidArea.x += entity.speed; // di chuyen vung va cham sang phai
//                if (entity.solidArea.intersects(gp.player.solidArea)) { // kiem tra va cham
//                    entity.collisionOn = true; // tat ca NPC va monster la solid
//                }
                break;
        }
        if (entity.solidArea.intersects(gp.player.solidArea)) { // kiem tra va cham
            entity.collisionOn = true; // tat ca NPC va monster la solid
            contactPlayer = true;
        }

        // reset lai vi tri vung va cham cua entity va object sau khi kiem tra
        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        
        return contactPlayer;
    }
}
