package com.wyatt92.games.model.entities;

import com.wyatt92.games.controller.Handler;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class EntityManager
{
    private Handler handler;
    private Player player;
    private ArrayList<Entity> entities;
    private Comparator<Entity> drawSorter = new Comparator<Entity>() {

        @Override
        public int compare(Entity a, Entity b)
        {
            if(a.getY() + a.getHeight() < b.getY() + b.getHeight())
                return -1;
            return 1;
        }
    };

    public EntityManager(Handler handler, Player player) {
        this.handler = handler;
        this.player = player;
        entities = new ArrayList<>();
        addEntity(player);
    }

    public void update() {
        Iterator<Entity> it = entities.iterator();
        while(it.hasNext()){
            Entity e = it.next();
            e.update();
            if(!e.isActive())
                it.remove();
        }
        entities.sort(drawSorter);
    }
    public void draw(Graphics g){
        for(Entity e : entities){
            e.draw(g);
        }
    }

    public void addEntity(Entity e) {
        entities.add(e);
    }

    // GETTERS and SETTERS

    public Handler getHandler()
    {
        return handler;
    }

    public void setHandler(Handler handler)
    {
        this.handler = handler;
    }

    public Player getPlayer()
    {
        return player;
    }

    public void setPlayer(Player player)
    {
        this.player = player;
    }

    public ArrayList<Entity> getEntities()
    {
        return entities;
    }

    public void setEntities(ArrayList<Entity> entities)
    {
        this.entities = entities;
    }
}
