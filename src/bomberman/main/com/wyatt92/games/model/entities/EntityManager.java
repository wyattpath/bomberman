package com.wyatt92.games.model.entities;

import com.wyatt92.games.controller.Handler;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class EntityManager
{
    private Handler handler;
    private ArrayList<Player> players;
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

    public EntityManager(Handler handler) {
        this.handler = handler;

        entities = new ArrayList<>();
        players = new ArrayList<>();
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
//        Iterator<Entity> it = entities.iterator();
//        while(it.hasNext()){
//            Entity e = it.next();
//            e.draw(g);
//        }
        for(int i = 0; i < entities.size(); i++)
            entities.get(i).draw(g);
//        entities.sort(drawSorter);
    }

    public void addEntity(Entity e) {
        entities.add(e);
    }
    public void removeEntity(Entity e) {
        entities.remove(e);
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

    public ArrayList<Player> getPlayers()
    {
        return players;
    }

    public Player getPlayer(int id)
    {
        return players.get(id);
    }

    public void addPlayer(Player p)
    {
        p.setHandler(handler);
        players.add(p);
        addEntity(p);
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
