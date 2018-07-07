package com.wyatt92.games.model.entities;

/**
 * A Bomb can be placed by a player in the world.
 * After a specific time the bomb explodes and places blasts depending on the strength of the bomb in the four directions.
 * If a blast in a line hits a tile or an entity it will destroy that entity or tile and will not place another blast in the line.
 *
 */
public class Bomb extends StaticEntity{
    private long lastTime;
    private float countdown;
    private static float waitTime;
    private final int bombStrength;

    public static final int BOMBWIDTH = 64, BOMBHEIGHT = 64;


    /**
     * Constructor
     *
     * @param x x-coordinate of bomb
     * @param y y-coordinate of bomb
     * @param bombStrength strength of bomb
     */
    public Bomb(float x, float y, int bombStrength)
    {
        super(x, y, BOMBWIDTH, BOMBHEIGHT);
        this.bombStrength = bombStrength;
        lastTime = System.currentTimeMillis();
        waitTime = 2000f;
        countdown = waitTime;



    }


    public void update()
    {


        countdown -= System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        if(countdown < 0 || health <=0) {
            setActive(false);
            countdown = waitTime;
        }
    }


    // GETTERS and SETTERS

    public int getBombStrength()
    {
        return bombStrength;
    }


}
