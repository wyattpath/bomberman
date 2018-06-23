package com.wyatt92.games.controller;

import com.wyatt92.games.view.View;

import java.awt.*;

public class Launcher {
    private static final int WIDTH = 15 * 64;
    private static final int HEIGHT = 15 * 64 + 32;
    private static final String TITLE = "Bomberman";

    public static void main( String[] args) {


        // uses the Event dispatch thread by default -> higher priority thread that handles key events
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    View view = new View(TITLE, WIDTH, HEIGHT);
                    Game game = new Game(view);
                    game.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
