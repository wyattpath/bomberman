package com.wyatt92.games.model.entities;

import com.wyatt92.games.controller.Handler;
import com.wyatt92.games.model.tiles.Tile;

import java.awt.*;

/**
 * The Dynamic Entity is an entity that can be moved around the world.
 *
 */
public abstract class DynamicEntity extends Entity
{
    public static final float DEFAULT_SPEED = 3.0f;
    public static final int DEFAULT_CHARACTER_WIDTH = 64;
    public static final int DEFAULT_CHARACTER_HEIGHT = 64;

    protected float speed;
    protected float xMove, yMove;


    public DynamicEntity(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
        speed = DEFAULT_SPEED;
    }

    public void move() {
        if(!checkEntityCollisions(xMove, 0f)) {
            moveX();

        }
        if(!checkEntityCollisions(0f, yMove)) {
            moveY();
        }

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
            if(!collisionWithTile(tx, (int)(y + bounds.y) / Tile.TILEHEIGHT) &&
                    !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)){
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

    public Point getCenterpoint(){
        return centerPoint;
    }
}
