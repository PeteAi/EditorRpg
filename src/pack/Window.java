package pack;

import javax.swing.*;

public class Window {

    public Window(int width, int height, String title,JFrame frame) {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setSize(width,height);
        frame.getContentPane().add(new EditorRpg());
        frame.setTitle(title);
        frame.setFocusable(true);
        frame.addMouseListener(new MouseInput());
        frame.addKeyListener(new KeyInput());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}