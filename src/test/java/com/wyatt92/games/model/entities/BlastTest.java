package com.wyatt92.games.model.entities;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class BlastTest
{
    Blast b;

    BlastTest(){
        b = new Blast(16,32);
    }

    @Test
    void hurt()
    {
        b.hurt(3);
        assertEquals(0,b.getHealth());
    }

    @Test
    void getCollisionBounds()
    {
        assertEquals(new Rectangle(32,64,64,64), b.getCollisionBounds(0,0));
    }

    @Test
    void getX()
    {
        assertEquals(16, b.getX());
    }

    @Test
    void setX()
    {
        b.setX(4);
        assertEquals(4,b.getX());
    }

    @Test
    void getY()
    {
        assertEquals(32, b.getY());
    }

    @Test
    void setY()
    {
        b.setY(3);
        assertEquals(3,b.getY());
    }

    @Test
    void getWidth()
    {
        assertEquals(64, b.getWidth());
    }

    @Test
    void setWidth()
    {
        b.setWidth(3);
        assertEquals(3,b.getWidth());
    }

    @Test
    void getHeight()
    {
        assertEquals(64, b.getHeight());
    }

    @Test
    void setHeight()
    {
        b.setHeight(2);
        assertEquals(2, b.getHeight());
    }

    @Test
    void getDefaultHealth()
    {
        assertEquals(3, b.getDefaultHealth());
    }

    @Test
    void isActive()
    {
        assertTrue(b.isActive());
    }

    @Test
    void setActive()
    {
        b.setActive(false);
        assertFalse(b.isActive());
    }

    @Test
    void getCenterPoint()
    {
        assertEquals(new Point(32,96), b.getCenterPoint());
    }

    @Test
    void setCenterPoint()
    {
        b.setCenterPoint(new Point(3,4));
        assertEquals(new Point(32,96), b.getCenterPoint());
        b.setCenterPoint(new Point(32,96));
        assertEquals(new Point(32,96), b.getCenterPoint());
    }

    @Test
    void getBounds()
    {
        assertEquals(new Rectangle(16,32,64,64), b.getBounds());
    }

    @Test
    void setBounds()
    {
        b.setBounds(new Rectangle(3,4,16,16));
        assertEquals(new Rectangle(3,4,16,16), b.getBounds());
    }

    @Test
    void getxOffset()
    {
        assertEquals(32,b.getxOffset());
    }

    @Test
    void setxOffset()
    {
        b.setxOffset(3);
        assertEquals(3,b.getxOffset());
    }

    @Test
    void getyOffset()
    {
        assertEquals(32, b.getyOffset());
    }

    @Test
    void setyOffset()
    {
        b.setyOffset(4);
        assertEquals(4, b.getyOffset());
    }

    @Test
    void getHealth()
    {
        assertEquals(3, b.getHealth());
    }

    @Test
    void setHealth()
    {
        b.setHealth(4);
        assertEquals(4,b.getHealth());
    }
}