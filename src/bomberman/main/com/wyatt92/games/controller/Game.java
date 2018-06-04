package com.wyatt92.games.controller;

import com.wyatt92.games.model.GameBoard;
import com.wyatt92.games.view.GamePanel;



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

        // init Timer
        int fps = 60;
        double timePerTick = 1000000000 /fps;
        double delta = 0 ;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while(running) {
            // update Time and delta
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick; // number between 0 and 1
            timer += now - lastTime;
            lastTime = now;


            if(delta >= 1) {
                update();
                render();
                ticks++;
                delta--;
            }

            // reset Timer
            if(timer >= 1000000000) {
                System.out.println("Ticks and Frames: " + ticks); // shows FPS
                ticks = 0;
                timer = 0;
            }
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



    private void tick() {

    }

    private void update(){
        gamePanel.update();
    }

    private void render(){
        gamePanel.render(gamePanel.getGraphics());
    }


}
