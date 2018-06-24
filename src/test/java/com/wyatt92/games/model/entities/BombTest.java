package com.wyatt92.games.model.entities;

import com.wyatt92.games.model.World;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BombTest
{
    World world;
    Bomb b;

    BombTest() {
        world = new World("world1.txt");
        b = new Bomb(world, 3, 4, 2);
    }

    @Test
    void testConstructor(){
        assertEquals(3,b.getX());
        assertEquals(4,b.getY());
        assertEquals(2, b.getBombStrength());
        assertEquals(world, b.getWorld());
    }

    @Test
    void destroy()
    {
        b.destroy();
        assertEquals(4, world.getBombBlastManager().getBlasts().size());
    }


    @Test
    void setPosition()
    {
    }

    @Test
    void collisionWithTile()
    {
    }

    @Test
    void getWorld()
    {
    }

    @Test
    void setWorld()
    {
    }
}