package com.wyatt92.games.controller;

import com.wyatt92.games.model.Assets;
import com.wyatt92.games.model.World;
import com.wyatt92.games.model.states.GameState;
import com.wyatt92.games.model.states.MenuState;
import com.wyatt92.games.model.states.State;
import com.wyatt92.games.model.ui.Clicker;
import com.wyatt92.games.model.ui.UIImageButton;
import com.wyatt92.games.model.ui.UIManager;
import com.wyatt92.games.view.GamePanel;

import java.awt.*;


/**
 * Controls and updates the view.
 * Keeps View and Model separated.
 */
public class Game implements Runnable{

    private static final int WIDTH = 15 * 64;
    private static final int HEIGHT = 15 * 64 + 32;
    private static final String TITLE = "Bomberman";

    private boolean running = false;
    private Thread thread1;

    private GamePanel gamePanel;

    private Graphics g;

    // States
    private State gameState;
    private State menuState;

    // World
    private World world;

    // Input
    private KeyManager keyManager;
    private MouseManager mouseManager;
    private UIManager uiManager;


    Game() {
        keyManager = new KeyManager();
        mouseManager = new MouseManager();
        uiManager = new UIManager();
        mouseManager.setUiManager(uiManager);
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

        world = new World(keyManager, "world1.txt");
        gameState = new GameState(world);
        menuState = new MenuState(world, gameState, uiManager, mouseManager);
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
                gamePanel.repaint();
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
        world.getPlayerOne().xMove = 0;
        world.getPlayerOne().yMove = 0;
        world.getPlayerTwo().xMove = 0;
        world.getPlayerTwo().yMove = 0;

        if(keyManager.W)
            world.getPlayerOne().moveUp();
        if(keyManager.S)
            world.getPlayerOne().moveDown();
        if(keyManager.A)
            world.getPlayerOne().moveLeft();
        if(keyManager.D)
            world.getPlayerOne().moveRight();
        if(keyManager.SPACE)
            world.getPlayerOne().placeBomb();
        if(keyManager.UP)
            world.getPlayerTwo().moveUp();
        if(keyManager.DOWN)
            world.getPlayerTwo().moveDown();
        if(keyManager.LEFT)
            world.getPlayerTwo().moveLeft();
        if(keyManager.RIGHT)
            world.getPlayerTwo().moveRight();
        if(keyManager.CTRL)
            world.getPlayerTwo().placeBomb();
        world.getPlayerOne().move();
        world.getPlayerTwo().move();
        if(State.getCurrentState() != null)
        {
            State.getCurrentState().update();
        }


    }

    private void render()
    {

    }

    // GETTERS and SETTERS

    public KeyManager getKeyManager() {
        return keyManager;
    }

    public MouseManager getMouseManager() {
        return mouseManager;
    }

    public State getGameState() {
        return gameState;
    }
    public State getMenuState() {
        return menuState;
    }

    public int getWidth(){
        return WIDTH;
    }

    public int getHeight(){
        return HEIGHT;
    }

    public World getWorld()
    {
        return world;
    }

    public void setWorld(World world)
    {
        this.world = world;
    }
}
