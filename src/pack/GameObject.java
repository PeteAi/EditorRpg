package pack;


import java.awt.*;
import java.util.LinkedList;

public abstract class GameObject {

    protected float x, y;
    protected int metadata;
    protected int metadata2;
    protected int cellsize;
    protected boolean enabled;
    protected int red;
    protected int green;
    protected int blue;


    public GameObject(float x, float y, int cellsize,boolean enabled, int metadata,int metadata2, int red, int green, int blue) {
        this.x = x;
        this.y = y;
        this.enabled = enabled;
        this.metadata = metadata;
        this.metadata2 = metadata2;
        this.cellsize = cellsize;
        this.red = red;
        this.green = green;
        this.blue = blue;

    }

    public abstract void tick(LinkedList<GameObject> object);

    public abstract void render(Graphics g);

    public abstract Dimension getBounds();


    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setCellsize(int cellsize) { this.cellsize = cellsize; }

    public int getCellsize() { return cellsize; }

    public void setMetadata(int Mdata) { this.metadata = Mdata; }

    public void setMetadata2(int metadata2) { this.metadata2 = metadata2; }

    public int getMetadata() { return metadata; }

    public int getMetadata2() { return metadata2; }

    public void setBlue(int blue) { this.blue = blue; }

    public void setGreen(int green) { this.green = green; }

    public void setRed(int red) { this.red = red; }

    public int getRed() { return red; }

    public int getGreen() { return green; }

    public int getBlue() { return blue; }

    public boolean isEnabled() { return enabled; }

    public void setEnabled(boolean enabled) { this.enabled = enabled; }
}
