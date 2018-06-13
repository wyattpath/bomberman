package com.wyatt92.games.model.entities;

import com.wyatt92.games.controller.Handler;
import com.wyatt92.games.model.tiles.Tile;

/**
 * The Pawn is the physical representation of a player or an AI within the world.
 *
 */
public abstract class Creature extends Entity
{
    public static final int DEFAULT_HEALTH = 10;
    public static final float DEFAULT_SPEED = 3.0f;
    public static final int DEFAULT_CHARACTER_WIDTH = 64;
    public static final int DEFAULT_CHARACTER_HEIGHT = 64;

    protected int health;
    protected float speed;
    protected float xMove, yMove;

    private enum Direction {UP, DOWN, LEFT, RIGHT};
    private Direction mDirection;

    public Creature(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
        health = DEFAULT_HEALTH;
        speed = DEFAULT_SPEED;
    }

    public void move() {
        moveX();
        moveY();
    }
    public void moveX() {
        if(xMove > 0){ //moving right
            int tx = (int) ((x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH);

            if(!collisionWithTile(tx, (int)(y + bounds.y) / Tile.TILEHEIGHT) &&
                    !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)){
                x += xMove;
            } else {
                x = tx * Tile.TILEWIDTH - bounds.x - bounds.width - 1;
            }
        } else if(xMove < 0) {//moving left
            int tx = (int) ((x + xMove + bounds.x) / Tile.TILEWIDTH);
            if(!collisionWithTile(tx, (int)(y + bounds.y) / Tile.TILEHEIGHT)){
                x += xMove;
            } else {
                x = tx * Tile.TILEWIDTH + Tile.TILEWIDTH - bounds.x;
            }
        }
    }
    public void moveY() {
        if(yMove < 0){ //Up
            int ty = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT;
            if(!collisionWithTile((int)(x + bounds.x)/ Tile.TILEWIDTH, ty) &&
                    !collisionWithTile((int)(x + bounds.x + bounds.width)/ Tile.TILEWIDTH, ty)){
                y += yMove;
            } else {
                y = ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y;
            }
        }else if(yMove > 0) {//Down
            int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;
            if(!collisionWithTile((int)(x + bounds.x)/ Tile.TILEWIDTH, ty) &&
                    !collisionWithTile((int)(x + bounds.x + bounds.width)/ Tile.TILEWIDTH, ty)){
                y += yMove;
            } else {
                y = ty * Tile.TILEHEIGHT - bounds.y - bounds.height - 1;
            }
        }
    }

    protected boolean collisionWithTile(int x, int y) {
        return handler.getWorld().getTile(x,y).isSolid();
    }

    // GETTERS and SETTERS
    public float getxMove()
    {
        return xMove;
    }

    public void setxMove(float xMove)
    {
        this.xMove = xMove;
    }

    public float getyMove()
    {
        return yMove;
    }

    public void setyMove(float yMove)
    {
        this.yMove = yMove;
    }
}
