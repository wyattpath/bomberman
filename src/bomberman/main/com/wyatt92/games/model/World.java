package com.wyatt92.games.model;

import com.wyatt92.games.controller.Handler;
import com.wyatt92.games.model.entities.EntityManager;
import com.wyatt92.games.model.entities.Player;
import com.wyatt92.games.model.entities.Stone;
import com.wyatt92.games.model.tiles.Tile;

import java.awt.*;

public class World
{
    private Handler handler;
    private int width, height;
    private int playerSpawnX,playerSpawnY;
    private int[][] tiles;

    //Entities
    private EntityManager entityManager;

    public World(Handler handler, String path) {
        this.handler = handler;
        entityManager = new EntityManager(handler, new Player(handler, 100, 100));
        entityManager.addEntity(new Stone(handler, 100, 250));
        loadWorld(path);

        entityManager.getPlayer().setX(playerSpawnX);
        entityManager.getPlayer().setY(playerSpawnY);
    }

    public void update() {
        entityManager.update();
    }


    public void draw(Graphics g)
    {
        for (int y = 0; y < height; y++){
            for (int x = 0; x < width; x++){
                getTile(x, y).draw(g, x * Tile.TILEWIDTH, y * Tile.TILEHEIGHT);
            }
        }

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
        playerSpawnX = Utils.parseInt(tokens[2]);
        playerSpawnY = Utils.parseInt(tokens[3]);

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
}

