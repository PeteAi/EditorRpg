package pack;

import java.awt.*;
import java.util.LinkedList;

import static pack.EditorRpg.rasterSize;
import static pack.EditorRpg.tex;

public class CreateBrush extends GameObject {
    public CreateBrush(float x, float y, int cellsize, boolean enabled, int metadata, int metadata2, int red, int green, int blue) {
        super(x, y, cellsize, enabled, metadata, metadata2, red, green, blue);
    }

    @Override
    public void tick(LinkedList<GameObject> object) {
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(tex.sprite[metadata2][metadata], (int) (x * 32), (int) (y * 32), rasterSize, rasterSize, null);
        if (enabled) {
            g.setColor(Color.red);
            g.drawRect((int) x * 32, (int) y * 32, 32, 32);
        }

    }

    @Override
    public Dimension getBounds() {
        return new Dimension(32, 32);
    }
}
