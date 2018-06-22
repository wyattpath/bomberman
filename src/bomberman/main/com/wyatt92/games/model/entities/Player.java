package com.wyatt92.games.model.entities;

import com.wyatt92.games.controller.Handler;
import com.wyatt92.games.model.Animation;
import com.wyatt92.games.model.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends DynamicEntity
{

//    private boolean isMoving = false;

    private Animation animDown, animUp, animLeft, animRight;
    private int bombCount = 1;
    private int bombStrength = 1;

    // AttackTimer
    private long lastAttackTimer, attackCooldown = 800, attackTimer = attackCooldown;
    public Player(Handler handler, float x, float y) {
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
        placeBomb();

    }

    public void addBombCount(){
        bombCount++;
        System.out.println("You can now deploy " + bombCount + " bombs at the same time.");
    }

    public void addBombStrength(){
        bombStrength++;
        System.out.println("Your bombblasts now covers " + bombStrength + " tiles in each direction.");
    }



    private void placeBomb()
    {
        // attack cooldown
        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();
        if(attackTimer < attackCooldown)
            return;

        Rectangle cb = getCollisionBounds(0,0); // collision bounds
        Rectangle ar = new Rectangle(); // attack rectangle
        int arSize = 20;
        ar.width = arSize;
        ar.height = arSize;

        if(handler.getKeyManager().aUP) {
            ar.x = cb.x + cb.width / 2 - arSize / 2;
            ar.y = cb.y - arSize;
            handler.getWorld().getBombManager().addBomb(Bomb.createNew(ar.x, ar.y, bombStrength));
        }else if(handler.getKeyManager().aDOWN) {
            ar.x = cb.x + cb.width / 2 - arSize / 2;
            ar.y = cb.y + cb.height;
            handler.getWorld().getBombManager().addBomb(Bomb.createNew(ar.x, ar.y, bombStrength));
        }
        else if(handler.getKeyManager().aLEFT) {
            ar.x = cb.x - arSize;
            ar.y = cb.y + cb.height / 2- arSize / 2;
            handler.getWorld().getBombManager().addBomb(Bomb.createNew(ar.x, ar.y, bombStrength));

        }
        else if(handler.getKeyManager().aRIGHT) {
            ar.x = cb.x + cb.width;
            ar.y = cb.y + cb.height / 2 - arSize / 2;
            handler.getWorld().getBombManager().addBomb(Bomb.createNew(ar.x, ar.y, bombStrength));

        } else if(handler.getKeyManager().SPACE) {
            System.out.println(super.getCenterPoint());
                handler.getWorld().getBombManager().addBomb(Bomb.createNew(super.getCenterPoint().x, super.getCenterPoint().y, bombStrength));
        }else {
            return;
        }

        attackTimer = 0;

//        for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
//            if(e.equals(this))
//                continue;
//            if(e.getCollisionBounds(0,0).intersects(ar)){
//                e.hurt(1);
//                return;
//            }
//        }

    }

    public Point getCenterPoint()
    {

        return centerPoint;
    }


    private void getInput(){
        xMove = 0;
        yMove = 0;

        if(handler.getKeyManager().UP)
            yMove = -speed;
        if(handler.getKeyManager().DOWN)
            yMove = speed;
        if(handler.getKeyManager().LEFT)
            xMove = -speed;
        if(handler.getKeyManager().RIGHT)
            xMove = speed;
        if(handler.getKeyManager().SPACE){
            placeBomb();
        }


    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (int) x ,(int) y, width, height,null);
    }

    @Override
    protected void destroy()
    {
        System.out.println("You lose!");
    }

    private BufferedImage getCurrentAnimationFrame(){
        if(xMove < 0) {
            return animLeft.getCurrentFrame();
        } else if(xMove > 0) {
            return animRight.getCurrentFrame();
        } else if(yMove < 0) {
            return animUp.getCurrentFrame();
        } else if(yMove > 0){
            return animDown.getCurrentFrame();
        } else {
            return animDown.getCurrentFrame();
        }
    }
}
