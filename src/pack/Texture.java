package pack;

import java.awt.image.BufferedImage;

public class Texture {

    SpriteSheet ts;


    private BufferedImage spriteSheet = null;

    public BufferedImage[][] sprite = new BufferedImage[32][32];

    public Texture() {

        BufferedImageLoader loader = new BufferedImageLoader();
        try {
        spriteSheet = loader.LoadImage("C:\\Users\\Pete Louis Benz\\Documents\\Gppcc\\rsc\\Blocks.png");
        } catch (Exception e) {
            e.printStackTrace();
        }
        ts = new SpriteSheet(spriteSheet);
        getTextures();
    }
    private void getTextures() {
        for(int y = 0;y<4;y++) {
            for(int x = 0;x<4;x++) {
                sprite[x][y] = ts.grabImage(x+1, y+1,32,32);
            }
        }
    }
}
