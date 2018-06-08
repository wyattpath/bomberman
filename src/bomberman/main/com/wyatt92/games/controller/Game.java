package com.wyatt92.games.controller;

import com.wyatt92.games.model.*;
import com.wyatt92.games.view.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;


public class Game implements Runnable{

    private static final int WIDTH = GameBoard.getColCount() * GameBoard.getSquareLength();
    private static final int HEIGHT = GameBoard.getRowCount() * GameBoard.getSquareLength();
    private static final String TITLE = "Bomberman";

    private boolean running = false;
    private Thread thread1;

    private GamePanel gamePanel;


    private KeyManager keyManager;

    private Graphics g;

    // STATES
    private State gameState;

    Game() {
        init();
        run();
    }

    // METHODS

    private void init() {
        gamePanel = new GamePanel(TITLE, WIDTH, HEIGHT);
        keyManager = new KeyManager();
        gamePanel.addKeyListener(keyManager);
//        gamePanel.setDoubleBuffered(true);

        gameState = new GameState(this);
        State.setCurrentState(gameState);
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
                gamePanel.draw(gamePanel.getGraphics());
                ticks++;
                delta--;
            }

            // reset Timer
            if(timer >= 1000000000) {
//                System.out.println("Ticks and Frames: " + ticks); // shows FPS
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


    private void update(){
        keyManager.update();
        if(State.getCurrentState() != null)
        {
            State.getCurrentState().update();
        }


        gamePanel.update();
//        if(keyManager.UP){
//            player.move("up");
//        } else if (keyManager.DOWN) {
//            player.move("down");
//        } else if(keyManager.LEFT) {
//            player.move("left");
//        } else if(keyManager.RIGHT) {
//            player.move("right");
//        }

    }

    private void render()
    {
//        bImg = gamePanel.getBufferedImage();
//        if (bImg == null)
//        {
//            gamePanel.createBufferedImage();
//            return;
//        }
//        g = bImg.getGraphics();

//        gamePanel.draw(g);
//        g.setColor(Color.YELLOW);
//        g.fillRect(0,0,WIDTH,HEIGHT);
//        g.clearRect(0,0,WIDTH,HEIGHT);
//        g.drawImage(Assets.player,0,0,null);
//        g.dispose();
//        gamePanel.draw(g);
//        player.draw(g);

    }

    public KeyManager getKeyManager()
    {
        return keyManager;
    }
}
