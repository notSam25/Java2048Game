package com.Java2048.app.Game;

public class GameBoard {

    public static enum MoveDirection {
        LEFT,
        RIGHT,
        UP,
        DOWN
    };

    /*
     * Returns if the tile is within the game board.
     * 
     * @param col the column to check
     * 
     * @param row the row to check
     * 
     * @return the boolean if the col and row are within bounds of the board.
     */
    private static boolean canMove(int col, int row) {
        return (col > 0 && col < 4 && row > 0 && row < 4);
    }

    /*
     * Returns the maximum tile to move based on the desired direction.
     * 
     * @param currentBoardTiles the game board
     * 
     * @param col the current column
     * 
     * @param row the current row
     * 
     * @param direction the direction desired to move towards
     * 
     * @return the number of tiles to move in array notation
     */
    private static int getMaxMoveTiles(Tile[][] currentBoardTiles, int col, int row, MoveDirection direction) {
        Tile curTile = currentBoardTiles[col][row];
        switch (direction) {
            case DOWN: {
                int canMove = 0;
                for (int c = col; c < 4; c--) {
                    if (canMove(c - 1, row)) {
                        Tile nextTile = currentBoardTiles[c - 1][row];
                        if (nextTile == null) {
                            canMove--;
                            continue;
                        } else if (curTile.getCurValue() == nextTile.getCurValue()) {
                            return c - 1;
                        }
                    } else
                        return canMove;
                }
                return canMove;
            }
            case LEFT: {
                int canMove = 0;
                for (int r = row; r < 4; r--) {
                    if (canMove(col, r - 1)) {
                        Tile nextTile = currentBoardTiles[col][r - 1];
                        if (nextTile == null) {
                            canMove--;
                            continue;
                        } else if (curTile.getCurValue() == nextTile.getCurValue()) {
                            return r - 1;
                        }
                    } else
                        return canMove;
                }
                return canMove;
            }
            case RIGHT: {
                int canMove = 0;
                for (int r = row; r < 4; r--) {
                    if (canMove(col, r - 1)) {
                        Tile nextTile = currentBoardTiles[col][r - 1];
                        if (nextTile == null) {
                            canMove--;
                            continue;
                        } else if (curTile.getCurValue() == nextTile.getCurValue()) {
                            return r - 1;
                        }
                    } else
                        return canMove;
                }
                return canMove;
            }
            case UP: {
                int canMove = 0;
                for (int c = col; c < 4; c--) {
                    if (canMove(c - 1, row)) {
                        Tile nextTile = currentBoardTiles[c - 1][row];
                        if (nextTile == null) {
                            canMove--;
                            continue;
                        } else if (curTile.getCurValue() == nextTile.getCurValue()) {
                            return c - 1;
                        }
                    } else
                        return canMove;
                }
                return canMove;
            }
        }
        return 0;
    }

    /*
     * Returns the tile array for the game board.
     * 
     * @param previousTiles the current game board to be modified
     * 
     * @param direction the direction the user wants to move to
     * 
     * @return the new updated game board
     */
    public static Tile[][] playGame(Tile[][] previousTiles, MoveDirection direction) {
        Tile[][] currentBoardTiles = previousTiles.clone();
        for (int c = 0; c < 4; c++) {
            for (int r = 0; r < 4; r++) {
                switch (direction) {
                    case DOWN:
                        // Check if the input is on the board
                        if (canMove(c - 1, r)) {
                            int tilesToMove = getMaxMoveTiles(currentBoardTiles, c, r, direction);
                            if (currentBoardTiles[c - tilesToMove][r] == null) { // If there is no tile
                                currentBoardTiles[c - tilesToMove][r] = currentBoardTiles[c][r];
                                currentBoardTiles[c] = null;
                            } else {
                                // If there is a tile replace our tile with it, and double the value.
                                currentBoardTiles[c - tilesToMove][r] = currentBoardTiles[c][r];
                                currentBoardTiles[c][r] = null;
                                currentBoardTiles[c - tilesToMove][r]
                                        .setCurValue(currentBoardTiles[c - tilesToMove][r].getCurValue() * 2);
                            }
                        }
                        break;
                    case LEFT:
                        // Check if the input is on the board
                        if (canMove(c, r - 1)) {
                            int tilesToMove = getMaxMoveTiles(currentBoardTiles, c, r, direction);
                            if (currentBoardTiles[c][r - tilesToMove] == null) { // If there is no tile
                                currentBoardTiles[c][r - tilesToMove] = currentBoardTiles[c][r];
                                currentBoardTiles[c] = null;
                            } else {
                                // If there is a tile replace our tile with it, and double the value.
                                currentBoardTiles[c][r - tilesToMove] = currentBoardTiles[c][r];
                                currentBoardTiles[c][r] = null;
                                currentBoardTiles[c][r - tilesToMove]
                                        .setCurValue(currentBoardTiles[c][r - tilesToMove].getCurValue() * 2);
                            }
                        }
                        break;
                    case RIGHT:
                        // Check if the input is on the board
                        if (canMove(c, r + 1)) {
                            int tilesToMove = getMaxMoveTiles(currentBoardTiles, c, r, direction);
                            if (currentBoardTiles[c][r + tilesToMove] == null) { // If there is no tile
                                currentBoardTiles[c][r + tilesToMove] = currentBoardTiles[c][r];
                                currentBoardTiles[c] = null;
                            } else {
                                // If there is a tile replace our tile with it, and double the value.
                                currentBoardTiles[c][r + tilesToMove] = currentBoardTiles[c][r];
                                currentBoardTiles[c][r] = null;
                                currentBoardTiles[c][r + tilesToMove]
                                        .setCurValue(currentBoardTiles[c][r + tilesToMove].getCurValue() * 2);
                            }
                        }
                        break;
                    case UP:
                        // Check if the input is on the board
                        if (canMove(c - 1, r)) {
                            int tilesToMove = getMaxMoveTiles(currentBoardTiles, c, r, direction);
                            if (currentBoardTiles[c - tilesToMove][r] == null) { // If there is no tile
                                currentBoardTiles[c - tilesToMove][r] = currentBoardTiles[c][r];
                                currentBoardTiles[c] = null;
                            } else {
                                // If there is a tile replace our tile with it, and double the value.
                                currentBoardTiles[c - tilesToMove][r] = currentBoardTiles[c][r];
                                currentBoardTiles[c][r] = null;
                                currentBoardTiles[c - tilesToMove][r]
                                        .setCurValue(currentBoardTiles[c - tilesToMove][r].getCurValue() * 2);
                            }
                        }
                        break;
                    default:
                        break;
                }
            }
        }

        return currentBoardTiles;
    }
}