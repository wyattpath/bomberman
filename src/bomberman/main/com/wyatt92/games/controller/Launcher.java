package com.wyatt92.games.controller;

import java.awt.*;

public class Launcher {
    public static void main( String[] args) {
        // uses the Event dispatch thread by default -> higher priority thread that handles key events
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Game game = new Game();
                    game.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
