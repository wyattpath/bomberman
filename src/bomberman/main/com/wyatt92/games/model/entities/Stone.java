package com.wyatt92.games.model.entities;

import com.wyatt92.games.model.Assets;
import com.wyatt92.games.model.World;
import com.wyatt92.games.model.items.Item;
import com.wyatt92.games.model.tiles.Tile;
import com.wyatt92.games.model.utils.Animation;

import java.awt.*;
import java.util.Random;

/**
 * This class represents a Stone.
 * Spawns sometimes an Item if it gets destroyed.
 */
public class Stone extends StaticEntity {
    private Animation animStone;

    public Stone(World world, float x, float y) {
        super(world, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
        animStone = new Animation(1000, Assets.stone);
    }

    @Override
    public void update() {
        animStone.update();
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(animStone.getCurrentFrame(), (int) x, (int) y, width, height, null);
    }

    @Override
    protected void destroy()
    {
        int randInt = new Random().nextInt(Item.items.length);
        System.out.println(randInt);
        if(randInt != 0 && randInt < 4)
            world.getItemManager().addItem(Item.items[randInt].createNew((int) x + Tile.TILEWIDTH/4,(int) y + Tile.TILEHEIGHT/4));
//        handler.getWorld().getItemManager().addItem(Item.powerUpItem.createNew((int) x + Tile.TILEWIDTH/4,(int) y + Tile.TILEHEIGHT/4));
    }
}
