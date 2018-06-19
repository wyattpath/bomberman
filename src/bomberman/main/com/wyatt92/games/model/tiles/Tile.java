package com.wyatt92.games.model.tiles;

import com.wyatt92.games.model.Model;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Tile implements Model
{
    public static Tile[] tiles = new Tile[256];
    public static Tile grassTile = new GrassTile(0);
    public static Tile dirtTile = new DirtTile(1);
    public static Tile wallTile = new WallTile(2);

    public static final int TILEWIDTH = 64;
    public static final int TILEHEIGHT = 64;

    protected BufferedImage texture;
    protected final int id;

    private int x, y;


    public Tile(BufferedImage texture, int id, int x, int y) {

        this.texture = texture;
        this.id = id;
        this.x = x;
        this.y = y;

        tiles[id] = this;
    }

    public Tile(BufferedImage texture, int id)
    {
        this.texture = texture;
        this.id = id;
        tiles[id] = this;
    }

    @Override
    public void update(){

    }

    @Override
    public void draw(Graphics g){
        g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
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
}
