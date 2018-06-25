package com.wyatt92.games.model.entities;

import com.wyatt92.games.model.Game;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class PlayerManager
{
    private ArrayList<Player> players;
    private Game game;

    public PlayerManager(Game game)
    {
        this.game = game;
        players = new ArrayList<>();
    }

    public void update()
    {
        Iterator<Player> it = players.iterator();
        while (it.hasNext())
        {
            Player p = it.next();
            p.update();
            if (!p.isActive())
            {
                it.remove();
            }
        }
    }

    public void draw(Graphics g)
    {
        Iterator<Player> it = players.iterator();
        while (it.hasNext())
        {
            Player p = it.next();
            p.draw(g);
        }
    }

    public ArrayList<Player> getPlayers()
    {
        return players;
    }

    public void setPlayers(ArrayList<Player> players)
    {
        this.players = players;
    }

    public void addPlayer(Player p)
    {
        p.setGame(game);
        players.add(p);
//        addEntity(p);
    }

    public Player getPlayer(int id) {
        return players.get(id);
    }

    public int getPlayerCount(){
        return players.size();
    }
}

