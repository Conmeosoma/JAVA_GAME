// CodeByConMeoSoMa
// /\_/\  
//( o.o ) 
// > ^ <
package Enity; // 1 goi package de to chuc ma nguon

import java.awt.image.BufferedImage; // them lop BufferedIamge de xu ly hinh anh
import java.awt.Rectangle;

public class Entity { // lop Entity chua cac thuoc tinh va phuong thuc chung cho tat ca doi tuong
    // trong game

    public int World_X, World_Y;// toa do the gioi cua doi tuong

    public int speed;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;
    // ======= XU LY ANIMATION NHAN VAT =======
    public int spiteCounter = 0;
    public int spiteNum = 1;
    // ========================================
    
    // XAC DINH VUNG VA CHAM
    public Rectangle solidArea;
    public boolean collisionOn = false;

}
