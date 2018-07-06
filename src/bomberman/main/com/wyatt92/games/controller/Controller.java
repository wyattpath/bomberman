package com.wyatt92.games.controller;

import com.wyatt92.games.model.Model;
import com.wyatt92.games.view.*;


/**
 * Controls and updates the view.
 * Keeps View and Model separated.
 */
public class Controller implements Runnable
{


    private boolean running = false;
    private Thread thread1;
    private View view;
    private Model model;

    private int r;
//    private long gameOverTimer;
//    private long gameOverLastTime;

    // Input
    private GameKeyListener gameKeyListener;


    Controller(View view, Model model)
    {
        this.model = model;
        this.view = view;

        gameKeyListener = new GameKeyListener();
        view.getFrame().addKeyListener(gameKeyListener);
        run();


    }


    // METHODS

    @Override
    public void run()
    {

        // init Timer
        int fps = 60;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while (running)
        {
            // update Time and delta
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick; // number between 0 and 1
            timer += now - lastTime;
            lastTime = now;

            if (delta >= 1)
            {
                update();
                view.repaint();
                ticks++;
                delta--;
            }

            // reset Timer
            if (timer >= 1000000000)
            {
//                System.out.println("Ticks and Frames: " + ticks); // shows FPS

                ticks = 0;
                timer = 0;
            }
        }

        stop();
    }

    private void update()
    {
        if (model.isPlaying())
        {
            gameKeyListener.update();
            model.update();

            if (!model.isGameOver())
            {
                if (model.getPlayersAlive() > 1)
                {

                    if (gameKeyListener.W)
                        model.moveUp(0);
                    if (gameKeyListener.S)
                        model.moveDown(0);
                    if (gameKeyListener.A)
                        model.moveLeft(0);
                    if (gameKeyListener.D)
                        model.moveRight(0);
                    if (gameKeyListener.SPACE)
                        model.placeBomb(0);

                    if (gameKeyListener.UP)
                        model.moveUp(1);
                    if (gameKeyListener.DOWN)
                        model.moveDown(1);
                    if (gameKeyListener.LEFT)
                        model.moveLeft(1);
                    if (gameKeyListener.RIGHT)
                        model.moveRight(1);
                    if (gameKeyListener.CTRL)
                        model.placeBomb(1);

                } else{
                    view.switchPanel();
                }
            }
        }
    }



    synchronized void start()
    {
        if (!running)
        {
            running = true;
            thread1 = new Thread(this);
            thread1.start();
        }
    }

    private synchronized void stop()
    {
        if (running){
            running = false;
            try
            {
                thread1.join();
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }


}
