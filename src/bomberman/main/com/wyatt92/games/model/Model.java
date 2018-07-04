package com.wyatt92.games.model;

import com.wyatt92.games.model.entities.*;
import com.wyatt92.games.model.entities.Item;
import com.wyatt92.games.model.tiles.Tile;

import java.util.ArrayList;

/**
 *  Base Interface of all models. Updates entities.
 */
public interface Model
{
    /**
     * Updates entities
     */
    void update();

    /**
     * Loads world map
     *
     * @param path path to world file as .txt
     */
    void loadWorld(String path);

    void resetWorld();

    /**
     * Moves specified player to the left.
     *
     * @param id player id
     */
    void moveLeft(int id);

    /** Moves specified player to the right.
     *
     * @param id player id
     */
    void moveRight(int id);

    /**
     * Moves specified player up.
     *
     * @param id player id
     */
    void moveUp(int id);

    /**
     * Moves specified player down.
     *
     * @param id
     */
    void moveDown(int id);

    /**
     * Places bomb at player position.
     *
     * @param id player id.
     */
    void placeBomb(int id);

    /**
     *  get Tile
     * @param x
     * @param y
     * @return
     */
    Tile getTile(int x, int y);

    // GETTERS and SETTERS

    int getWidth();

    int getHeight();

    int[][] getTiles();

    int getPlayerCount();

    boolean isGameOver();


    ArrayList<Player> getPlayers();

    Player getPlayer(int id);

    void setGameOver(boolean b);


    int getPlayerAlive();

    int getPlayersAlive();

    ArrayList<Blast> getBlasts();

    ArrayList<Bomb> getBombs();

    ArrayList<Entity> getEntities();

    ArrayList<Item> getItems();

    ArrayList<Stone> getStones();

    int getTileRows();

    int getTileColumns();

}
