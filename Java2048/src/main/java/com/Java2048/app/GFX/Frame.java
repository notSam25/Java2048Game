package com.Java2048.app.GFX;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.Java2048.app.Game.GameBoard;
import com.Java2048.app.Game.Tile;
import com.Java2048.app.Game.GameBoard.MoveDirection;
import com.Java2048.app.Util.KeyboardHandler;

public class Frame extends JFrame {
    private class Panel extends JPanel {

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            // FIXME unimplemented method
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
    }

    /*
     * The logic handler for the game
     */
    public void handleGame() {
        // Randomly add tiles to board
        for (int c = 0; c < 4; c++) {
            for (int r = 0; r < 4; r++) {
                boolean addTile = (boolean) ((int) Math.round(Math.random()) == 1);
                if (addTile == true) {
                    this.m_GameBoard[c][r] = new Tile();
                }
            }
        }

        // FIXME this is debug code
        for (int c = 0; c < 4; c++) {
            for (int r = 0; r < 4; r++) {
                Tile curTile = this.m_GameBoard[c][r];
                System.out.print((curTile == null ? "-" : curTile.getCurValue()) + " ");
            }
            System.out.println();
        }

        while (true) {
            KeyboardHandler.update(); // Update all inputs
            // Handle new input

            if(KeyboardHandler.keys[KeyboardHandler.GetKey('w')].pressed) {
                this.m_GameBoard = GameBoard.playGame(this.m_GameBoard, MoveDirection.UP);
            } else if(KeyboardHandler.keys[KeyboardHandler.GetKey('a')].pressed) {
                this.m_GameBoard = GameBoard.playGame(this.m_GameBoard, MoveDirection.LEFT);
            } else if(KeyboardHandler.keys[KeyboardHandler.GetKey('s')].pressed) {
                this.m_GameBoard = GameBoard.playGame(this.m_GameBoard, MoveDirection.DOWN);
            } else if(KeyboardHandler.keys[KeyboardHandler.GetKey('d')].pressed) {
                this.m_GameBoard = GameBoard.playGame(this.m_GameBoard, MoveDirection.RIGHT);
            }

            // FIXME this is debug code
            for (int c = 0; c < 4; c++) {
                for (int r = 0; r < 4; r++) {
                    Tile curTile = this.m_GameBoard[c][r];
                    System.out.print((curTile == null ? "-" : curTile.getCurValue()) + " ");
                }
                System.out.println();
            }

            this.repaint(); // Draw to the window
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private Tile[][] m_GameBoard = new Tile[4][4];
    private static final String m_WindowName = "2048 | github.com/notSam25/Java2048/";
    private final Panel m_Panel = new Panel();
}