// CodeByConMeoSoMa
// /\_/\  
//( o.o ) 
// > ^ <
package Enity; // 1 goi package de to chuc ma nguon

import java.awt.image.BufferedImage; // them lop BufferedIamge de xu ly hinh anh

public class Entity { // lop Entity chua cac thuoc tinh va phuong thuc chung cho tat ca doi tuong
    // trong game

    public int x, y;
    public int speed;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;
    // ======= XU LY ANIMATION NHAN VAT =======
    public int spiteCounter = 0;
    public int spiteNum = 1;
    // ========================================
    

}
