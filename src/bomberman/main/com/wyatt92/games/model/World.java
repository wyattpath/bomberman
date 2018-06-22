package com.wyatt92.games.model;

import com.wyatt92.games.controller.Handler;
import com.wyatt92.games.model.entities.*;
import com.wyatt92.games.model.items.ItemManager;
import com.wyatt92.games.model.items.BombStrengthItem;
import com.wyatt92.games.model.tiles.Tile;

import java.awt.*;

public class World
{
    private Handler handler;
    private int width, height;
    private int playerCount;
    private int playerSpawnX,playerSpawnY;
    private int[][] tiles;
    private int xCenter;
    private int yCenter;
    private Player playerOne;
    private Player playerTwo;

    //Entities
    private EntityManager entityManager;
    // Item
    private ItemManager itemManager;
    // Bombs
    private BombManager bombManager;
    // Blasts
    private BombBlastManager bombBlastManager;

    public World(Handler handler, String path) {
        this.handler = handler;
        entityManager = new EntityManager(handler);
        itemManager = new ItemManager(handler);
        bombManager = new BombManager(handler);
        bombBlastManager = new BombBlastManager(handler);

        // Temporary entity code!


        loadWorld(path);
        playerOne = new PlayerOne(handler, 0+Tile.TILEWIDTH,0+Tile.TILEHEIGHT);
        playerTwo = new PlayerTwo(handler, width*Tile.TILEWIDTH-Tile.TILEWIDTH*2,height*Tile.TILEHEIGHT-Tile.TILEHEIGHT*2);
        entityManager.addPlayer(playerOne);
        entityManager.addPlayer(playerTwo);
        loadEntities();


//        itemManager.addItem(new BombStrengthItem(0).createNew(256, 64));
    }

    private void loadEntities()
    {
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                if(!getTile(x,y).isSolid())
                {
                    if(
                            (x*Tile.TILEWIDTH > 128 || y *Tile.TILEHEIGHT > 128) &&
                                    (x*Tile.TILEWIDTH < width*Tile.TILEWIDTH - Tile.TILEWIDTH*3 || y*Tile.TILEHEIGHT < height*Tile.TILEHEIGHT - Tile.TILEHEIGHT*3)
                            )
                        entityManager.addEntity(new Stone(handler, x * Tile.TILEWIDTH , y * Tile.TILEHEIGHT));
                } else System.out.println("entity not placed");
            }
        }


    }

    public void update() {
        bombBlastManager.update();
        bombManager.update();
        itemManager.update();
        entityManager.update();
    }


    public void draw(Graphics g)
    {
        for (int y = 0; y < height; y++){
            for (int x = 0; x < width; x++){
                getTile(x,y).setPosition(x * Tile.TILEWIDTH, y * Tile.TILEHEIGHT);
                getTile(x, y).draw(g);
            }
        }
        // Blasts
        bombBlastManager.draw(g);
        // Bombs
        bombManager.draw(g);
        // Items
        itemManager.draw(g);
        //entities
        entityManager.draw(g);
    }

    public Tile getTile(int x, int y)
    {
        if(x < 0 || y < 0 || x >= width || y >= height){
            return Tile.grassTile;
        }
        Tile t = Tile.tiles[tiles[x][y]];
        if (t == null){
            return Tile.dirtTile;
        }
        return t;
    }

    public Point getTileCenter(int x, int y){

        for (int ty = 0; ty < height; ty++)
        {
            if(y >= ty*Tile.TILEHEIGHT && y < ty*Tile.TILEHEIGHT + Tile.TILEHEIGHT)
            {
                yCenter = ty * Tile.TILEHEIGHT ;
            }
        }
        for (int tx = 0; tx < width; tx++)
        {
            if(x >= tx*Tile.TILEWIDTH && y < tx*Tile.TILEWIDTH + Tile.TILEWIDTH)
            {
                xCenter = tx * Tile.TILEWIDTH;
            }
        }
//        for(Tile t : Tile.tiles){
//            System.out.println(t.getPosition());
//        }
//        System.out.println(handler.getWorld().getTile(x,y).getPosition());
        return new Point(xCenter,yCenter);
    }

    public Point getTilePosition(int x, int y) {
        Point point = new Point();
        for(int ty = 0; ty < height; ty++)
            for(int tx = 0; tx < width; tx++){
            if(
                    x+1 >= tx &&
                        x+1 < tx + Tile.TILEWIDTH &&
                        y+1 >= ty &&
                                y+1 < ty + Tile.TILEHEIGHT){
                point = new Point(tx,ty);
            }
            }
            return point;
    }

    private void loadWorld(String path)
    {
        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
//        for(int i = 0; i < playerCount; i++)
//        {
//
//            playerSpawnX = Utils.parseInt(tokens[3 + i*2]);
//            playerSpawnY = Utils.parseInt(tokens[4 + i*2]);
//            entityManager.getPlayer(i).setX(playerSpawnX);
//            entityManager.getPlayer(i).setY(playerSpawnY);
//        }


        tiles = new int[width][height];
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
            }
        }
    }

    // GETTERS and SETTERS

    public EntityManager getEntityManager()
    {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }

    public ItemManager getItemManager()
    {
        return itemManager;
    }

    public void setItemManager(ItemManager itemManager)
    {
        this.itemManager = itemManager;
    }

    public BombManager getBombManager()
    {
        return bombManager;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public int[][] getTiles()
    {
        return tiles;
    }

    public BombBlastManager getBombBlastManager() {
        return bombBlastManager;
    }

    public void setBombBlastManager(BombBlastManager bombBlastManager) {
        this.bombBlastManager = bombBlastManager;
    }
}


