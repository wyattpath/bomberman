package com.wyatt92.games.model.items;

import com.wyatt92.games.model.Assets;
import com.wyatt92.games.model.entities.Player;

/**
 * When picked up by a player grants the player another bomb to place before the bomb CoolDown gets activated.
 */
public class MaxBombsItem extends Item
{

    private int id;
    public MaxBombsItem(int id )
    {
        super(0, 0,  Assets.bombCount, id);
        this.id = id;
    }

    public Item createNew(int x, int y)
    {
        Item item = new MaxBombsItem(id);
        item.setPosition(x, y);
        return item;
    }

    @Override
    public void addEffect(Player player)
    {
        player.addMaxBombs();
    }

}
