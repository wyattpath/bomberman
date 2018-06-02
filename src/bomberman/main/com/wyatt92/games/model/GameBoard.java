package com.wyatt92.games.model;

public class GameBoard {

    // FIELDS

    // constants for size of game board
    private static final int ROW_COUNT = 13;
    private static final int COL_COUNT = 15;
    private static final int SQUARE_LENGTH = 40;


    // GETTERS AND SETTERS
    public static int getRowCount() {
        return ROW_COUNT;
    }

    public static int getColCount() {
        return COL_COUNT;
    }

    public static int getSquareLength() {
        return SQUARE_LENGTH;
    }
}
