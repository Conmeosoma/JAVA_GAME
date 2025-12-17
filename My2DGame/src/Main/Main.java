// CodeByConMeoSoMa
// /\_/\  
//( o.o ) 
// > ^ <

package Main;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();// Khởi tạo cửa sổ JFrame
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Đóng cửa số khi nhấn nút X
        window.setResizable(false); // Khong cho phep thay doi kich thuoc cua cua so
        window.setTitle("2D Adventure Game"); // Tieu de cua cua so
        window.setLocationRelativeTo(null); // cua so o giua man hinh
        window.setVisible(true); // hien thi cua so
  
    }

}
