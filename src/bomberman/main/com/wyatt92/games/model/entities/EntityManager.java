package com.wyatt92.games.model.entities;

import com.wyatt92.games.controller.Handler;

import java.awt.*;
import java.util.ArrayList;

public class EntityManager
{
    private Handler handler;
    private Player player;
    private ArrayList<Entity> entities;

    public EntityManager(Handler handler, Player player) {
        this.handler = handler;
        this.player = player;
        entities = new ArrayList<Entity>();
        addEntity(player);
    }

    public void update() {
        for(int i = 0; i < entities.size(); i++){
            Entity e = entities.get(i);
            e.update();
        }
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
