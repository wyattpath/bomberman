package com.wyatt92.games.controller;

import com.wyatt92.games.model.Entity;
import com.wyatt92.games.model.Model;
import com.wyatt92.games.model.Player;

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
