package com.wyatt92.games.model.entities;

import com.wyatt92.games.model.Game;
import com.wyatt92.games.model.tiles.Tile;
import com.wyatt92.games.model.utils.Sound;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class manages all bombs by adding, updating, removing and drawing bombs.
 */
public class BombManager
{
    private Game game;
    private ArrayList<Bomb> bombs;

    public BombManager(Game game) {
        this.game = game;
        bombs = new ArrayList<>();
    }

    public void update()
    {
        Iterator<Bomb> it = bombs.iterator();
        while(it.hasNext()) {
            Bomb b = it.next();
            b.update();
            if(!b.isActive()) {
                String sound = (b.bombStrength>5) ? "boom_L.wav" : (b.bombStrength> 3)? "boom_M.wav" : "boom_S.wav";
                Sound.playSound(sound);

                placeBlast(b,0,0);
                placeBlast(b, Tile.TILEWIDTH,0); // nextTile on the right
                placeBlast(b,  -Tile.TILEWIDTH, 0); // nextTile on the left
                placeBlast(b, 0,-Tile.TILEHEIGHT); //nextTile above
                placeBlast(b, 0,Tile.TILEHEIGHT); //nextTile below

                b.destroy();
                it.remove();
            }
        }
    }

    public void draw(Graphics g)
    {
        for(Bomb i : bombs)
            i.draw(g);
    }

    public void addBomb(Bomb b) {
        b.setWorld(game);
        bombs.add(b);
    }

    private void placeBlast(Bomb b, int xOffset, int yOffset) {
        boolean stop = false;
        for(int i = 0; i < b.bombStrength;i++)
        {
            if(stop || collisionWithTile((int)b.getX() + xOffset + i * xOffset, (int)b.getY()+ yOffset + i *yOffset))
                return;

            game.getBombBlastManager().addBlast(new Blast(game, b.getX() + xOffset + i*xOffset, b.getY() + yOffset + i *yOffset));

            Rectangle tempBounds = new Rectangle();
            tempBounds.x = (int)b.getX() + xOffset + i * xOffset;
            tempBounds.y = (int)b.getY() + yOffset + i *yOffset;
            tempBounds.setSize(b.BOMBWIDTH, b.BOMBHEIGHT);
            for (Entity e : game.getEntityManager().getEntities())
            {
                if (e.equals(this))
                    continue;
                if (e.getCollisionBounds(32, 32).intersects(tempBounds))
                {
                    e.destroy();
                    e.hurt(3);
                    stop = true;
                }
            }

        }
    }

    protected boolean collisionWithTile(int x, int y) {
        return game.getTile(x/Tile.TILEWIDTH,y/Tile.TILEHEIGHT).isSolid();
    }

    public ArrayList<Bomb> getBombs()
    {
        return bombs;
    }
}
