package com.wyatt92.games.model;

import com.wyatt92.games.model.entities.*;
import com.wyatt92.games.view.Sound;

import java.awt.*;
import java.util.*;

/**
 * This class loads the world from a file.
 * Creates and manages all entities, items, bombs, blasts.
 */
public class Model
{
    private static Map<Integer, Point> spawnMap;
    private int width, height;
    private int playerCount;
    private int[][] tiles;
    private ArrayList<Tile> tileArrayList;
    private String path;
    private boolean playing, gameOver, pausing;
    private final Comparator<Entity> drawSorter = (a, b) -> (a.getY() + a.getHeight() < b.getY() + b.getHeight())? -1 : 1;

    //Entities
    private ArrayList<Entity> entities;
    private ArrayList<Item> items;
    private ArrayList<Bomb> bombs;
    private ArrayList<Blast> blasts;
    private ArrayList<Player> players;
    private ArrayList<Stone> stones;

    /**
     * Generates a new playing field.
     */
    public void resetWorld()
    {
        tileArrayList = new ArrayList<>();
        tileArrayList.add(new Tile(0));
        tileArrayList.add(new Tile(1));
        tileArrayList.add(new Tile(2));
        stones = new ArrayList<>();
        entities = new ArrayList<>();
        items = new ArrayList<>();
        blasts = new ArrayList<>();
        bombs = new ArrayList<>();
        players = new ArrayList<>();

        playerCount = 2;

        loadWorld(path);
        loadSpawnPoints();
        addPlayers();
        loadStones();

        playing = false;
        gameOver = false;
        pausing = false;
    }

    /**
     * Updates all entities
     */
    public void update()
    {
        updateEntities();
        updatePlayers();
        updateBlasts();
        updateItems();
        updateBombs();
        updateStones();

        checkItemPickUp();
        checkPlayerStoneCollisions();
        checkBlastEntityCollisions();

    }


    /**
     * Moves the specified player to the left.
     *
     * @param id player id
     */
    public void moveLeft(int id)
    {
        getPlayer(id).moveLeft();
    }

    /**
     * Moves the specified player to the right.
     *
     * @param id player id
     */
    public void moveRight(int id)
    {
        getPlayer(id).moveRight();
    }

    /**
     * Moves the specified player up.
     *
     * @param id player id
     */
    public void moveUp(int id)
    {
        getPlayer(id).moveUp();
    }

    /**
     * Moves the specified player down.
     *
     * @param id player id
     */
    public void moveDown(int id)
    {
        getPlayer(id).moveDown();
    }

    /**
     * Places a bomb at the specified player position.
     *
     * @param id player id
     */
    public void placeBomb(int id)
    {
        Player p = getPlayer(id);
        // attack cooldown
        if (p.getTimer() < p.getAttackCooldown())
            return;

        if (p.getBombCount() > 0)
        {
            Sound.playSound("bomb_Set.wav");
            Bomb b = new Bomb(p.getCenterPoint().x, p.getCenterPoint().y, p.getBombStrength());
            bombs.add(b);
            p.decrementBombCount();
            p.setTimer(0);
        }
    }

    /**
     * Checks Collision with tile.
     *
     * @param x x-coordinate of tile
     * @param y y-coordinate of tile
     * @return if tile is solid
     */
    private boolean collisionWithTile(int x, int y)
    {
        return getTile(x / Tile.TILEWIDTH, y / Tile.TILEHEIGHT).getID() == 2;
    }

    /**
     * Checks if player collides with an item and adds effect of item to the player.
     */
    private void checkItemPickUp()
    {
        for (Item i : items)
        {
            for (Player p : players)
            {
                if (p.getCollisionBounds(0f, 0f).intersects(i.getBounds()))
                {
                    Sound.playSound("item_get.wav");
                    i.setPickedUp(true);
                    System.out.println(i.getId());
                    p.addEffect(i.getId());
                }
            }
        }
    }


    /**
     * Returns tile on specified coordinations.
     *
     * @param x x-coordinate of tile
     * @param y y-coordinate of tile
     * @return tile on specified position
     */
    public Tile getTile(int x, int y)
    {
        if (x < 0 || y < 0 || x >= width || y >= height)
        {
            return tileArrayList.get(0); // dirtTile
        }
        Tile t = Tile.tiles[tiles[x][y]];
        if (t == null)
        {
            return tileArrayList.get(1); // grassTile
        }
        return t; // wallTile
    }

    /**
     * Loads world from file.
     *
     * @param path world as .txt-file
     */
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

    /**
     * Checks if specified entity collides with any entity.
     *
     * @param e1 entity to check
     * @param xOffset offset from x-coordinate of entity
     * @param yOffset offset from y-coordinate of entity
     * @return if entity collides with any entity
     */
    private boolean checkEntityCollisions(Entity e1, float xOffset, float yOffset)
    {
        for (Entity e : entities)
        {
            if (e.equals(e1))
            {
                continue;
            }
            if (e.getCollisionBounds(0f, 0f).intersects(e1.getCollisionBounds(xOffset, yOffset)))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if specified entity collides with any player.
     *
     * @param e1 entity to check
     * @param xOffset offset from x-coordinate of entity
     * @param yOffset offset from y-coordinate of entity
     * @return if entity collides with any player
     */
    private boolean checkPlayerCollision(Entity e1, float xOffset, float yOffset)
    {
        for (Player e : players)
        {
            if (e.equals(e1))
            {
                continue;
            }
            if (e.getCollisionBounds(0f, 0f).intersects(e1.getCollisionBounds(xOffset, yOffset)))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if specified entity collides with any stone.
     *
     * @param e1 entity to check
     * @param xOffset offset from x-coordinate of entity
     * @param yOffset offset from y-coordinate of entity
     * @return if entity collides with any stone
     */
    private boolean checkStoneCollisions(Entity e1, float xOffset, float yOffset)
    {
        for (Stone e : stones)
        {
            if (e.equals(e1))
            {
                continue;
            }
            if (e.getCollisionBounds(0f, 0f).intersects(e1.getCollisionBounds(xOffset, yOffset)))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds specified number of players to the world on specified spawn points.
     */
    private void addPlayers()
    {
        for (int i = 1; i <= playerCount; i++)
        {
            final Point startingPoint = spawnMap.get(i);
            final Player player = new Player(startingPoint.x * Tile.TILEWIDTH - 1, startingPoint.y * Tile.TILEHEIGHT - 1, i);
            players.add(player);
            entities.add(player);
        }
    }

    /**
     * Moves specified player along the x-axes if no collision with tile.
     *
     * @param p player to move
     */
    private void moveX(Player p)
    {
        Rectangle bounds = p.getBounds();
        int x = (int) p.getX();
        int y = (int) p.getY();
        int xMove = (int) p.getxMove();

        if (xMove > 0)
        { //moving right
            int tx = (x + xMove + bounds.x + bounds.width);

            if (!collisionWithTile(tx, y + bounds.y) &&
                    !collisionWithTile(tx, (y + bounds.y + bounds.height)))
            {
                x += xMove;
                p.setX(x);
            } else
            {
                x = tx - bounds.x - bounds.width - xMove;
                p.setX(x);
            }
        } else if (xMove < 0)
        {//moving left
            int tx = (x + xMove + bounds.x);
            if (!collisionWithTile(tx, y + bounds.y) &&
                    !collisionWithTile(tx, y + bounds.y + bounds.height))
            {
                x += xMove;
                p.setX(x);
            } else
            {
                x = tx - bounds.x - xMove;
                p.setX(x);
            }
        }
    }

    /**
     * Moves player along the y-axes if no collision with tile.
     *
     * @param p player
     */
    private void moveY(Player p)
    {
        Rectangle bounds = p.getBounds();
        int x = (int) p.getX();
        int y = (int) p.getY();
        int yMove = (int) p.getyMove();

        if (yMove < 0)
        { //Up
            int ty = y + yMove + bounds.y;
            if (!collisionWithTile(x + bounds.x, ty) &&
                    !collisionWithTile(x + bounds.x + bounds.width, ty))
            {
                y += yMove;
                p.setY(y);
            } else
            {
                y = ty - bounds.y - yMove;
                p.setY(y);
            }
        } else if (yMove > 0)
        {//Down
            int ty = y + yMove + bounds.y + bounds.height;
            if (!collisionWithTile(x + bounds.x, ty) &&
                    !collisionWithTile(x + bounds.x + bounds.width, ty))
            {
                y += yMove;
                p.setY(y);
            } else
            {
                y = ty - bounds.y - bounds.height - yMove;
                p.setY(y);
            }
        }
    }

    /**
     * Loads player spawn locations.
     */
    private void loadSpawnPoints()
    {
        spawnMap = new HashMap<Integer, Point>()
        {
            {
                put(1, new Point(1, 1));
                put(2, new Point(width - 2, height - 2));
                put(3, new Point(width - 2, 0));
                put(4, new Point(0, height - 2));
            }
        };
    }

    /**
     * Generates randomly placed stones on the world.
     */
    private void loadStones()
    {

        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                if (getTile(x, y).getID() != 2)
                {
                    if (
                            (x * Tile.TILEWIDTH > Tile.TILEWIDTH * 2 || y * Tile.TILEHEIGHT > Tile.TILEHEIGHT * 2) &&
                                    (x * Tile.TILEWIDTH < width * Tile.TILEWIDTH - Tile.TILEWIDTH * 3 || y * Tile.TILEHEIGHT < height * Tile.TILEHEIGHT - Tile.TILEHEIGHT * 3)
                            )
                    {
                        int r = new Random().nextInt(3);
                        if (r != 0)
                        {
                            Stone s = new Stone(x * Tile.TILEWIDTH, y * Tile.TILEHEIGHT);
                            entities.add(s);
                            stones.add(s);
                        }
                    }
                }
            }
        }
    }

    /**
     * Checks if blasts collides with any entity and hurts them.
     * Checks if blasts collides with any bomb and hurts them.
     * Checks if blasts collides with any players and hurts them.
     */
    private void checkBlastEntityCollisions()
    {
        //checkCollision
        for (Blast b : blasts)
        {
            for (Entity e : entities)
            {
                if (e.equals(b))
                    continue;
                if (e.getCollisionBounds(32, 32).intersects(b.getBounds()))
                {
                    e.hurt(3);
                }
            }

            for (Bomb bomb : bombs)
            {
                if (bomb.getCollisionBounds(0, 0).intersects(b.getCollisionBounds(0f,0f)))
                {
                    bomb.hurt(3);
                }
            }

            for (Player p : players)
            {
                if (p.getCollisionBounds(32f, 32f).intersects(b.getCollisionBounds(0f,0f)))
                {
                    p.hurt(3);
                }
            }
        }
    }

    /**
     * If player does not hit a stone, then moce player to specified direction.
     */
    private void checkPlayerStoneCollisions()
    {
        for (Player p : players)
        {
            {
                if(!checkStoneCollisions(p,p.getxMove(),0f) && !checkPlayerCollision(p,p.getxMove(),0f)) {
                    moveX(p);
                }

                if (!checkStoneCollisions(p,0f,p.getyMove()) && !checkPlayerCollision(p,0f,p.getyMove())){
                    moveY(p);
                }

                p.setxMove(0);
                p.setyMove(0);

            }
        }


    }

    /**
     * Updates every stone.
     * If stone is not active, remove the stone
     * and occasionally place an item at the stone's position.
     */
    private void updateStones()
    {
        Iterator<Stone> stoneIterator = stones.iterator();
        while (stoneIterator.hasNext())
        {
            Stone stone = stoneIterator.next();
            stone.update();

            if (!stone.isActive())
            {
                //placeItem
                int randInt = new Random().nextInt(10);
                if (randInt < 3)
                {
                    float x = stone.getX() + Tile.TILEWIDTH / 4;
                    float y = stone.getY() + Tile.TILEHEIGHT / 4;
                    Item item = new Item(x, y, randInt);
                    items.add(item);
                    entities.add(item);
                }

                stoneIterator.remove();
                entities.remove(stone);
            }
        }
    }

    /**
     * Updates every item.
     * if item is picked up, remove the item.
     */
    private void updateItems()
    {
        Iterator<Item> itemIterator = items.iterator();
        while (itemIterator.hasNext())
        {
            Item item = itemIterator.next();
            item.update();
            if (item.isPickedUp())
            {
                itemIterator.remove();
            }
        }
    }

    /**
     * Updates blasts.
     * If blast is not active, remove the blast.
     */
    private void updateBlasts()
    {
        Iterator<Blast> blastIterator = blasts.iterator();
        while (blastIterator.hasNext())
        {
            Blast blast = blastIterator.next();
            blast.update();

            if (!blast.isActive())
            {
                blastIterator.remove();
            }
        }
    }

    /**
     * Updates bombs.
     * If bomb is not active, remove the bomb.
     */
    private void updateBombs()
    {
        Iterator<Bomb> bombIterator = bombs.iterator();
        while (bombIterator.hasNext())
        {
            Bomb b = bombIterator.next();
            b.update();
            if (!b.isActive())
            {
                String sound = (b.getBombStrength() > 5) ? "boom_L.wav" : (b.getBombStrength() > 3) ? "boom_M.wav" : "boom_S.wav";
                Sound.playSound(sound);

                placeBlast(b, 0, 0);
                placeBlast(b, Tile.TILEWIDTH, 0); // nextTile on the right
                placeBlast(b, -Tile.TILEWIDTH, 0); // nextTile on the left
                placeBlast(b, 0, -Tile.TILEHEIGHT); //nextTile above
                placeBlast(b, 0, Tile.TILEHEIGHT); //nextTile below

                bombIterator.remove();
            }
        }
    }

    /**
     * Places blasts at specified bomb's position with specified offset.
     * Places another blast depending on the bomb's strength.
     * If blast hits an entity, the entity gets hurt and no blast will be placed along that line.
     *
     * @param b bomb
     * @param xOffset offset along the x-axes
     * @param yOffset offset along the y-axes
     */
    private void placeBlast(Bomb b, int xOffset, int yOffset)
    {
        boolean stop = false;
        for (int i = 0; i < b.getBombStrength(); i++)
        {
            if (stop || collisionWithTile((int) b.getX() + xOffset + i * xOffset, (int) b.getY() + yOffset + i * yOffset))
                return;

            Blast blast = new Blast(b.getX() + xOffset + i * xOffset, b.getY() + yOffset + i * yOffset);
            blasts.add(blast);

            Rectangle tempBounds = new Rectangle();
            tempBounds.x = (int) b.getX() + xOffset + i * xOffset;
            tempBounds.y = (int) b.getY() + yOffset + i * yOffset;
            tempBounds.setSize(Bomb.BOMBWIDTH, Bomb.BOMBHEIGHT);
            for (Entity e : entities)
            {
                if (e.equals(b))
                    continue;
                if (e.getCollisionBounds(32, 32).intersects(tempBounds))
                {
                    e.hurt(3);
                    stop = true;
                }
            }

        }
    }

    /**
     * Updates players.
     * If player is not active, remove the player.
     */
    private void updatePlayers()
    {
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
    }

    /**
     * Updates entities.
     * If entity is not active, remove the entity and sort them.
     */
    private void updateEntities()
    {
        Iterator<Entity> entityIterator = entities.iterator();
        while (entityIterator.hasNext())
        {
            Entity e = entityIterator.next();
            e.update();
            if (!e.isActive())
            {
                entityIterator.remove();
            }
        }
        entities.sort(drawSorter);
    }


    // GETTERS and SETTERS
    public int getPlayerAlive()
    {
        for (Player p : players)
        {
            if (p.isActive())
            {
                return p.getId();
            }
        }
        return 0;
    }

    public int getWidth()
    {
        return width * Tile.TILEWIDTH;
    }

    public int getHeight()
    {
        return height * Tile.TILEHEIGHT;
    }

    public int getTileColumns()
    {
        return width;
    }

    public int getTileRows()
    {
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

    public void setGameOver(boolean b)
    {
        gameOver = b;
    }

    public ArrayList<Player> getPlayers()
    {
        return players;
    }

    public Player getPlayer(int id)
    {
        return players.get(id);
    }

    public int getPlayersAlive()
    {
        return players.size();
    }

    public ArrayList<Bomb> getBombs()
    {
        return bombs;
    }

    public ArrayList<Entity> getEntities()
    {
        return entities;
    }

    public ArrayList<Item> getItems()
    {
        return items;
    }

    public ArrayList<Stone> getStones()
    {
        return stones;
    }

    public ArrayList<Blast> getBlasts()
    {
        return blasts;
    }

    public boolean isPlaying()
    {
        return playing;
    }

    public void setPlaying(boolean playing)
    {
        this.playing = playing;
    }

    public boolean isPausing()
    {
        return pausing;
    }

    public void setPausing(boolean pausing)
    {
        this.pausing = pausing;
    }
}


