package pack;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


import static pack.EditorRpg.*;

public class MouseInput implements MouseListener {
    static boolean shiftStart = false;

    public MouseInput() {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseX = e.getX() - 8;
        mouseY = e.getY() - 31;
        paintX = (int) (mouseX / (32.0 * scale));
        paintY = (int) (mouseY / (32.0 * scale));
        if (mouseX < screenSizey) {
            if (!Enemy) {
                save = false;

                if (shift) {
                    if (shiftStart) {
                        storeStartX = paintX;
                        storeStartY = paintY;
                        shiftStart = false;
                    } else {
                        for (int j = storeStartX; j <= paintX; j++) {
                            for (int i = storeStartY; i <= paintY; i++) {
                                red[j][i] = brush[0];
                                green[j][i] = brush[1];
                                blue[j][i] = brush[2];
                            }
                        }
                    }
                } else {
                    red[paintX][paintY] = brush[0];
                    green[paintX][paintY] = brush[1];
                    blue[paintX][paintY] = brush[2];
                }
            } else {
                JSONReader.writeJSON("Enemy", paintX + levelX * 32, paintY + levelY * 32, Room);
            }
        } else {
            Point point = new Point();
            point.x = (int) (mouseX / (32.0 * scale));
            point.y = (int) (mouseY / (32.0 * scale));

            for (int i = 0; i < handler.object.size(); i++) {
                handler.object.get(i).setEnabled(false);

                int X = (int) handler.object.get(i).getX();
                int Y = (int) handler.object.get(i).getY();

                if (point.x == X && point.y == Y) {
                    handler.object.get(i).setEnabled(true);
                    brush[0] = handler.object.get(i).getMetadata2();
                    brush[1] = handler.object.get(i).getMetadata();
                    brush[2] = handler.object.get(i).getBlue();
                }

            }
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
