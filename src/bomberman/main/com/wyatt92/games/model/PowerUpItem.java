package com.wyatt92.games.model;

public class PowerUpItem extends Item
{

    public PowerUpItem(int id)
    {
        super(Assets.powerup, "Powerup", id);
    }

    @Override
    protected void addEffect()
    {
        handler.getWorld().getEntityManager().getPlayer().addBombCount();
    }

    @Override
    public Item createNew(int x, int y){
        Item i = new PowerUpItem(id);
        i.setPosition(x,y);
        return i;
    }

}
