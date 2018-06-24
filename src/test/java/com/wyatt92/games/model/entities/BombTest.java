package com.wyatt92.games.model.entities;

import com.wyatt92.games.model.World;
import com.wyatt92.games.model.tiles.Tile;
import com.wyatt92.games.model.tiles.WallTile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BombTest
{
    World world;
    Bomb b;

    BombTest() {
        world = new World("world1.txt");
        b = new Bomb(world, 64, 64, 2);
    }

    @Test
    void testConstructor(){
        assertEquals(64,b.getX());
        assertEquals(64,b.getY());
        assertEquals(2, b.getBombStrength());
        assertEquals(world, b.getWorld());
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
    void collisionWithTile()
    {
        assertFalse(b.collisionWithTile(64,64));
        b.setX(0);
        b.setY(0);
        assertTrue(b.collisionWithTile(0,0));
    }

    @Test
    void getWorld()
    {
        assertEquals(world, b.getWorld());
    }

    @Test
    void setWorld()
    {
        World newWorld = new World("world2.txt");
        b.setWorld(newWorld);
        assertEquals(newWorld,b.getWorld());
    }
}