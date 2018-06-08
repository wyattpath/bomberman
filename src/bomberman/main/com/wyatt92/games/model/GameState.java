package com.wyatt92.games.model;

import com.wyatt92.games.controller.Game;

import java.awt.*;

public class GameState extends State
{
    private Player player;

    public GameState(Game game) {
        player = new Player(game,100, 100);
    }

    @Override
    public void update()
    {
        player.update();
    }

    @Override
    public void draw(Graphics g)
    {
        player.draw(g);
    }

    
}
