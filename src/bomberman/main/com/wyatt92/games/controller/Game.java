package com.wyatt92.games.controller;

import com.wyatt92.games.model.GameBoard;
import com.wyatt92.games.view.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Game implements Runnable{

    private static final int WIDTH = GameBoard.getColCount() * GameBoard.getSquareLength();
    private static final int HEIGHT = GameBoard.getRowCount() * GameBoard.getSquareLength();
    private static final String TITLE = "Bomberman";

    private boolean running = false;
    private Thread thread1;

    private GamePanel gamePanel;

    Game() {
        init();
        run();
    }

    // METHODS

    private void init() {
        gamePanel = new GamePanel(TITLE, WIDTH, HEIGHT);
    }
    public void run() {

        while(running) {
            update();
            render();
        }

        stop();
    }

    synchronized void start(){
        if(running) return;
        running = true;
        thread1 = new Thread(this);
        thread1.start();
    }

    private synchronized void stop(){
        if(!running) return;
        running = false;
        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void update(){
        gamePanel.update(gamePanel.getGraphics());
    }

    private void render(){

    }


}
