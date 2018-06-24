package com.wyatt92.games.model.entities;

import com.wyatt92.games.model.World;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BombBlastManagerTest
{
    private World world;
    private BombBlastManager bombBlastManager;

    BombBlastManagerTest() {
        world = new World("world1.txt");
    }

    @Test
    void getBlasts()
    {
        world.getBombBlastManager().getBlasts();
        assertTrue(world.getBombBlastManager().getBlasts().isEmpty());
    }

    @Test
    void addBlast()
    {
        Blast b = new Blast(world, 3, 54);
        assertFalse(world.getBombBlastManager().getBlasts().contains(b));
        world.getBombBlastManager().addBlast(b);
        assertTrue(world.getBombBlastManager().getBlasts().contains(b));
    }
}