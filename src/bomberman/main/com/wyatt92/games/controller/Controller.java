package com.wyatt92.games.controller;

import com.wyatt92.games.model.Assets;
import com.wyatt92.games.model.Game;
import com.wyatt92.games.model.entities.Player;
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
public class Controller implements Runnable{

    private boolean running = false;
    private Thread thread1;

    private View view;

    // States
    private State gameState;
    private State menuState;
    private State gameOverState;

    // World
    private Game game;

    // Input
    private GameKeyListener gameKeyListener;
    private GameMouseListener gameMouseListener;

    private UIManager uiManager;

    Controller(View view) {
        this.view = view;
        gameKeyListener = new GameKeyListener();
        gameMouseListener = new GameMouseListener();
        uiManager = new UIManager();
        gameMouseListener.setUiManager(uiManager);
        init();
        run();
    }

    // METHODS

    public void init() {
        view.getGamePanel().addKeyListener(gameKeyListener);
        view.getGamePanel().addMouseListener(gameMouseListener);
        view.getGamePanel().addMouseMotionListener(gameMouseListener);
        Assets.init();
//        gamePanel.setDoubleBuffered(true);

        game = new Game("world2.txt");
        gameState = new GameState(game);
        State.setGameState(gameState);
        menuState = new MenuState(game, uiManager);

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
            if(game.getPlayerManager().getPlayers().size()>0){
                for(int i = 0; i < game.getPlayerManager().getPlayers().size(); i++){
                    game.getPlayerManager().getPlayer(i).setxMove(0);
                    game.getPlayerManager().getPlayer(i).setyMove(0);
                }
            }

            if (game.getPlayerManager().getPlayers().size()>=1){

                if (gameKeyListener.W)
                    game.getPlayerManager().getPlayer(0).moveUp();
                if (gameKeyListener.S)
                    game.getPlayerManager().getPlayer(0).moveDown();
                if (gameKeyListener.A)
                    game.getPlayerManager().getPlayer(0).moveLeft();
                if (gameKeyListener.D)
                    game.getPlayerManager().getPlayer(0).moveRight();
                if (gameKeyListener.SPACE)
                    game.getPlayerManager().getPlayer(0).placeBomb();
            }
            if (game.getPlayerManager().getPlayers().size()> 1)
            {
                if (gameKeyListener.UP)
                    game.getPlayerManager().getPlayer(1).moveUp();
                if (gameKeyListener.DOWN)
                    game.getPlayerManager().getPlayer(1).moveDown();
                if (gameKeyListener.LEFT)
                    game.getPlayerManager().getPlayer(1).moveLeft();
                if (gameKeyListener.RIGHT)
                    game.getPlayerManager().getPlayer(1).moveRight();
                if (gameKeyListener.CTRL)
                    game.getPlayerManager().getPlayer(1).placeBomb();
            }

            if(State.getCurrentState() != null)
            {
                State.getCurrentState().update();

                if(!game.isGameOver())
                {
                    if(game.getPlayerManager().getPlayerCount()<2){
                        game.setGameOver(true);
                        int winner = 0;
                        for(Player p : game.getPlayerManager().getPlayers()){
                            if(p.isActive()) {
                                winner = p.getId();
                            }
                        }
                        gameOverState = new GameOverState(game, uiManager);
                        gameOverState.setWinner(winner);
                        State.setCurrentState(gameOverState);

                    }
                }
            }
        }


}
