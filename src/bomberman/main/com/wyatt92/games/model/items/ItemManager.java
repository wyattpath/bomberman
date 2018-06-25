package com.wyatt92.games.model.items;


import com.wyatt92.games.model.Game;
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
    private Game game;

    public ArrayList<Item> getItems()
    {
        return items;
    }

    public void setItems(ArrayList<Item> items)
    {
        this.items = items;
    }

    private ArrayList<Item> items;

    /**
     * Constructor
     *
     * @param game world where the items will be managed
     */
    public ItemManager(Game game) {
        this.game = game;
        items = new ArrayList<>();
    }


    public void update()
    {
        Iterator<Item> it = items.iterator();
        while(it.hasNext()) {
            Item i = it.next();
            i.update();
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
        i.setGame(game);
        items.add(i);
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
}
