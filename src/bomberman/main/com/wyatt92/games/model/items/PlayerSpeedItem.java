package com.wyatt92.games.model.items;

import com.wyatt92.games.model.Assets;
import com.wyatt92.games.model.entities.Player;


public class PlayerSpeedItem extends Item
{
    /**
     * Constructor
     *
     * @param id id of Item
     */
    public PlayerSpeedItem(int id)
    {
        super(Assets.playerSpeed, "Powerup", id);
    }

    @Override
    public Item createNew(int x, int y)
    {
        Item i = new PlayerSpeedItem(id);
        i.setPosition(x,y);
        return i;
    }

    @Override
    protected void addEffect(Player player)
    {
        player.addPlayerSpeed();
    }
}
