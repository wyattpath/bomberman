package com.wyatt92.games.model.entities;

import com.wyatt92.games.model.World;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class BlastTest
{
    private static World world;

    BlastTest() {

        world = new World("world1.txt");
        assertNotNull(world);
    }

    @Test
    void constructor(){
        Blast blast = new Blast(world, 3, 4);
        assertEquals(world, blast.getWorld());
        assertEquals(3,blast.getX());
        assertEquals(4, blast.getY());
    }

    @Test
    void destroy()
    {
        Blast blast = new Blast(world,3, 4);
        blast.destroy();
        assertFalse(blast.isActive());
    }

    @Test
    void getPosition()
    {
        Blast blast = new Blast(world,3, 4);
        world.getBombBlastManager().addBlast(blast);
        assertEquals(new Point(3,4), blast.getPosition());
    }

    @Test
    void setPosition()
    {
        Blast blast = new Blast(world,3, 4);
        world.getBombBlastManager().addBlast(blast);
        blast.setPosition(6 , 7);
        assertEquals(new Point(6,7), blast.getPosition());
    }

    @Test
    void getWorld()
    {
        Blast blast = new Blast(world,3, 4);
        assertEquals(world,blast.getWorld());
    }

/*    @Test
    void setWorld()
    {
        World noWorld = new World("nothing");
        Blast blast = new Blast(noWorld,3, 4);
        noWorld.getBombBlastManager().addBlast(blast);
        World world = new World("world1.txt");
        blast.setWorld(world);
        assertEquals(world,blast.getWorld());
    }*/
}