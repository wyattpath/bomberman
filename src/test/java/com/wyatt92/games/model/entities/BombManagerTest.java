package com.wyatt92.games.model.entities;

import com.wyatt92.games.model.World;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BombManagerTest
{

    @Test
    void addBomb()
    {
        World world = new World("world1.txt");
        assertTrue(world.getBombManager().getBombs().isEmpty());
        Bomb b = new Bomb(world, 3, 4,2);
//        world.getBombManager().addBomb(b);
        assertTrue(world.getBombManager().getBombs().contains(b));

    }
}