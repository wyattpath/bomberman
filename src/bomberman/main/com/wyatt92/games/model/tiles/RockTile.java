package com.wyatt92.games.model.tiles;

import com.wyatt92.games.model.Assets;

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
