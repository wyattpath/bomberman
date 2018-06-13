package com.wyatt92.games.model.tiles;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile
{
    public static Tile[] tiles = new Tile[256];
    public static Tile grassTile = new GrassTile(0);
    public static Tile dirtTile = new DirtTile(1);
    public static Tile rockTile = new RockTile(2);

    public static final int TILEWIDTH = 64;
    public static final int TILEHEIGHT = 64;

    protected BufferedImage texture;
    protected final int id;


    public Tile(BufferedImage texture, int id) {
        this.texture = texture;
        this.id = id;

        tiles[id] = this;
    }

    public void update(){

    }

    public void draw(Graphics g, int x, int y){
        g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
    }

    public void draw(){

    }

    public boolean isSolid() {
        return false;
    }

    // GETTERS and SETTERS

    public int getID() {
        return id;
    }
}
