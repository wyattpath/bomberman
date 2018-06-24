package com.wyatt92.games.model.entities;

import com.wyatt92.games.model.World;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

/**
 * This class manages all entities.
 * Adds entities to a list.
 * Updates all entities.
 * Removes an Entity if it is not set active.
 * Calls the draw method of all entities.
 * Determines the order of when the entities are drawn.
 */
public class EntityManager
{
    private World world;
    private ArrayList<Player> players;
    private ArrayList<Entity> entities;
    private Comparator<Entity> drawSorter = (a, b) -> (a.getY() + a.getHeight() < b.getY() + b.getHeight())? -1 : 1;

    public EntityManager(World world) {
        this.world = world;

        entities = new ArrayList<>();
        players = new ArrayList<>();
    }

    public void update() {
        Iterator<Entity> it = entities.iterator();
        while(it.hasNext()){
            Entity e = it.next();
            e.update();
            if(!e.isActive()){
                if(getPlayers().contains(e))
                {
                    players.remove(e);
                    world.setPlayerCount(world.getPlayerCount()-1);
                }
                it.remove();
            }
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

    public World getWorld()
    {
        return world;
    }

    public void setWorld(World world)
    {
        this.world = world;
    }

    public ArrayList<Player> getPlayers()
    {
        return players;
    }

    public int getPlayerCount() {
        return players.size();
    }
    public Player getPlayer(int id)
    {
        return players.get(id);
    }

    public void addPlayer(Player p)
    {
        p.setWorld(world);
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
