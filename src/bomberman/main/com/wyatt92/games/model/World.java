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
    private int playerSpawnX,playerSpawnY;
    private int[][] tiles;
    private int xCenter;
    private int yCenter;

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
        entityManager = new EntityManager(handler, new Player(handler, 0, 0));
        itemManager = new ItemManager(handler);
        bombManager = new BombManager(handler);
        bombBlastManager = new BombBlastManager(handler);

        // Temporary entity code!
        loadWorld(path);
        loadEntities();


//        itemManager.addItem(new BombStrengthItem(0).createNew(256, 64));
    }

    private void loadEntities()
    {
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
//                System.out.println();
//                System.out.println(!getTile(x,y).isSolid());
//                System.out.println("playerPos = " + (int)entityManager.getPlayer().getX() + "," + (int)entityManager.getPlayer().getY());
//                System.out.println("tilePos = " + x * Tile.TILEWIDTH+ "," + y * Tile.TILEHEIGHT);
                if(!getTile(x,y).isSolid())
//                            && ((int)entityManager.getPlayer().getX() != x * Tile.TILEWIDTH )
//                            && ((int)entityManager.getPlayer().getY() != y * Tile.TILEHEIGHT))
                {
//                    System.out.println("entity placed");
                    entityManager.addEntity(new Stone(handler, x * Tile.TILEWIDTH , y * Tile.TILEHEIGHT));
                } else System.out.println("entity not placed");
            }
        }
        entityManager.getEntities().remove(14);
        entityManager.getEntities().remove(1);
        entityManager.getEntities().remove(1);


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
        playerSpawnX = Utils.parseInt(tokens[2]);
        playerSpawnY = Utils.parseInt(tokens[3]);

        entityManager.getPlayer().setX(playerSpawnX);
        entityManager.getPlayer().setY(playerSpawnY);

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


