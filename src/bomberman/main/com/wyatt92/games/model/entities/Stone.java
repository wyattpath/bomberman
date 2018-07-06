package com.wyatt92.games.model.entities;

import com.wyatt92.games.model.Tile;

import java.awt.*;

/**
 * This class represents a Stone.
 * Spawns sometimes an Item if it gets destroyed.
 */
public class Stone extends StaticEntity {

    public Stone(float x, float y) {
        super(x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);

    }

    @Override
    public void update() {
    }


    public void draw(Graphics g) {
    }

}
