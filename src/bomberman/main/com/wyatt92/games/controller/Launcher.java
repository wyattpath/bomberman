package com.wyatt92.games.controller;

import com.wyatt92.games.model.Model;
import com.wyatt92.games.view.View;

import java.awt.*;

class Launcher {
    private static final int WIDTH = 13 * 64 + 642;
    private static final int HEIGHT = 13 * 64 + 32;
    private static final String TITLE = "Bomberman";

    public static void main( String[] args) {


        // uses the Event dispatch thread by default -> higher priority thread that handles key events
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Model model = new Model();
                    View view = new View(model);
                    Controller controller = new Controller(view, model);
                    controller.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
