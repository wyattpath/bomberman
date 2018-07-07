package com.wyatt92.games.model.entities;

import java.awt.*;

/**
 * A Player can be controlled by an AI or a Human.
 */
public class Player extends DynamicEntity
{

//    private boolean isMoving = false;


    private int maxBombs = 1;
    private int bombCount = 1;
    private int bombStrength = 1;
    private final int id;

    // AttackTimer
    private long lastTime;
    private final long attackCooldown = 400;
    private long timer = attackCooldown;
    private final long bombCooldown = 2000;



    /**
     * Constructor
     *

     * @param x x-coordinate of player
     * @param y y-coordinate of player
     */
    public Player(float x, float y, int id)
    {
        super(x, y, DynamicEntity.DEFAULT_WIDTH, DynamicEntity.DEFAULT_HEIGHT);
        this.id = id;
        bounds.x = 16;
        bounds.y = 32;
        bounds.width = 32;
        bounds.height = 32;


    }

    @Override
    public void update()
    {
        updateBombCount();
    }

    private void updateBombCount()
    {
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        if (timer < bombCooldown)
        {
            return;
        }
        if(bombCount < maxBombs)
        {
            bombCount++;
            System.out.println(id + " : bombCount = " + bombCount);
            timer = 0;
        }
    }

    public void addEffect(int id)
    {
        switch(id){
            case 0 :
                bombStrength++;
                break;
            case 1 :
                speed+=1.0f;
                break;
            case 2:
                maxBombs++;
                break;
        }
    }


    public void moveUp(){
        yMove = -speed;
    }

    public void moveDown(){
        yMove = speed;
    }

    public void moveLeft(){
        xMove = -speed;
    }

    public void moveRight(){
        xMove = speed;
    }


    public int getId()
    {
        return id;
    }

    public long getTimer()
    {
        return timer;
    }

    public long getAttackCooldown()
    {
        return attackCooldown;
    }

    public int getBombCount()
    {
        return bombCount;
    }

    public int getBombStrength()
    {
        return bombStrength;
    }

    public void decrementBombCount(){
        bombCount--;
    }

    public void incrementBombCouunt() {
        bombCount++;
    }

    public void setTimer(long timer)
    {
        this.timer = timer;
    }



}
