// CodeByConMeoSoMa
// /\_/\  
//( o.o ) 
// > ^ <
package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    GamePanel gp;

    public boolean upPressed, downPressed, leftPressed, rightPressed; // bien kiem tra phim di chuyen
    public boolean enterPressed;
    // DEBUG
    boolean checkDrawTime = false;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    public KeyHandler() {

    }

    @Override
    public void keyTyped(KeyEvent e) { // phuong thuc xu ly su kien nhan phim
        // Sau khi nhan nhung truoc khi ngon tay ra khoi phim

    }

    @Override
    public void keyPressed(KeyEvent e) { // phuong thuc xu ly su kien nhan phim
        // Ngay sau khi ban nhan xuong phim
        int code = e.getKeyCode(); // lay ma phim vua nhan
        // TITLE STATE
        if (gp.gameState == gp.titleState) {
            // SCENE MENU
            if (gp.ui.titleSceenState == 0) {
                if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                    gp.ui.commandNum--;
                    if (gp.ui.commandNum < 0) {
                        gp.ui.commandNum = 0;
                    }
                }
                if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                    gp.ui.commandNum++;
                    if (gp.ui.commandNum > 3) {
                        gp.ui.commandNum = 0;
                    }
                }
                if (code == KeyEvent.VK_ENTER) {
                    if (gp.ui.commandNum == 0) {
                        gp.ui.titleSceenState = 1;

                        gp.ui.commandNum = 0; // Reset commandNum
                    } else if (gp.ui.commandNum == 1) {
                        // gp.gameState = gp.LOADState;
                    } else if (gp.ui.commandNum == 2) {
                        // gp.gameState = gp.OPTIONState;
                    } else if (gp.ui.commandNum == 3) {
                        System.exit(0);
                    }
                }
            }

            // SCENE CHOSE CHARACTER
            else if (gp.ui.titleSceenState == 1) {
                if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                    gp.ui.commandNum--;
                    if (gp.ui.commandNum < 0) {
                        gp.ui.commandNum = 0;
                    }
                }
                if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                    gp.ui.commandNum++;
                    if (gp.ui.commandNum > 4) {
                        gp.ui.commandNum = 0;
                    }
                }
                if (code == KeyEvent.VK_ENTER) {
                    if (gp.ui.commandNum == 0) {
                        System.out.println("ADD WARRIOR");
                        // ADD WARRIOR
                        gp.gameState = gp.playState;
                        gp.playMusic(0);
                    } else if (gp.ui.commandNum == 1) {
                        System.out.println("ADD MAGE");
                        // ADD MAGE
                        gp.gameState = gp.playState;
                        gp.playMusic(0);
                    } else if (gp.ui.commandNum == 2) {
                        System.out.println("ADD GUNNER");
                        // ADD GUNNER
                        gp.gameState = gp.playState;
                        gp.playMusic(0);
                    } else if (gp.ui.commandNum == 3) {
                        System.out.println("ADD ASSASSIN");
                        // ADD ASSASSIN
                        gp.gameState = gp.playState;
                        gp.playMusic(0);
                    } else if (gp.ui.commandNum == 4) {
                        gp.ui.titleSceenState = 0;
                    }
                }
            }

        }

        // PLAY STATE
        if (gp.gameState == gp.playState) {
            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                upPressed = true;
            }
            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                downPressed = true;
            }
            if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
                leftPressed = true;
            }
            if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
                rightPressed = true;
            }
            if (code == KeyEvent.VK_T) {
                checkDrawTime = !checkDrawTime;
            }
            if (code == KeyEvent.VK_ESCAPE) {
                gp.gameState = gp.pauseState;
                // if (gp.gameState == gp.playState) {
                // gp.gameState = gp.pauseState;
                // } else if (gp.gameState == gp.pauseState) {
                // gp.gameState = gp.playState;
                // }
            }
            if (code == KeyEvent.VK_ENTER) {
                // System.out.println("Enter is pressed!");
                enterPressed = true;
            }
        }
        // DEBUG
        // PAUSE STATE
        else if (gp.gameState == gp.pauseState) {
            if (code == KeyEvent.VK_ESCAPE) {
                gp.gameState = gp.playState;
            }
        }
        // DIALOGUE STATE
        else if (gp.gameState == gp.dialogueState) {
            if (code == KeyEvent.VK_ENTER) {
                gp.gameState = gp.playState;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {// phuong thuc xu ly su kien tha phim
        // Ngay sau khi ban tha ngon tay ra khoi phim
        int code = e.getKeyCode(); // lay ma phim vua nhan
        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            upPressed = false;

        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }
    }

}
