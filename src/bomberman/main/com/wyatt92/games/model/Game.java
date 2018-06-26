package com.wyatt92.games.model;

import com.wyatt92.games.model.entities.*;
import com.wyatt92.games.model.items.Item;
import com.wyatt92.games.model.items.ItemManager;
import com.wyatt92.games.model.tiles.Tile;
import com.wyatt92.games.model.utils.Utils;

import java.awt.*;
import java.util.*;

/**
 * This class loads the world from a file.
 * Creates and manages all entities, items, bombs, blasts.
 */
public class Game implements Model
{
    private int width, height;
    private int playerCount;
    private int[][] tiles;
    private static Map<Integer, Point> spawnMap;
    private boolean gameOver;
    private String path;
    private int winner;

    //Entities
    private EntityManager entityManager;
    // Item
    private ItemManager itemManager;
    // Bombs
    private BombManager bombManager;
    // Blasts
    private BombBlastManager bombBlastManager;
    // Player
    private PlayerManager playerManager;


    /**
     * Constructor
     *
     */
    public Game() {
    }



    public void resetWorld() {
        entityManager = new EntityManager(this);
        itemManager = new ItemManager(this);
        bombManager = new BombManager(this);
        bombBlastManager = new BombBlastManager(this);
        playerManager = new PlayerManager(this);
        playerCount = 2;
        gameOver = false;

        loadWorld(path);
        loadSpawnPoints();
        addPlayers();
        loadEntities();
    }

    private void loadSpawnPoints()
    {
        spawnMap = new HashMap<Integer, Point>() {
            {
                put(1, new Point(1, 1));
                put(2, new Point(width - 2, height - 2));
                put(3, new Point(width - 2, 0));
                put(4, new Point(0, height - 2));
            }
        };
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
                            (x*Tile.TILEWIDTH > Tile.TILEWIDTH*2 || y *Tile.TILEHEIGHT > Tile.TILEHEIGHT*2) &&
                                    (x*Tile.TILEWIDTH < width*Tile.TILEWIDTH - Tile.TILEWIDTH*3 || y*Tile.TILEHEIGHT < height*Tile.TILEHEIGHT - Tile.TILEHEIGHT*3)
                            ){
                        int r = new Random().nextInt(3);
                        if(r != 0){
                            entityManager.addEntity(new Stone(this, x * Tile.TILEWIDTH , y * Tile.TILEHEIGHT));
                        }
                    }
                }
            }
        }


    }

    public void update()
    {
        bombBlastManager.update();
        bombManager.update();
        itemManager.update();
        entityManager.update();
        playerManager.update();

        checkItemPickUp();
        checkPlayerCollisions();
        checkBlastCollisions();

    }

    private void checkBlastCollisions()
    {
        //checkCollision
        for(Blast b : bombBlastManager.getBlasts()) {
            for(Entity e : entityManager.getEntities()) {
                if(e.equals(b))
                    continue;
                if(e.getCollisionBounds(32,32).intersects(b.getBounds())){
                    e.destroy();
                    e.hurt(3);
                }
            }
        }
    }

    private void checkItemPickUp()
    {
        Iterator<Item> it = itemManager.getItems().iterator();
        while(it.hasNext()){
            Item i = it.next();
            Iterator<Player> itp = playerManager.getPlayers().iterator();
            while(itp.hasNext()) {
                Player p = itp.next();
                if(p.getCollisionBounds(0f,0f).intersects(i.getBounds())){
                    Sound.playSound("item_get.wav");
                    i.setPickedUp(true);
                    i.addEffect(p);
                }
            }
        }
    }


    private void checkPlayerCollisions()
    {
        Iterator<Entity> it = entityManager.getEntities().iterator();
        while (it.hasNext())
        {
            Entity e = it.next();
            e.update();
            if (playerManager.getPlayers().contains(e))
            {
                Player p = (Player) e;
                if (!checkEntityCollisions(p, p.getxMove(), 0f))
                {
                    p.moveX();
                }
                if (!checkEntityCollisions(p, 0f, p.getyMove()))
                {
                    p.moveY();
                }
            }
        }
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
        //players
        playerManager.draw(g);
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

    public void loadWorld(String path)
    {
        this.path = path;
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

    public void addPlayers() {
        for (int i = 1; i <= playerCount; i++) {
            final Point startingPoint = spawnMap.get(i);
            final Player player = new Player(this, startingPoint.x * Tile.TILEWIDTH -1, startingPoint.y * Tile.TILEHEIGHT -1, i);
            playerManager.addPlayer(player);
            entityManager.addEntity(player);
        }
    }

    public boolean checkEntityCollisions(Entity e1, float xOffset, float yOffset) {
        for(Entity e : entityManager.getEntities()){
            if(e.equals(e1)){
                continue;
            }
            if(e.getCollisionBounds(0f, 0f).intersects(e1.getCollisionBounds(xOffset, yOffset))){
                return true;
            }
        }
        return false;
    }

    // GETTERS and SETTERS

    public EntityManager getEntityManager()
    {
        return entityManager;
    }

    public ItemManager getItemManager()
    {
        return itemManager;
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


    public int getPlayerCount()
    {
        return playerCount;
    }


    public boolean isGameOver()
    {
        return gameOver;
    }


    @Override
    public ArrayList<Player> getPlayers() {
        return playerManager.getPlayers();
    }

    @Override
    public Player getPlayer(int id)
    {
        return playerManager.getPlayer(id);
    }

    @Override
    public void setGameOver(boolean b)
    {
        gameOver = b;
    }

    @Override
    public void setWinner(int winner)
    {
        this.winner = winner;
    }

    public int getWinner()
    {
        return winner;
    }

    @Override
    public int getPlayerAlive()
    {
        return playerManager.getPlayers().size();
    }


}


