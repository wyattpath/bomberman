package com.wyatt92.games.model.entities;


import com.wyatt92.games.model.Game;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class manages all bomb blasts by adding, updating, removing and drawing blasts.
 */
public class BombBlastManager {
    private Game game;
    private ArrayList<Blast> blasts;

    public BombBlastManager(Game game) {
        this.game = game;
        blasts = new ArrayList<>();
    }

    public void update()
    {
        Iterator<Blast> it = blasts.iterator();
        while(it.hasNext()) {
            Blast b = it.next();
            b.update();

            //checkCollision
            for(Entity e : game.getEntityManager().getEntities()) {
                if(e.equals(b))
                    continue;
                if(e.getCollisionBounds(32,32).intersects(b.bounds)){
                    e.destroy();
                    e.hurt(3);
                }
            }
//            if(b.checkEntityCollisions(32,32)){



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
        b.setWorld(game);
        blasts.add(b);
    }

    public ArrayList<Blast> getBlasts()
    {
        return blasts;
    }


}
