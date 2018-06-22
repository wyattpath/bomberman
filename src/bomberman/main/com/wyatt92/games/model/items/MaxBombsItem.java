package com.wyatt92.games.model.items;

import com.wyatt92.games.model.Assets;
import com.wyatt92.games.model.entities.Player;

public class MaxBombsItem extends Item
{

    public MaxBombsItem(int id)
    {
        super(Assets.bombCount, "Powerup", id);
    }

    @Override
    protected void addEffect(Player player)
    {
        player.addMaxBombs();
    }

    @Override
    public Item createNew(int x, int y){
        Item i = new MaxBombsItem(id);
        i.setPosition(x,y);
        return i;
    }

}
