package com.wyatt92.games.controller;

import com.wyatt92.games.model.Assets;
import com.wyatt92.games.model.World;
import com.wyatt92.games.model.states.GameOverState;
import com.wyatt92.games.model.states.GameState;
import com.wyatt92.games.model.states.MenuState;
import com.wyatt92.games.model.states.State;
import com.wyatt92.games.model.ui.UIManager;
import com.wyatt92.games.view.View;


/**
 * Controls and updates the view.
 * Keeps View and Model separated.
 */
public class Game implements Runnable{

    private boolean running = false;
    private Thread thread1;

    private View view;

    // States
    private State gameState;
    private State menuState;
    private State gameOverState;

    // World
    private World world;

    // Input
    private GameKeyListener gameKeyListener;
    private GameMouseListener gameMouseListener;
    private UIManager uiManager;

    Game(View view) {
        this.view = view;
        gameKeyListener = new GameKeyListener();
        gameMouseListener = new GameMouseListener();
        uiManager = new UIManager();
        gameMouseListener.setUiManager(uiManager);
        init();
        run();
    }

    // METHODS

    private void init() {
        view.getGamePanel().addKeyListener(gameKeyListener);
        view.getGamePanel().addMouseListener(gameMouseListener);
        view.getGamePanel().addMouseMotionListener(gameMouseListener);
        Assets.init();
//        gamePanel.setDoubleBuffered(true);

        world = new World("world1.txt");
        gameState = new GameState(world);
        menuState = new MenuState(world, gameState, uiManager);
        gameOverState = new GameOverState(world);
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
//                gamePanel.draw(gamePanel.getGraphics());
                view.repaint();
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
        gameKeyListener.update();
        if(world.getPlayerCount()>0){
            for(int i = 0; i < world.getPlayerCount(); i++){
                world.getEntityManager().getPlayer(i).xMove = 0;
                world.getEntityManager().getPlayer(i).yMove = 0;
            }
        }
        {
            if (world.getEntityManager().getPlayer(0) != null)

            if (gameKeyListener.W)
                world.getEntityManager().getPlayer(0).moveUp();
            if (gameKeyListener.S)
                world.getEntityManager().getPlayer(0).moveDown();
            if (gameKeyListener.A)
                world.getEntityManager().getPlayer(0).moveLeft();
            if (gameKeyListener.D)
                world.getEntityManager().getPlayer(0).moveRight();
            if (gameKeyListener.SPACE)
                world.getEntityManager().getPlayer(0).placeBomb();


            if (world.getEntityManager().getPlayer(1) != null)
            {
                world.getEntityManager().getPlayer(1).xMove = 0;
                world.getEntityManager().getPlayer(1).yMove = 0;

                if (gameKeyListener.UP)
                    world.getEntityManager().getPlayer(1).moveUp();
                if (gameKeyListener.DOWN)
                    world.getEntityManager().getPlayer(1).moveDown();
                if (gameKeyListener.LEFT)
                    world.getEntityManager().getPlayer(1).moveLeft();
                if (gameKeyListener.RIGHT)
                    world.getEntityManager().getPlayer(1).moveRight();
                if (gameKeyListener.CTRL)
                    world.getEntityManager().getPlayer(1).placeBomb();
            }
        }
        if(State.getCurrentState() != null)
        {
            State.getCurrentState().update();
        }


    }
}
