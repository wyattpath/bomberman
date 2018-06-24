package com.wyatt92.games.model.entities;


import com.wyatt92.games.model.utils.Sound;
import com.wyatt92.games.model.utils.Animation;
import com.wyatt92.games.model.Assets;
import com.wyatt92.games.model.World;
import com.wyatt92.games.model.tiles.Tile;

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
     * @param world world where the bomb will be placed
     * @param x x-coordinate of bomb
     * @param y y-coordinate of bomb
     * @param bombStrength strength of bomb
     */
    public Bomb(World world, float x, float y, int bombStrength)
    {
        super(world, x, y, BOMBWIDTH, BOMBHEIGHT);
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

    protected void destroy()
    {
        placeBlast((int)super.x,(int)super.y,0,0);
        placeBlast((int)super.x, (int)super.y, Tile.TILEWIDTH,0); // nextTile on the right
        placeBlast((int)super.x, (int)super.y, -Tile.TILEWIDTH, 0); // nextTile on the left
        placeBlast((int)super.x, (int)super.y,0,-Tile.TILEHEIGHT); //nextTile above
        placeBlast((int)super.x, (int)super.y,0,Tile.TILEHEIGHT); //nextTile below

    }

    private void placeBlast(int x, int y, int xOffset, int yOffset) {
        boolean stop = false;
        String sound =
                (bombStrength>5) ? "boom_L.wav" :
                (bombStrength> 3)? "boom_M.wav" : "boom_S.wav";
        Sound.playSound(sound);

        for(int i = 0; i < bombStrength;i++)
        {
            if(stop || collisionWithTile(x + xOffset + i * xOffset, y + yOffset + i *yOffset))
                return;

            world.getBombBlastManager().addBlast(new Blast(world , x + xOffset + i*xOffset, y + yOffset + i *yOffset));

            Rectangle tempBounds = new Rectangle();
            tempBounds.x = x + xOffset + i * xOffset;
            tempBounds.y = y + yOffset + i *yOffset;
            tempBounds.setSize(BOMBWIDTH, BOMBHEIGHT);
            for (Entity e : world.getEntityManager().getEntities())
            {
                if (e.equals(this))
                    continue;
                if (e.getCollisionBounds(32, 32).intersects(tempBounds))
                {
                    e.destroy();
                    e.hurt(3);
                    stop = true;
                }
            }

        }
    }

    protected boolean collisionWithTile(int x, int y) {
        return world.getTile(x/Tile.TILEWIDTH,y/Tile.TILEHEIGHT).isSolid();
    }

    // GETTERS and SETTERS

    public World getWorld()
    {
        return super.world;
    }

    public void setWorld(World world)
    {
        super.world = world;
    }

    public int getBombStrength()
    {
        return bombStrength;
    }
}
