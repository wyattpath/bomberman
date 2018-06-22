package com.wyatt92.games.model.entities;

import com.wyatt92.games.controller.Handler;
import com.wyatt92.games.model.Animation;
import com.wyatt92.games.model.Assets;
import com.wyatt92.sounds.Sound;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Player extends DynamicEntity
{

//    private boolean isMoving = false;

    public static Player[] player = new Player[4];
    private Animation animDown, animUp, animLeft, animRight;
    private int maxBombs = 1;
    private int bombCount = 1;
    private int bombStrength = 1;

    // AttackTimer
    private long lastAttackTimer, attackCooldown = 400, attackTimer = attackCooldown;
    private long bombCooldown = 2000;

    protected Handler handler;

    public Player(Handler handler, float x, float y)
    {
        super(handler, x, y, DynamicEntity.DEFAULT_CHARACTER_WIDTH, DynamicEntity.DEFAULT_CHARACTER_HEIGHT);
        bounds.x = 16;
        bounds.y = 32;
        bounds.width = 32;
        bounds.height = 32;

        animDown = new Animation(200, Assets.player_down);
        animUp = new Animation(200, Assets.player_up);
        animLeft = new Animation(200, Assets.player_left);
        animRight = new Animation(200, Assets.player_right);
    }

    @Override
    public void update()
    {
        animDown.update();
        animUp.update();
        animRight.update();
        animLeft.update();

        getInput();
        move();
        updateBombCount();

    }



    public void addMaxBombs()
    {
        maxBombs++;
        System.out.println("You can now deploy " + maxBombs + " bombs at the same time.");
    }

    public void addBombStrength()
    {
        bombStrength++;
        System.out.println("Your bombblasts now covers " + bombStrength + " tiles in each direction.");
    }

    private void updateBombCount()
    {
        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();

        if (attackTimer < bombCooldown)
        {
            return;
        }
        if(bombCount < maxBombs)
        {
            bombCount++;
            System.out.println("bombCount = " + bombCount);
            attackTimer = 0;
        }
    }
    protected void placeBomb()
    {

        // attack cooldown
        if (attackTimer < attackCooldown)
            return;

        if (bombCount > 0)
        {
            Sound.playSound("bomb_Set.wav");
            System.out.println("placing Bomb");
            Bomb b = Bomb.createNew(super.getCenterPoint().x, super.getCenterPoint().y, bombStrength);
            handler.getWorld().getBombManager().addBomb(b);
            bombCount--;
            System.out.println("bombCount = " + bombCount);
            attackTimer = 0;
        }

    }

    protected abstract void getInput();

    @Override
    public void draw(Graphics g)
    {
        g.drawImage(getCurrentAnimationFrame(), (int) x, (int) y, width, height, null);
    }

    @Override
    protected void destroy()
    {
        System.out.println("You lose!");
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

        return centerPoint;
    }

    public Handler getHandler()
    {
        return handler;
    }

    public void setHandler(Handler handler)
    {
        this.handler = handler;
    }
}
