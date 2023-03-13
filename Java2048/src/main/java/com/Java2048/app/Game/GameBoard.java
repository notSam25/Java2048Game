package com.Java2048.app.Game;

public class GameBoard {

    public enum MoveDirection {
        LEFT,
        RIGHT,
        UP,
        DOWN
    }

    /*
     * Returns if the tile is within the game board in array notation.
     * 
     * @param col the column to check
     * 
     * @param row the row to check
     * 
     * @return the boolean if the col and row are within bounds of the board.
     */
    private static boolean canMove(int col, int row) {
        return (col >= 0 && col < 4 && row >= 0 && row < 4);
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
                for (int c = col; c < 4; c++) {
                    if (canMove(c + 1, row)) { // If the next position is on the board
                        Tile nextTile = currentBoardTiles[c + 1][row];
                        if(nextTile == null) {
                            canMove++;
                        } else if(nextTile.getCurValue() == curTile.getCurValue()) {
                            canMove++;
                        } else {
                            return canMove;
                        }
                    } else {
                        return canMove;
                    }
                }
                return canMove;
            }
            case LEFT: {
                int canMove = 0;
                for (int r = row; r > 0; r--) {
                    if (canMove(col, r - 1)) { // If the next position is on the board
                        Tile nextTile = currentBoardTiles[col][r - 1];
                        if(nextTile == null) {
                            canMove++;
                        } else if(nextTile.getCurValue() == curTile.getCurValue()) {
                            canMove++;
                        } else {
                            System.out.println("value mismatch");
                            return canMove;
                        }
                    } else {
                        System.out.println("!canMove");
                        return canMove;
                    }
                }
                return canMove;
            }
            case RIGHT: {
                int canMove = 0;
                for (int r = row; r < 4; r++) {
                    if (canMove(col, r + 1)) { // If the next position is on the board
                        Tile nextTile = currentBoardTiles[col][r + 1];
                        if(nextTile == null) {
                            canMove++;
                        } else if(nextTile.getCurValue() == curTile.getCurValue()) {
                            canMove++;
                        } else {
                            return canMove;
                        }
                    } else {
                        return canMove;
                    }
                }
                return canMove;
            }
            case UP: {
                int canMove = 0;
                for (int c = col; c > 0; c--) {
                    if (canMove(c - 1, row)) { // If the next position is on the board
                        Tile nextTile = currentBoardTiles[c - 1][row];
                        if(nextTile == null) {
                            canMove++;
                        } else if(nextTile.getCurValue() == curTile.getCurValue()) {
                            canMove++;
                        } else {
                            return canMove;
                        }
                    } else {
                        return canMove;
                    }
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
                        if(currentBoardTiles[c][r] != null) {
                            int tilesToMove = getMaxMoveTiles(currentBoardTiles, c, r, direction);
                            System.out.printf("[%d][%d] Down: %d\n", r, c, tilesToMove);
                            // If we need to move a tile
                            if(tilesToMove > 0) {
                                // If the tile we want to travel to is !empty
                                if(currentBoardTiles[c + tilesToMove][r] != null) {
                                    // Replace the tile with our current tile with double the value
                                    currentBoardTiles[c + tilesToMove][r] = new Tile(currentBoardTiles[c][r].getCurValue() * 2);
                                }
                                else {
                                    // Set the new tile to the current position
                                    currentBoardTiles[c + tilesToMove][r] = currentBoardTiles[c][r];
                                }

                                // Remove our current tile from its previous position
                                currentBoardTiles[c][r] = null;
                            }
                        }
                        break;
                    case LEFT:
                        if(currentBoardTiles[c][r] != null) {
                            int tilesToMove = getMaxMoveTiles(currentBoardTiles, c, r, direction);
                            System.out.printf("[%d][%d] Left: %d\n", r, c, tilesToMove);

                            // If we need to move a tile
                            if(tilesToMove > 0) {
                                // If the tile we want to travel to is !empty
                                if(currentBoardTiles[c][r - tilesToMove] != null) {
                                    // Replace the tile with our current tile with double the value
                                    currentBoardTiles[c][r - tilesToMove] = new Tile(currentBoardTiles[c][r].getCurValue() * 2);
                                }
                                else {
                                    // Set the new tile to the current position
                                    currentBoardTiles[c][r - tilesToMove] = currentBoardTiles[c][r];
                                }

                                // Remove our current tile from its previous position
                                currentBoardTiles[c][r] = null;
                            }
                        }
                        break;
                    case RIGHT:
                        if(currentBoardTiles[c][r] != null) {
                            int tilesToMove = getMaxMoveTiles(currentBoardTiles, c, r, direction);
                            System.out.printf("[%d][%d] Right: %d\n", r, c, tilesToMove);
                            // If we need to move a tile
                            if(tilesToMove > 0) {
                                // If the tile we want to travel to is !empty
                                if(currentBoardTiles[c][r + tilesToMove] != null) {
                                    // Replace the tile with our current tile with double the value
                                    currentBoardTiles[c][r + tilesToMove] = new Tile(currentBoardTiles[c][r].getCurValue() * 2);
                                }
                                else {
                                    // Set the new tile to the current position
                                    currentBoardTiles[c][r + tilesToMove] = currentBoardTiles[c][r];
                                }

                                // Remove our current tile from its previous position
                                currentBoardTiles[c][r] = null;
                            }
                        }
                        break;
                    case UP:
                        if(currentBoardTiles[c][r] != null) {
                            int tilesToMove = getMaxMoveTiles(currentBoardTiles, c, r, direction);
                            System.out.printf("[%d][%d] Up: %d\n", r, c, tilesToMove);
                            // If we need to move a tile
                            if(tilesToMove > 0) {
                                // If the tile we want to travel to is !empty
                                if(currentBoardTiles[c - tilesToMove][r] != null) {
                                    // Replace the tile with our current tile with double the value
                                    currentBoardTiles[c - tilesToMove][r] = new Tile(currentBoardTiles[c][r].getCurValue() * 2);
                                }
                                else {
                                    // Set the new tile to the current position
                                    currentBoardTiles[c - tilesToMove][r] = currentBoardTiles[c][r];
                                }

                                // Remove our current tile from its previous position
                                currentBoardTiles[c][r] = null;
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