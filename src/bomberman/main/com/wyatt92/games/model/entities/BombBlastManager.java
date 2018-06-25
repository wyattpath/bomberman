package com.wyatt92.games.model.entities;


import com.wyatt92.games.model.World;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class manages all bomb blasts by adding, updating, removing and drawing blasts.
 */
public class BombBlastManager {
    private World world;
    private ArrayList<Blast> blasts;

    public BombBlastManager(World world) {
        this.world = world;
        blasts = new ArrayList<>();
    }

    public void update()
    {
        Iterator<Blast> it = blasts.iterator();
        while(it.hasNext()) {
            Blast b = it.next();
            b.update();

            //checkCollision
            for(Entity e : world.getEntityManager().getEntities()) {
                if(e.equals(this))
                    continue;
                if(e.getCollisionBounds(32,32).intersects(b.bounds)){
                    e.destroy();
                    e.hurt(3);
                }
            }

            if(!b.isActive()) {
                it.remove();
            }
        }
    }

    public void draw(Graphics g)
    {
        Iterator<Blast> it = blasts.iterator();
        while(it.hasNext()){
            Blast b = it.next();
            b.draw(g);
        }
    }

    public void addBlast(Blast b) {
        b.setWorld(world);
        blasts.add(b);
    }

    public ArrayList<Blast> getBlasts()
    {
        return blasts;
    }


}
