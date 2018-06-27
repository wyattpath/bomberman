package com.wyatt92.games.model.items;

import com.wyatt92.games.model.Assets;
import com.wyatt92.games.model.entities.Player;

/**
 * Grants a player more bombStrength when picked up.
 * If the player places a bomb the bomb covers more tiles with blasts when it explodes.
 */
public class BombStrengthItem extends Item
{
    private int id;

    public BombStrengthItem(int id)
    {
        super(0, 0,  Assets.bombStrength, id);
        this.id = id;
    }

    @Override
    public Item createNew(int x, int y)
    {
        Item item = new BombStrengthItem(id);
        item.setPosition(x, y);

        return item;
    }

    @Override
    public void addEffect(Player player)
    {
        player.addBombStrength();
    }

}
