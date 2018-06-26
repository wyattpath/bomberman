package com.wyatt92.games.model;

import com.wyatt92.games.model.entities.*;
import com.wyatt92.games.model.items.Item;
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
    private Comparator<Entity> drawSorter = (a, b) -> (a.getY() + a.getHeight() < b.getY() + b.getHeight())? -1 : 1;

    //Entities
    private ArrayList<Entity> entities;
    // Item
    private ArrayList<Item> items;

    // Bombs
    private ArrayList<Bomb> bombs;
    // Blasts
    private ArrayList<Blast> blasts;
    // Player
    private ArrayList<Player> players;

    private ArrayList<Stone> stones;



    /**
     * Constructor
     *
     */
    public Game() {


    }



    public void resetWorld() {
        stones = new ArrayList<>();
        entities = new ArrayList<>();
        items = new ArrayList<>();
        blasts = new ArrayList<>();
        bombs = new ArrayList<>();
        players = new ArrayList<>();

        playerCount = 2;

        loadWorld(path);
        loadTiles();
        loadSpawnPoints();
        addPlayers();
        loadEntities();
    }

    private void loadTiles()
    {
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                getTile(x, y).setPosition(x * Tile.TILEWIDTH, y * Tile.TILEHEIGHT);
            }
        }
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
                            Stone s = new Stone(this, x * Tile.TILEWIDTH , y * Tile.TILEHEIGHT);
                            entities.add(s);
                            stones.add(s);
                        }
                    }
                }
            }
        }


    }

    public void update()
    {
        Iterator<Entity> entityIterator = entities.iterator();
        while(entityIterator.hasNext()){
            Entity e = entityIterator.next();
            e.update();
            if(!e.isActive()){
                entityIterator.remove();
            }
        }
        entities.sort(drawSorter);

        Iterator<Player> playerIterator = players.iterator();
        while (playerIterator.hasNext())
        {
            Player p = playerIterator.next();
            p.update();
            if (!p.isActive())
            {
                playerIterator.remove();
            }
        }

        Iterator<Blast> blastIterator = blasts.iterator();
        while(blastIterator.hasNext()) {
            Blast blast = blastIterator.next();
            blast.update();

            if(!blast.isActive()) {
                blastIterator.remove();
            }
        }

        Iterator<Item> itemIterator = items.iterator();
        while(itemIterator.hasNext()) {
            Item item = itemIterator.next();
            item.update();
            if(item.isPickedUp()) {
                itemIterator.remove();
            }
        }

        Iterator<Bomb> bombIterator = bombs.iterator();
        while(bombIterator.hasNext()) {
            Bomb b = bombIterator.next();
            b.update();
            if(!b.isActive()) {
                String sound = (b.getBombStrength()>5) ? "boom_L.wav" : (b.getBombStrength()> 3)? "boom_M.wav" : "boom_S.wav";
                Sound.playSound(sound);

                placeBlast(b,0,0);
                placeBlast(b, Tile.TILEWIDTH,0); // nextTile on the right
                placeBlast(b,  -Tile.TILEWIDTH, 0); // nextTile on the left
                placeBlast(b, 0,-Tile.TILEHEIGHT); //nextTile above
                placeBlast(b, 0,Tile.TILEHEIGHT); //nextTile below

                b.destroy();
                bombIterator.remove();
            }
        }

        Iterator<Stone> stoneIterator = stones.iterator();
        while(stoneIterator.hasNext()) {
            Stone stone = stoneIterator.next();
            stone.update();

            if(!stone.isActive()) {
                stoneIterator.remove();
            }
        }

        checkItemPickUp();
        checkPlayerCollisions();
        checkBlastCollisions();

    }

    private void placeBlast(Bomb b, int xOffset, int yOffset) {
        boolean stop = false;
        for(int i = 0; i < b.getBombStrength();i++)
        {
            if(stop || collisionWithTile((int)b.getX() + xOffset + i * xOffset, (int)b.getY()+ yOffset + i *yOffset))
                return;

            Blast blast = new Blast(this, b.getX() + xOffset + i*xOffset, b.getY() + yOffset + i *yOffset);
            blasts.add(blast);
//            addBlast(blast);

            Rectangle tempBounds = new Rectangle();
            tempBounds.x = (int)b.getX() + xOffset + i * xOffset;
            tempBounds.y = (int)b.getY() + yOffset + i *yOffset;
            tempBounds.setSize(b.BOMBWIDTH, b.BOMBHEIGHT);
            for (Entity e : entities)
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

    private void checkBlastCollisions()
    {
        //checkCollision
        for(Blast b : blasts) {
            for(Entity e : entities) {
                if(e.equals(b))
                    continue;
                if(e.getCollisionBounds(32,32).intersects(b.getBounds())){
                    e.destroy();
                    e.hurt(3);
                }
            }
        }
    }

    protected boolean collisionWithTile(int x, int y) {
        return getTile(x/Tile.TILEWIDTH,y/Tile.TILEHEIGHT).isSolid();
    }

    private void checkItemPickUp()
    {
        Iterator<Item> it = items.iterator();
        while(it.hasNext()){
            Item i = it.next();
            Iterator<Player> itp = players.iterator();
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
        Iterator<Entity> it = entities.iterator();
        while (it.hasNext())
        {
            Entity e = it.next();
            e.update();
            if (players.contains(e))
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
//        for (int y = 0; y < height; y++){
//            for (int x = 0; x < width; x++){
//                getTile(x,y).setPosition(x * Tile.TILEWIDTH, y * Tile.TILEHEIGHT);
////                getTile(x, y).draw(g);
//            }
//        }
//        // Blasts
//        bombBlastManager.draw(g);
//        // Bombs
//        bombManager.draw(g);
//        // Items
//        itemManager.draw(g);
//        //entities
//        entityManager.draw(g);
//        //players
//        playerManager.draw(g);
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
            players.add(player);
            entities.add(player);
        }
    }

    public void addPlayer(Player p)
    {
        p.setGame(this);
        players.add(p);
        entities.add(p);
    }

    public boolean checkEntityCollisions(Entity e1, float xOffset, float yOffset) {
        for(Entity e : entities){
            if(e.equals(e1)){
                continue;
            }
            if(e.getCollisionBounds(0f, 0f).intersects(e1.getCollisionBounds(xOffset, yOffset))){
                return true;
            }
        }
        return false;
    }

    @Override
    public void addItem(Item i)
    {
        items.add(i);
    }

    public ArrayList<Blast> getBlasts()
    {
        return blasts;
    }

    // GETTERS and SETTERS



    public int getWidth()
    {
        return width* Tile.TILEWIDTH;
    }

    public int getHeight()
    {
        return height*Tile.TILEHEIGHT;
    }

    public int getTileColums(){
        return width;
    }

    public int getTileRows(){
        return height;
    }

    public int[][] getTiles()
    {
        return tiles;
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
        return players;
    }

    @Override
    public Player getPlayer(int id)
    {
        return players.get(id);
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
        return players.size();
    }


    @Override
    public ArrayList<Bomb> getBombs()
    {
        return bombs;
    }

    @Override
    public ArrayList<Entity> getEntities() {
        return entities;
    }

    @Override
    public ArrayList<Item> getItems()
    {
        return items;
    }

    @Override
    public ArrayList<Stone> getStones()
    {
        return stones;
    }

    public void addBomb(Bomb b) {
        b.setWorld(this);
        bombs.add(b);
    }

}


