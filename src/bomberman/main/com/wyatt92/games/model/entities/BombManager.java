package com.wyatt92.games.model.entities;

import com.wyatt92.games.model.World;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class manages all bombs by adding, updating, removing and drawing bombs.
 */
public class BombManager
{
    private World world;
    private ArrayList<Bomb> bombs;

    public BombManager(World world) {
        this.world = world;
        bombs = new ArrayList<>();
    }

    public void update()
    {
        Iterator<Bomb> it = bombs.iterator();
        while(it.hasNext()) {
            Bomb b = it.next();
            b.update();
            if(!b.isActive()) {
                it.remove();
            }
        }
    }

    public void draw(Graphics g)
    {
        for(Bomb i : bombs)
            i.draw(g);
    }

    public void addBomb(Bomb b) {
        b.setWorld(world);
        bombs.add(b);
    }
}
