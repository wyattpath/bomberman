package com.wyatt92.games.model;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * A square where the players can walk on if it is not solid.
 * They can not be destroyed.
 */
public class Tile
{
    static Tile[] tiles = new Tile[128];
    private int x, y;

    private final int id;

    public static final int TILEHEIGHT = 64;
    public static final int TILEWIDTH = 64;

    /**
     *
     * @param id
     */
    public Tile(int id) {

        this.id = id;

        tiles[id] = this;



    }


    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }

    // GETTERS and SETTERS
    public int getID() {
        return id;
    }
    public boolean isSolid() {
        return false;
    }

    public Point getPosition(){
        return new Point(x, y);
    }

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }
}
