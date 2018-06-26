package com.wyatt92.games.controller;

import com.wyatt92.games.model.Assets;
import com.wyatt92.games.model.Model;
import com.wyatt92.games.model.Sound;
import com.wyatt92.games.model.entities.Player;
import com.wyatt92.games.view.*;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;


/**
 * Controls and updates the view.
 * Keeps View and Model separated.
 */
public class Controller implements Runnable
{

    private static GamePanel gamePanel;
    private static MenuPanel menuPanel;
    private static GameOverPanel gameOverPanel;
    private final MenuOptionsPanel menuOptionsPanel;
    private boolean running = false;
    private boolean paused = false;
    private boolean playing = false;
    private boolean gameOver = false;
    private Thread thread1;
    private View view;
    private Model model;
    private JPanel currentPanel;

    private int r;


    // Input
    private GameKeyListener gameKeyListener;
    private GameMouseListener gameMouseListener;


    Controller(View view, Model model)
    {
        this.model = model;
        this.view = view;
        Assets.init();

        gameKeyListener = new GameKeyListener();
        gameMouseListener = new GameMouseListener();

        gamePanel = new GamePanel(model);

        menuPanel = new MenuPanel();
        gameOverPanel = new GameOverPanel(model);
        menuOptionsPanel = new MenuOptionsPanel();

        switchPanel(menuPanel);

        addActionListener();
        view.makeVisible();

        r = new Random().nextInt(Assets.menu_bgMusic.length);
        Assets.menu_bgMusic[r].start();
        Assets.menu_bgMusic[r].loop(Clip.LOOP_CONTINUOUSLY);

        run();
    }

    private void addActionListener()
    {
        menuPanel.getStartButton().addActionListener(e -> setUpActionButton());
        menuPanel.getStartButton().addMouseListener(addEnterSound());
        menuPanel.getOptionsButton().addActionListener(e -> switchPanel(menuOptionsPanel));
        menuPanel.getQuitButton().addActionListener(e -> System.exit(0));
        menuPanel.getQuitButton().addMouseListener(addEnterSound());

        gameOverPanel.getStartButton().addActionListener(e -> setUpActionButton());
        gameOverPanel.getStartButton().addMouseListener(addEnterSound());
        gameOverPanel.getQuitButton().addActionListener(e -> System.exit(0));
        gameOverPanel.getQuitButton().addMouseListener(addEnterSound());

        view.getFrame().addKeyListener(gameKeyListener);
        view.getFrame().addMouseListener(gameMouseListener);
        view.getFrame().addMouseMotionListener(gameMouseListener);
    }

    // METHODS

    public void setUpActionButton()
    {
        model.loadWorld("world1.txt");
        model.resetWorld();
        switchPanel(gamePanel);
        playMusic();
        gameOver = false;
        playing = true;
        view.getFrame().addKeyListener(gameKeyListener);
    }

    public MouseAdapter addEnterSound()
    {
        return new MouseAdapter()
        {
            @Override
            public void mouseEntered(MouseEvent e)
            {
                super.mouseEntered(e);
                Sound.playSound("cursor_move.wav");
            }
        };
    }

    private void playMusic()
    {
        if (!gameOver && playing)
        {
            Assets.game_bgMusic[r].stop();
            r = new Random().nextInt(Assets.gameOver_bgMusic.length);
            Assets.gameOver_bgMusic[r].setFramePosition(0);
            Assets.gameOver_bgMusic[r].start();
        } else if (!gameOver && !playing)
        {
            Assets.menu_bgMusic[r].stop();
            r = new Random().nextInt(Assets.game_bgMusic.length);
            Assets.game_bgMusic[r].setFramePosition(0);
            Assets.game_bgMusic[r].start();
            Assets.game_bgMusic[r].loop(Clip.LOOP_CONTINUOUSLY);
        } else
        {
            Assets.gameOver_bgMusic[r].stop();
            r = new Random().nextInt(Assets.game_bgMusic.length);
            Assets.game_bgMusic[r].setFramePosition(0);
            Assets.game_bgMusic[r].start();
            Assets.game_bgMusic[r].loop(Clip.LOOP_CONTINUOUSLY);
        }

    }

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

    synchronized void start()
    {
        if (running) return;
        running = true;
        thread1 = new Thread(this);
        thread1.start();

    }

    private synchronized void stop()
    {
        if (!running) return;
        running = false;
        try
        {
            thread1.join();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    private void update()
    {
//        menuPanel.getAnimLogo().update();
//        menuPanel.getAnimBG().update();
        if (playing)
        {
            if (!gameOver)
            {



                gameKeyListener.update();
                model.update();


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




                if (model.getPlayerAlive() < 2)
                {
                    playMusic();
                    gameOver = true;
                    playing = false;
                    int winner = 0;
                    for (Player p : model.getPlayers())
                    {
                        if (p.isActive())
                        {
                            winner = p.getId();
                        }
                    }
                    model.setWinner(winner);


                    switchPanel(gameOverPanel);


                }

            }
        }
    }


    private void switchPanel(JPanel panel)
    {
        view.setPanel(panel);
        currentPanel = panel;
    }
}
