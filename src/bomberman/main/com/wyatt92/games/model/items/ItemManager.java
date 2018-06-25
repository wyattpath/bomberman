package com.wyatt92.games.model.items;


import com.wyatt92.games.model.World;
import com.wyatt92.games.model.entities.Player;
import com.wyatt92.games.model.utils.Sound;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Manages all items.
 */
public class ItemManager
{
    private World world;
    private ArrayList<Item> items;

    /**
     * Constructor
     *
     * @param world world where the items will be managed
     */
    public ItemManager(World world) {
        this.world = world;
        items = new ArrayList<>();
    }


    public void update()
    {
        Iterator<Item> it = items.iterator();
        while(it.hasNext()) {
            Item i = it.next();
            i.update();
            Iterator<Player> itp = world.getEntityManager().getPlayers().iterator();
            while(itp.hasNext()) {
                Player p = itp.next();
                if(p.getCollisionBounds(0f,0f).intersects(i.bounds)){
                    Sound.playSound("item_get.wav");
                    i.setPickedUp(true);
                    i.addEffect(p);
                }
            }
            if(i.isPickedUp()) {
                it.remove();
            }
        }
    }


    public void draw(Graphics g)
    {
        for(Item i : items)
            i.draw(g);
    }

    public void addItem(Item i) {
        i.setWorld(world);
        items.add(i);
    }

    // GETTERS and SETTERS

    public World getWorld()
    {
        return world;
    }

    public void setWorld(World world)
    {
        this.world = world;
    }
}
