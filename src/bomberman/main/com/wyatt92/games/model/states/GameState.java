package com.wyatt92.games.model.states;

import com.wyatt92.games.controller.Game;
import com.wyatt92.games.model.Player;
import com.wyatt92.games.model.World;

import java.awt.*;

public class GameState extends State
{
    private Player player;
    private World world;

    public GameState(Game game) {
        player = new Player(game, 100, 100);
        world = new World("world1.txt");
    }

    @Override
    public void update()
    {
        world.update();
        player.update();
    }

    @Override
    public void draw(Graphics g)
    {
        world.draw(g);
        player.draw(g);
    }
}
