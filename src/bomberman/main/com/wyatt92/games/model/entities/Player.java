package com.wyatt92.games.model.entities;

import com.wyatt92.games.model.Animation;
import com.wyatt92.games.model.Assets;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * A Player can be controlled by an AI or a Human.
 */
public class Player extends DynamicEntity
{

//    private boolean isMoving = false;

    private Animation animDown, animUp, animLeft, animRight;
    private int maxBombs = 1;
    private int bombCount = 1;
    private int bombStrength = 1;
    private int id;

    // AttackTimer
    private long lastTime, attackCooldown = 400, timer = attackCooldown;
    private long bombCooldown = 2000;



    /**
     * Constructor
     *

     * @param x x-coordinate of player
     * @param y y-coordinate of player
     */
    public Player(float x, float y, int id)
    {
        super(x, y, DynamicEntity.DEFAULT_CHARACTER_WIDTH, DynamicEntity.DEFAULT_CHARACTER_HEIGHT);
        this.id = id;
        bounds.x = 16;
        bounds.y = 32;
        bounds.width = 32;
        bounds.height = 32;

        animDown = new Animation(200, Assets.player_down);
        animUp = new Animation(200, Assets.player_up);
        animLeft = new Animation(200, Assets.player_left);
        animRight = new Animation(200, Assets.player_right);
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


    public void addMaxBombs()
    {
        maxBombs++;
        System.out.println(id + ": maxBombs " + maxBombs);
    }

    public void addBombStrength()
    {
        bombStrength++;
        System.out.println(id + ": bombStrength " + bombStrength);
    }

    public void addPlayerSpeed() {
        speed+=1.0f;
        System.out.println(id + ": playerSpeed " + speed);
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

    @Override
    public void update()
    {

        animDown.update();
        animUp.update();
        animRight.update();
        animLeft.update();

        updateBombCount();

    }

    @Override
    public void draw(Graphics g)
    {
        g.drawImage(getCurrentAnimationFrame(), (int) x, (int) y, width, height, null);
    }

    private BufferedImage getCurrentAnimationFrame()
    {
        if (xMove < 0)
        {
            return animLeft.getCurrentFrame();
        } else if (xMove > 0)
        {
            return animRight.getCurrentFrame();
        } else if (yMove < 0)
        {
            return animUp.getCurrentFrame();
        } else if (yMove > 0)
        {
            return animDown.getCurrentFrame();
        } else
        {
            return animDown.getCurrentFrame();
        }
    }

    public Point getCenterPoint()
    {

        return new Point((int)(x+xOffset)/width * width + width/2,(int) (y+yOffset)/height * height + height/2);
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


    public void addEffect(int id)
    {
        switch(id){
            case 0 :
                addBombStrength();
                break;
            case 1 :
                addPlayerSpeed();
                break;
            case 2:
                addMaxBombs();
                break;
        }
    }
}
