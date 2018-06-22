package com.wyatt92.games.model.items;

import com.wyatt92.games.model.Assets;

public class PowerUpItem extends Item
{

    public PowerUpItem(int id)
    {
        super(Assets.powerup, "Powerup", id);
    }

    @Override
    protected void addEffect()
    {
        handler.getWorld().getEntityManager().getPlayer().addBombStrength();
    }

    @Override
    public Item createNew(int x, int y){
        Item i = new PowerUpItem(id);
        i.setPosition(x,y);
        return i;
    }

}
