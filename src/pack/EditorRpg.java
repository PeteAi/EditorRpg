package pack;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;


public class EditorRpg extends JPanel {
    static BufferedImage Map = null;

    static int mouseX = 0;
    static int mouseY = 0;

    static int paintX = 0;
    static int paintY = 0;
    static int[] brush = new int[3];
    static boolean shift = false;
    static boolean save = false;
    static int storeStartX = 0;
    static int storeStartY = 0;
    static boolean rasterEnabled;

    static int levelX = 0;
    static int levelY = 0;

    static int Room = 0;

    static int screenSizex = 1000;
    static int screenSizey = 800;

    static int rasterSize = 32;
    static double scale = 0.0000;
    static double nebensumme = 0;
    static boolean Enemy = false;

    static int[][] red = new int[32][32];
    static int[][] green = new int[32][32];
    static int[][] blue = new int[32][32];


    static JFrame frame = new JFrame();

    static Texture tex;
    static Handler handler;

    static public void main(String args[]) {
        new Window(screenSizex, screenSizey, "Editor", frame);
        new MouseInput();


    }

    public EditorRpg() {

        rasterEnabled = false;

        for (int i = 0; i < 3; i++) {
            brush[i] = 0;
        }
        handler = new Handler();
        int index = 0;
        for (int yy = 0; yy < 32; yy++) {
            int index2 = 0;
            for (int xx = 32; xx < 64; xx++) {
                handler.addObject(new CreateBrush(xx, yy, 32, false, index, index2, 0, 0, 0));
                index2 += 1;
            }
            index += 1;
            index2 = 0;

        }
        BufferedImageLoader loader = new BufferedImageLoader();
        Map = loader.LoadImage("C:\\Users\\Pete Louis Benz\\Desktop\\Java\\Rpg\\rsc\\Map.png");
        fillarray(0, 0);
        tex = new Texture();
    }

    public void paintComponent(Graphics g) {
        Graphics2D gd2 = (Graphics2D) g;
        screenSizex = frame.getWidth();
        screenSizey = frame.getHeight() - 41;
        //Fensterbalkenhöhe muss aggezogen werden
        scale = (double) screenSizey / 1024.0;
        gd2.setColor(Color.black);
        gd2.fillRect(0, 0, screenSizex, screenSizey);
        gd2.scale(scale, scale);
        for (int yy = 0; yy < 32; yy++) {
            for (int xx = 0; xx < 32; xx++) {
                gd2.drawImage(tex.sprite[red[xx][yy]][green[xx][yy]], xx * 32, yy * 32, this);
                if (rasterEnabled) {
                    g.setColor(Color.red);
                    gd2.drawRect(xx * 32, yy * 32, 32, 32);
                }
            }
        }
        gd2.setColor(Color.WHITE);
        //Die 1 ist damit man das ganze Tile sehen kann
        nebensumme = (((double) screenSizex / (double) screenSizey) - 1) * 1024;
        gd2.fillRect(1024 + 1, 0, (int) nebensumme, 1024);

        handler.render(g);
        g.setColor(Color.black);
        g.drawString("LevelX: " + String.valueOf(levelX), 1050, 1000);
        g.drawString("LevelY: " + String.valueOf(levelY), 1150, 1000);
        if (save == true) {
            g.drawString("Saved", 1050, 950);
        }
        if (Enemy) {
            g.drawString("Enemy", 1050, 900);
        }
        g.drawString(String.valueOf(Room), 1050, 850);
        repaint();

    }

    public static void savePng(int levX, int levY) {
        try {

            // TYPE_INT_ARGB specifies the image format: 8-bit RGBA packed
            // into integer pixels
            BufferedImage bi = new BufferedImage(1024, 1024, BufferedImage.TYPE_INT_ARGB);
            BufferedImageLoader loader = new BufferedImageLoader();
            Map = loader.LoadImage("C:\\Users\\Pete Louis Benz\\Desktop\\Java\\Rpg\\rsc\\Map.png");

            Graphics2D gd2 = bi.createGraphics();
            gd2.drawImage(Map, 0, 0, null);
            for (int yy = levY * 32; yy < 32 + levY * 32; yy++) {
                for (int xx = levX * 32; xx < 32 + levX * 32; xx++) {
                    //draw here
                    gd2.setColor(new Color(red[xx - levX * 32][yy - levY * 32], green[xx - levX * 32][yy - levY * 32], blue[xx - levX * 32][yy - levY * 32]));
                    gd2.fillRect(xx, yy, 1, 1);
                }
            }
            ImageIO.write(bi, "PNG", new File("C:\\Users\\Pete Louis Benz\\Desktop\\Java\\Rpg\\rsc\\Map.PNG"));
            ImageIO.write(bi, "PNG", new File("C:\\Users\\Pete Louis Benz\\Desktop\\Java\\Rpg\\rsc\\EditorMap.PNG"));


            //ImageIO.write(bi, "JPEG", new File("/Users/Home/Desktop/Java/Rpg/rsc/EditorMap.JPG"));
            //System.out.println("Saved");
        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }

    public static Texture getInstance() {
        return tex;
    }

    public static void fillarray(int levelX, int levelY) {
        for (int yy = levelY * 32; yy < levelY * 32 + 32; yy++) {
            for (int xx = levelX * 32; xx < levelX * 32 + 32; xx++) {
                int pixel = Map.getRGB(xx, yy);
                int reda = (pixel >> 16) & 0xff;
                int greena = (pixel >> 8) & 0xff;
                int bluea = (pixel) & 0xff;

                red[xx - levelX * 32][yy - levelY * 32] = reda;
                green[xx - levelX * 32][yy - levelY * 32] = greena;
                blue[xx - levelX * 32][yy - levelY * 32] = bluea;
            }
        }
    }
}