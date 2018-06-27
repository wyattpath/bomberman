package com.wyatt92.games.model.items;

import com.wyatt92.games.model.Assets;
import com.wyatt92.games.model.entities.Player;


public class PlayerSpeedItem extends Item
{
    public PlayerSpeedItem(int id)
    {
        super(0, 0,  Assets.playerSpeed, id);

    }

    public Item createNew(int x, int y)
    {
        Item item = new PlayerSpeedItem(id);
        item.setPosition(x, y);
        return item;
    }

    @Override
    public void addEffect(Player player)
    {
        player.addPlayerSpeed();
    }
}
