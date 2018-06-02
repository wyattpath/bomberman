package com.wyatt92.games.controller;

import com.wyatt92.games.model.GameBoard;
import com.wyatt92.games.view.GamePanel;

import java.awt.*;


public class Game {

    private static final Dimension DIM = new Dimension(GameBoard.getColCount() * GameBoard.getSquareLength(),
            GameBoard.getRowCount() * GameBoard.getSquareLength());

    public Game() {
        new GamePanel("Bomberman", DIM);

    }



}
