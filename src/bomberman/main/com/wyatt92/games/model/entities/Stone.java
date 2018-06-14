package com.wyatt92.games.model.entities;

import com.wyatt92.games.controller.Handler;
import com.wyatt92.games.model.Assets;
import com.wyatt92.games.model.tiles.Tile;

import java.awt.*;

public class Stone extends StaticEntity {
    public Stone(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(Assets.stone, (int) x, (int) y, width, height, null);
    }

    @Override
    protected void destroy()
    {

    }
}
