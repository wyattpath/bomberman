package com.wyatt92.games.model;

import com.wyatt92.games.model.entities.*;
import com.wyatt92.games.model.entities.Item;
import com.wyatt92.games.model.tiles.Tile;

import java.util.ArrayList;

/**
 *  Base Interface of all models. Updates and draws objects.
 */
public interface Model
{
    /**
     * Updates timer for animation and player bombCooldown
     */
    void update();


    void moveLeft(int id);

    void moveRight(int id);

    void moveUp(int id);

    void moveDown(int id);

    void placeBomb(int id);

    void resetWorld();

    Tile getTile(int x, int y);

    void loadWorld(String path);

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
