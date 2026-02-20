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
    public boolean shotKeyPressed;
    // DEBUG
    // boolean checkDrawTime = false;
    boolean showDebugText = false;

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
            titleState(code);
        } // PLAY STATE
        else if (gp.gameState == gp.playState) {
            playState(code);
        } // PAUSE STATE
        else if (gp.gameState == gp.pauseState) {
            pauseState(code);
        } // DIALOGUE STATE
        else if (gp.gameState == gp.dialogueState) {
            dialogueState(code);
        } // CHARACTER STATE
        else if (gp.gameState == gp.characterState) {
            characterState(code);
        } // OPTIONS STATE
        else if (gp.gameState == gp.optionsState) {
            optionsState(code);
        } // GAME OVER STATE
        else if (gp.gameState == gp.gameOverState) {
            gameOverState(code);
        }
    }

    public void optionsState(int code) {
        if (code == KeyEvent.VK_ESCAPE) {
            // If inside a sub-menu (e.g. full screen details or controls), go back to main
            // options
            if (gp.ui.subState != 0) {
                gp.ui.subState = 0;
                gp.ui.commandNum = 0; // reset cursor to top
            } else {
                gp.gameState = gp.playState;
            }
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }
        int maxCommandNum = 0;
        switch (gp.ui.subState) {
            case 0:
                maxCommandNum = 5; // 5 options in main menu
                break;
            case 3: // End Game confirmation
                maxCommandNum = 1; // Only one option (Yes/No toggle)
                break;

        }
        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            gp.ui.commandNum--;
            gp.playSE(9);
            if (gp.ui.commandNum < 0) {
                gp.ui.commandNum = maxCommandNum;

            }
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            gp.ui.commandNum++;
            gp.playSE(9);
            if (gp.ui.commandNum > maxCommandNum) {
                gp.ui.commandNum = 0;
            }
        }
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            if (gp.ui.subState == 0) {
                if (gp.ui.commandNum == 1 && gp.music.volumeScale > 0) {
                    gp.music.volumeScale--;
                    gp.music.checkVolume();
                    gp.playSE(9);
                }
                if (gp.ui.commandNum == 2 && gp.se.volumeScale > 0) {
                    gp.se.volumeScale--;
                    gp.se.checkVolume();
                    gp.playSE(9);
                }
            }
        }
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            if (gp.ui.subState == 0) {
                if (gp.ui.commandNum == 1 && gp.music.volumeScale < 5) {
                    gp.music.volumeScale++;
                    gp.music.checkVolume();
                    gp.playSE(9);
                }
                if (gp.ui.commandNum == 2 && gp.se.volumeScale < 5) {
                    gp.se.volumeScale++;
                    gp.se.checkVolume();
                    gp.playSE(9);
                }
            }
        }

    }

    public void titleState(int code) {
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
                    gp.playSE(0);

                } else if (gp.ui.commandNum == 1) {
                    // gp.gameState = gp.LOADState;
                } else if (gp.ui.commandNum == 2) {
                    // gp.gameState = gp.OPTIONState;
                } else if (gp.ui.commandNum == 3) {
                    System.exit(0);
                }
            }
        } // SCENE CHOSE CHARACTER
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

    public void playState(int code) {
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
        if (code == KeyEvent.VK_F) {
            shotKeyPressed = true;
        }
        if (code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.optionsState;
        }
        // DEBUG
        if (code == KeyEvent.VK_T) {
            // checkDrawTime = !checkDrawTime;
            if (showDebugText == false) {
                showDebugText = true;
            } else if (showDebugText == true) {
                showDebugText = false;
            }
        }

        if (code == KeyEvent.VK_R) { // refresh
            switch (gp.currentMap) {
                case 0:
                    gp.tileM.loadMap("res/maps/worldV3.txt", 0);
                    break;
                case 1:
                    gp.tileM.loadMap("res/maps/interior01.txt", 1);
                    break;

            }
        }

        if (code == KeyEvent.VK_P) {
            gp.gameState = gp.pauseState;
        }
        if (code == KeyEvent.VK_I) {
            gp.gameState = gp.characterState;
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }
    }

    public void pauseState(int code) {
        if (code == KeyEvent.VK_P) {
            gp.gameState = gp.playState;
        }
    }

    public void dialogueState(int code) {
        if (code == KeyEvent.VK_ENTER) {
            gp.gameState = gp.playState;
        }
    }

    public void characterState(int code) {
        if (code == KeyEvent.VK_I) {
            gp.gameState = gp.playState;
        }
        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            if (gp.ui.slotRow != 0) {
                gp.ui.slotRow--;
                gp.playSE(9);
            }
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            if (gp.ui.slotRow != 3) {
                gp.ui.slotRow++;
                gp.playSE(9);
            }
        }
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            if (gp.ui.slotCol != 0) {
                gp.ui.slotCol--;
                gp.playSE(9);
            }
        }
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            if (gp.ui.slotCol != 4) {
                gp.ui.slotCol++;
                gp.playSE(9);
            }
        }
        if (code == KeyEvent.VK_ENTER) {
            gp.player.selectItem();
        }
    }

    public void gameOverState(int code) {
        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            gp.ui.commandNum--;
            if (gp.ui.commandNum < 0) {
                gp.ui.commandNum = 1;
            }
            gp.playSE(9);
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            gp.ui.commandNum++;
            if (gp.ui.commandNum > 1) {
                gp.ui.commandNum = 0;
            }
            gp.playSE(9);
        }

        if (code == KeyEvent.VK_ENTER) {
            if (gp.ui.commandNum == 0) {
                gp.gameState = gp.playState;
                gp.retry();
                gp.playMusic(0);
            } else if (gp.ui.commandNum == 1) {
                gp.gameState = gp.titleState;
                gp.restart();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
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
        if (code == KeyEvent.VK_F) {
            shotKeyPressed = false;
        }
    }
}
