package com.wyatt92.games.model.entities;

import com.wyatt92.games.controller.Handler;
import com.wyatt92.games.model.Assets;

import java.awt.*;

import static com.wyatt92.games.model.entities.Bomb.bomb;

public class Player extends DynamicEntity
{

//    private boolean isMoving = false;

    private int bombCount = 1;

    // AttackTimer
    private long lastAttackTimer, attackCooldown = 800, attackTimer = attackCooldown;
    public Player(Handler handler, float x, float y) {
        super(handler, x, y, DynamicEntity.DEFAULT_CHARACTER_WIDTH, DynamicEntity.DEFAULT_CHARACTER_HEIGHT);

        bounds.x = 16;
        bounds.y = 32;
        bounds.width = 32;
        bounds.height = 32;
    }

    @Override
    public void update()
    {
        getInput();
        move();
        placeBomb();

    }

    public void addBombCount(){
        bombCount++;
        System.out.println("You can now deploy " + bombCount + " bombs at the same time.");
    }

    private void checkAttacks()
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
        }else if(handler.getKeyManager().aDOWN) {
            ar.x = cb.x + cb.width / 2 - arSize / 2;
            ar.y = cb.y -+ cb.height;
        }
        else if(handler.getKeyManager().aLEFT) {
            ar.x = cb.x - arSize;
            ar.y = cb.y + cb.height - arSize / 2;
        }
        else if(handler.getKeyManager().aRIGHT) {
            ar.x = cb.x + cb.width;
            ar.y = cb.y + cb.height / 2 - arSize / 2;
        } else {
            return;
        }

        attackTimer = 0;

        for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
            if(e.equals(this))
                continue;
            if(e.getCollisionBounds(0,0).intersects(ar)){
                e.hurt(1);
                return;
            }
        }

;    }

    private void placeBomb()
    {
        //TODO
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
            handler.getWorld().getBombManager().addBomb(Bomb.createNew(ar.x, ar.y));
        }else if(handler.getKeyManager().aDOWN) {
            ar.x = cb.x + cb.width / 2 - arSize / 2;
            ar.y = cb.y -+ cb.height;
            handler.getWorld().getBombManager().addBomb(Bomb.createNew(ar.x, ar.y));


        }
        else if(handler.getKeyManager().aLEFT) {
            ar.x = cb.x - arSize;
            ar.y = cb.y + cb.height - arSize / 2;
            handler.getWorld().getBombManager().addBomb(Bomb.createNew(ar.x, ar.y));

        }
        else if(handler.getKeyManager().aRIGHT) {
            ar.x = cb.x + cb.width;
            ar.y = cb.y + cb.height / 2 - arSize / 2;
            handler.getWorld().getBombManager().addBomb(Bomb.createNew(ar.x, ar.y));

        } else {
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
        g.drawImage(Assets.player, (int) x ,(int) y, width, height,null);

//        g.setColor(Color.red);
//        g.fillRect((int)(x + bounds.x),(int)(y + bounds.y), bounds.width, bounds.height);
    }

    @Override
    protected void destroy()
    {
        System.out.println("You lose!");
    }

}
