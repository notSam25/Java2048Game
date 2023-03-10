package com.Java2048.app.Game;

public class Tile {
    public Tile() {
        this.curValue = (Math.ceil(Math.random()) + 1) == 2 ? 2 : 4;
    }

    /*
     * Returns the current number value of the tile.
     * 
     * @return the number value of the tile
     */
    public int getCurValue() {
        return curValue;
    }

    /*
     * Sets the value of the tile
     */
    public void setCurValue(int curValue) {
        this.curValue = curValue;
    }

    private int curValue = 0;
}