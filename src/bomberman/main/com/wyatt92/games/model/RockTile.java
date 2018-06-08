package com.wyatt92.games.model;

import java.awt.image.BufferedImage;

public class RockTile extends Tile
{
    public RockTile(int id)
    {
        super(Assets.stone, id);
    }

    @Override
    public boolean isSolid() {
        return true;
    }
}
