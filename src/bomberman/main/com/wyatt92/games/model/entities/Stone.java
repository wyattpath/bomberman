package com.wyatt92.games.model.entities;

import com.wyatt92.games.model.Assets;
import com.wyatt92.games.model.Game;
import com.wyatt92.games.model.items.Item;
import com.wyatt92.games.model.tiles.Tile;
import com.wyatt92.games.view.Animation;

import java.awt.*;
import java.util.Random;

/**
 * This class represents a Stone.
 * Spawns sometimes an Item if it gets destroyed.
 */
public class Stone extends StaticEntity {
    private Animation animStone;

    public Stone(float x, float y) {
        super(x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
        animStone = new Animation(1000, Assets.stone);
    }

    @Override
    public void update() {
        animStone.update();
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(animStone.getCurrentFrame(), (int) x, (int) y, width, height, null);
    }

}
