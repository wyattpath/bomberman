package com.wyatt92.games.model.entities;

import com.wyatt92.games.model.Game;

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
    private Game game;
    private ArrayList<Entity> entities;
    private Comparator<Entity> drawSorter = (a, b) -> (a.getY() + a.getHeight() < b.getY() + b.getHeight())? -1 : 1;

    public EntityManager(Game game) {
        this.game = game;

        entities = new ArrayList<>();
    }

    public void update() {
        Iterator<Entity> it = entities.iterator();
        while(it.hasNext()){
            Entity e = it.next();
            e.update();
            if(!e.isActive()){
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

    public Game getGame()
    {
        return game;
    }

    public void setGame(Game game)
    {
        this.game = game;
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
