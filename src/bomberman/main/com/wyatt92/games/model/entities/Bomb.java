package com.wyatt92.games.model.entities;


import com.wyatt92.games.model.Model;
import com.wyatt92.games.view.Animation;
import com.wyatt92.games.model.Assets;
import com.wyatt92.games.model.Game;

import java.awt.*;


public class Bomb extends StaticEntity{
    private long lastTime;
    private float countdown;
    private static float waitTime;
    private static Animation animBomb;

//    protected static World world;
    protected Rectangle bounds;
    protected int bombStrength;

    public static final int BOMBWIDTH = 64, BOMBHEIGHT = 64;


    /**
     * A Bomb can be placed by a player in the world.
     * After a specific time the bomb explodes and places blasts depending on the strength of the bomb in the four directions.
     * If a blast in a line hits a tile or an entity it will destroy that entity or tile and will not place another blast in the line.
     *
     * @param model world where the bomb will be placed
     * @param x x-coordinate of bomb
     * @param y y-coordinate of bomb
     * @param bombStrength strength of bomb
     */
    public Bomb(Model model, float x, float y, int bombStrength)
    {
        super(model, x, y, BOMBWIDTH, BOMBHEIGHT);
        this.bombStrength = bombStrength;
        bounds = new Rectangle((int) x,(int) y, BOMBWIDTH, BOMBHEIGHT);
        lastTime = System.currentTimeMillis();
        waitTime = 2000f;
        countdown = waitTime;

        animBomb = new Animation(500, Assets.bomb);

    }


    public void update()
    {
        //Animation
        animBomb.update();

        countdown -= System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        if(countdown < 0) {
            setActive(false);
            countdown = waitTime;
        }
    }

    public void draw(Graphics g)
    {
        g.drawImage(animBomb.getCurrentFrame(), (int)super.x - BOMBWIDTH/2, (int)super.y-BOMBHEIGHT/2, BOMBWIDTH, BOMBHEIGHT, null);
    }

    public void destroy()
    {


    }

    // GETTERS and SETTERS
    public void setWorld(Model model){
        this.model = model;
    }

    public int getBombStrength()
    {
        return bombStrength;
    }
}
