package com.Java2048.app.GFX;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.Java2048.app.Game.GameBoard;
import com.Java2048.app.Game.Tile;
import com.Java2048.app.Game.GameBoard.MoveDirection;
import com.Java2048.app.Util.KeyboardHandler;

public class Frame extends JFrame {
    private static class Panel extends JPanel {

        /*
         * An override of the component method that draws the window for the game.
         *
         * @note see Oracle documentation for information regarding this method.
         */
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Draw the board outline
            g.setColor(Color.black);

            g.drawLine(0, 150, 600, 150);
            g.drawLine(0, 300, 600, 300);
            g.drawLine(0, 450, 600, 450);

            g.drawLine(150, 0, 150, 600);
            g.drawLine(300, 0, 300, 600);
            g.drawLine(450, 0, 450, 600);

            // FIXME draw the numbers for the squares
            for (int c = 0; c < 4; c++) {
                for (int r = 0; r < 4; r++) {
                    Tile curTile = this.m_GameBoard[c][r];
                    g.drawString("" + (curTile == null ? "" : curTile.getCurValue()), 75 + (150 * r), 75 + (150 * c));
                }
            }

            g.dispose();
        }

        /*
         * Updates the game board array.
         *
         * @param gameBoard the board to update for drawing
         */
        public void updateGameBoard(Tile[][] gameBoard) {
            m_GameBoard = gameBoard.clone();
        }
        private Tile[][] m_GameBoard = new Tile[4][4];
    }

    /*
     * Starts the window for drawing the game.
     */
    public Frame() {
        this.m_Panel.setDoubleBuffered(true);
        this.m_Panel.setPreferredSize(new Dimension(600, 600));

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
     * The logic handler for the game.
     */
    public void handleGame() {
        // Randomly add tiles to board

        for (int c = 0; c < 4; c++) {
            for (int r = 0; r < 4; r++) {
                boolean addTile = ((int) Math.round(Math.random()) == 1);
                if (addTile) {
                    this.m_GameBoard[c][r] = new Tile();
                }
            }
        }

        while (true) {
            KeyboardHandler.update(); // Update all inputs

            if (KeyboardHandler.keys[KeyboardHandler.GetKey('w')].pressed) { // Handle user input
                this.m_GameBoard = GameBoard.playGame(this.m_GameBoard, MoveDirection.UP);
            } else if (KeyboardHandler.keys[KeyboardHandler.GetKey('a')].pressed) {
                this.m_GameBoard = GameBoard.playGame(this.m_GameBoard, MoveDirection.LEFT);
            } else if (KeyboardHandler.keys[KeyboardHandler.GetKey('s')].pressed) {
                this.m_GameBoard = GameBoard.playGame(this.m_GameBoard, MoveDirection.DOWN);
            } else if (KeyboardHandler.keys[KeyboardHandler.GetKey('d')].pressed) {
                this.m_GameBoard = GameBoard.playGame(this.m_GameBoard, MoveDirection.RIGHT);
            }

            this.m_Panel.updateGameBoard(this.m_GameBoard); // Update the game board before we render
            this.repaint(); // Draw to the window
            try {
                Thread.sleep(10);
            } catch (InterruptedException | IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }
    private final Panel m_Panel = new Panel();
    private Tile[][] m_GameBoard = new Tile[4][4];
    private static final String m_WindowName = "2048 | github.com/notSam25/Java2048/";
}