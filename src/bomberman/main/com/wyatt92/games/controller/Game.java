package com.wyatt92.games.controller;

import com.wyatt92.games.model.Assets;
import com.wyatt92.games.model.states.GameState;
import com.wyatt92.games.model.states.MenuState;
import com.wyatt92.games.model.states.State;
import com.wyatt92.games.view.GamePanel;

import java.awt.*;


public class Game implements Runnable{

    private static final int WIDTH = 1080;
    private static final int HEIGHT = 1080;
    private static final String TITLE = "Bomberman";

    private boolean running = false;
    private Thread thread1;

    private GamePanel gamePanel;

    private Graphics g;

    // States
    private State gameState;
    private State menuState;

    // Input
    private KeyManager keyManager;
    private MouseManager mouseManager;

    // Handler
    private Handler handler;

    Game() {
        keyManager = new KeyManager();
        mouseManager = new MouseManager();
        init();
        run();
    }

    // METHODS

    private void init() {
        gamePanel = new GamePanel(TITLE, WIDTH, HEIGHT);
        gamePanel.addKeyListener(keyManager);
        gamePanel.addMouseListener(mouseManager);
        gamePanel.addMouseMotionListener(mouseManager);
        Assets.init();
//        gamePanel.setDoubleBuffered(true);

        handler = new Handler(this);
        gameState = new GameState(handler);
        menuState = new MenuState(handler);
        State.setCurrentState(menuState);
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
    }

    private void render()
    {

    }

    State getGameState() {
        return gameState;
    }

    public KeyManager getKeyManager()
    {
        return keyManager;
    }

    public MouseManager getMouseManager()
    {
        return mouseManager;
    }

    public int getWidth() {
        return WIDTH;
    }

    public int getHeight()
    {
        return HEIGHT;
    }
}
