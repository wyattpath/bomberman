package com.wyatt92.games.model.ui;

import com.wyatt92.games.controller.MouseManager;
import com.wyatt92.games.model.Model;

import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class UIObject implements Model
{
    protected float x, y;
    protected int width, height;
    protected Rectangle bounds;
    protected boolean hovering = false;

    public UIObject(float x, float y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        bounds = new Rectangle((int) x, (int) y, width, height);
    }

    @Override
    public abstract void update();

    @Override
    public abstract void draw(Graphics g);

    public abstract void onClick();

    public void onMouseMove(MouseEvent e) {
        hovering = (bounds.contains(e.getX(), e.getY()))? true : false;
    }

    public void onMouseRelease(MouseEvent e) {
        if( hovering)
            onClick();
    }

    // GETTERS and SETTERS

    public float getX()
    {
        return x;
    }

    public void setX(float x)
    {
        this.x = x;
    }

    public float getY()
    {
        return y;
    }

    public void setY(float y)
    {
        this.y = y;
    }

    public int getWidth()
    {
        return width;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public int getHeight()
    {
        return height;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public boolean isHovering()
    {
        return hovering;
    }

    public void setHovering(boolean hovering)
    {
        this.hovering = hovering;
    }
}
