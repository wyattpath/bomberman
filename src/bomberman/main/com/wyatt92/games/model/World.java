package com.wyatt92.games.model;

import com.wyatt92.games.model.entities.*;
import com.wyatt92.games.model.items.ItemManager;
import com.wyatt92.games.model.tiles.Tile;
import com.wyatt92.games.model.utils.Utils;

import java.awt.*;
import java.util.Random;

/**
 * This class loads the world from a file.
 * Creates and manages all entities, items, bombs, blasts.
 */
public class World
{
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


    /**
     * Constructor
     *
     * @param path path to the word file
     */
    public World( String path) {
        entityManager = new EntityManager(this);
        itemManager = new ItemManager(this);
        bombManager = new BombManager(this);
        bombBlastManager = new BombBlastManager(this);

        loadWorld(path);
        loadEntities();
    }

    private void loadEntities()
    {
        playerOne = new PlayerOne(this, 0+Tile.TILEWIDTH,0+Tile.TILEHEIGHT);
        playerTwo = new PlayerTwo(this, width*Tile.TILEWIDTH-Tile.TILEWIDTH*2-1,height*Tile.TILEHEIGHT-Tile.TILEHEIGHT*2-1);
        entityManager.addPlayer(playerOne);
        entityManager.addPlayer(playerTwo);

        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                if(!getTile(x,y).isSolid())
                {
                    if(
                            (x*Tile.TILEWIDTH > Tile.TILEWIDTH*2 || y *Tile.TILEHEIGHT > Tile.TILEHEIGHT*2) &&
                                    (x*Tile.TILEWIDTH < width*Tile.TILEWIDTH - Tile.TILEWIDTH*3 || y*Tile.TILEHEIGHT < height*Tile.TILEHEIGHT - Tile.TILEHEIGHT*3)
                            ){
                        int r = new Random().nextInt(3);
                        if(r != 0){
                            entityManager.addEntity(new Stone(this, x * Tile.TILEWIDTH , y * Tile.TILEHEIGHT));
                        }
                    }
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

    private void loadWorld(String path)
    {
        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);

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
        return width* Tile.TILEWIDTH;
    }

    public int getHeight()
    {
        return height*Tile.TILEHEIGHT;
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

    public Player getPlayerOne()
    {
        return playerOne;
    }

    public void setPlayerOne(Player playerOne)
    {
        this.playerOne = playerOne;
    }

    public Player getPlayerTwo()
    {
        return playerTwo;
    }

    public void setPlayerTwo(Player playerTwo)
    {
        this.playerTwo = playerTwo;
    }
}


