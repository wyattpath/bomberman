package com.wyatt92.games.model.entities;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class BlastTest
{

    @Test
    void getBounds()
    {
        Blast b = new Blast(3, 4);
        assertEquals(new Rectangle(3,4,64,64), b.getBounds());
    }

    @Test
    void setBounds()
    {
        Blast b = new Blast(3, 4);
        Rectangle newBounds = new Rectangle(7,8,32,32);
        b.setBounds(newBounds);
        assertEquals(newBounds,b.getBounds());
    }
}