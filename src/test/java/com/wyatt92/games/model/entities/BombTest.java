package com.wyatt92.games.model.entities;

import com.wyatt92.games.model.Game;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BombTest
{
    Game game;
    Bomb b;

    BombTest() {
        game = new Game("world1.txt");
        b = new Bomb(game, 64, 64, 2);
    }

    @Test
    void testConstructor(){
        assertEquals(64,b.getX());
        assertEquals(64,b.getY());
        assertEquals(2, b.getBombStrength());
        assertEquals(game, b.getWorld());
    }
/*
    @Test
    void destroy()
    {
        world.getBombManager().addBomb(b);
        assertEquals(1,world.getBombManager().getBombs().size());
        b.setActive(false);
        b.destroy();
        world.getBombBlastManager().update();
//        assertEquals(0,world.getBombManager().getBombs().size());
        assertEquals(4, world.getBombBlastManager().getBlasts().size());
    }*/


    @Test
    void getWorld()
    {
        assertEquals(game, b.getWorld());
    }

    @Test
    void setWorld()
    {
        Game newGame = new Game("world2.txt");
        b.setWorld(newGame);
        assertEquals(newGame,b.getWorld());
    }
}