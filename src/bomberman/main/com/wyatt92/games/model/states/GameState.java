package com.wyatt92.games.model.states;

import com.wyatt92.games.controller.Game;
import com.wyatt92.games.controller.Handler;
import com.wyatt92.games.model.Player;
import com.wyatt92.games.model.World;

import java.awt.*;

public class GameState extends State
{
    private Player player;
    private World world;

    public GameState(Handler handler) {
        super(handler);
        world = new World(handler, "world1.txt");
        handler.setWorld(world);
        player = new Player(handler, 100, 100);
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
