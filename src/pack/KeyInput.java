package pack;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static pack.EditorRpg.*;
import static pack.MouseInput.shiftStart;

public class KeyInput extends KeyAdapter {
    static boolean combo = false;

    public KeyInput() {

    }

    @Override

    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        //System.out.println(code);
        if (code == 17) {
            combo = true;
        }
        if (code == KeyEvent.VK_S && combo) {
            savePng(levelX, levelY);
            save = true;
        }
        if (code == KeyEvent.VK_G && combo) {
            if (rasterEnabled) {
                rasterEnabled = false;
            } else {
                rasterEnabled = true;
            }
        }
        if (code == KeyEvent.VK_SHIFT) {
            if (!shift) {
                shiftStart = true;
            }
            shift = true;

        }
        //LEVEL
        if (code == KeyEvent.VK_PLUS) {
            Room++;
        }
        if (code == KeyEvent.VK_MINUS) {
            if (Room > 0) {
                Room--;
            }
        }
        if (code == KeyEvent.VK_UP) {
            savePng(levelX, levelY);
            if (levelY > 0) {
                levelY -= 1;
            }
            fillarray(levelX, levelY);
        } else if (code == KeyEvent.VK_DOWN) {
            savePng(levelX, levelY);
            if (levelY < 31) {
                levelY += 1;
            }
            fillarray(levelX, levelY);
        } else if (code == KeyEvent.VK_LEFT) {
            savePng(levelX, levelY);
            if (levelX > 0) {
                levelX -= 1;
            }
            fillarray(levelX, levelY);
        } else if (code == KeyEvent.VK_RIGHT) {
            savePng(levelX, levelY);
            if (levelX < 31) {
                levelX += 1;
            }
            fillarray(levelX, levelY);
        } else if (code == KeyEvent.VK_E) {
            if (!Enemy) {
                Enemy = true;
            } else {
                Enemy = false;
            }
        }

        if (code == 48) {
            for (int i = 0; i < handler.object.size(); i++) {
                handler.object.get(i).setEnabled(false);
                GameObject tempObject = handler.object.get(i);
                if (tempObject.getX() == 0 + 32 && tempObject.getY() == 0) {
                    handler.object.get(i).setEnabled(true);
                    brush[0] = 0;
                    brush[1] = 0;
                }
            }
        }
        if (code == 57) {
            for (int i = 0; i < handler.object.size(); i++) {
                handler.object.get(i).setEnabled(false);
                GameObject tempObject = handler.object.get(i);
                if (tempObject.getX() == 3 + 32 && tempObject.getY() == 3) {
                    handler.object.get(i).setEnabled(true);
                    brush[0] = 3;
                    brush[1] = 3;
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        combo = false;
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_SHIFT) {
            shift = false;
            shiftStart = false;
        }
    }
}
