package com.wyatt92.games.model.items;

import com.wyatt92.games.model.Assets;

public class BombCountItem extends Item
{

    public BombCountItem(int id)
    {
        super(Assets.bombCount, "Powerup", id);
    }

    @Override
    protected void addEffect()
    {
        handler.getWorld().getEntityManager().getPlayer().addBombCount();
    }

    @Override
    public Item createNew(int x, int y){
        Item i = new BombCountItem(id);
        i.setPosition(x,y);
        return i;
    }

}
