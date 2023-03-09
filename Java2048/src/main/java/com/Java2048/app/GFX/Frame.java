package com.Java2048.app.GFX;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.Java2048.app.Util.KeyboardHandler;

public class Frame extends JFrame {
    private class Panel extends JPanel {

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            g.dispose();
        }
    }

    /*
     * Starts the window for drawing the game
     */
    public Frame() {
        m_Panel.setDoubleBuffered(true);
        m_Panel.setPreferredSize(new Dimension(400, 400));

        this.setTitle(m_WindowName);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocation(new Point(75, 200));
        this.setUndecorated(false);
        this.add(this.m_Panel);
        this.pack();
        this.setVisible(true);
        this.addKeyListener(KeyboardHandler.getListener());

        while(true) {
            KeyboardHandler.update();
            this.repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static final String m_WindowName = "2048 | github.com/notSam25/Java2048/";
    private final Panel m_Panel = new Panel();
}