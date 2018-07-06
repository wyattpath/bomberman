package com.wyatt92.games.model.tiles;

import com.wyatt92.games.view.Assets;

public class WallTile extends Tile
{
    public WallTile(int id)
    {
        super(Assets.wall, id);
    }

    @Override
    public boolean isSolid() {
        return true;
    }
}
