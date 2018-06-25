package com.wyatt92.games.model.entities;

import com.wyatt92.games.model.Game;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class EntityManagerTest
{
    private EntityManager entityManager;
    Game game;

    EntityManagerTest() {
        game = new Game("world1.txt");
        entityManager = new EntityManager(game);
    }

    @Test
    void addEntity()
    {
        Stone stone = new Stone(game,3 ,4 );
        game.getEntityManager().addEntity(stone);
        assertTrue(game.getEntityManager().getEntities().contains(stone));
    }

    @Test
    void removeEntity()
    {
        Stone stone = new Stone(game,3 ,4 );
        game.getEntityManager().addEntity(stone);
        game.getEntityManager().removeEntity(stone);
        assertFalse(game.getEntityManager().getEntities().contains(stone));
    }

    @Test
    void getWorld()
    {
        assertEquals(game, entityManager.getGame());
    }

    @Test
    void setWorld()
    {
        Game newGame = new Game("world2.txt");
        entityManager.setGame(newGame);
        assertEquals(newGame, entityManager.getGame());
    }

    @Test
    void addPlayer()
    {
        Player player = new Player(game, 3, 4, 0);
        entityManager.addPlayer(player);
        assertTrue(entityManager.getPlayers().contains(player));
        assertEquals(player,entityManager.getPlayer(0));
    }

    @Test
    void getPlayers()
    {
        assertEquals(0,entityManager.getPlayers().size());
        Player player = new Player(game, 3, 4, 0);
        entityManager.addPlayer(player);
        assertEquals(1,entityManager.getPlayers().size());
    }

    @Test
    void getPlayer()
    {
        Player player = new Player(game, 3, 4, 0);
        Player player2 = new Player(game, 10, 4, 1);
        entityManager.addPlayer(player);
        entityManager.addPlayer(player2);
        assertEquals(player,entityManager.getPlayer(0));
        assertEquals(player2,entityManager.getPlayer(1));
    }

    @Test
    void getEntities()
    {
        Stone stone = new Stone(game,3 ,4 );
        assertTrue(entityManager.getEntities().isEmpty());
        assertFalse(entityManager.getEntities().contains(stone));
        entityManager.addEntity(stone);
        assertTrue(entityManager.getEntities().contains(stone));
    }

    @Test
    void setEntities()
    {
        assertTrue(entityManager.getEntities().isEmpty());

        ArrayList<Entity> entities = new ArrayList<>();
        Stone stone1 = new Stone(game,30 ,4 );
        Stone stone2 = new Stone(game,40 ,4 );
        Stone stone3 = new Stone(game,70 ,4 );
        entities.add(stone1);
        entities.add(stone2);
        entities.add(stone3);

        entityManager.setEntities(entities);
        assertTrue(entityManager.getEntities().contains(stone1));
        assertTrue(entityManager.getEntities().contains(stone2));
        assertTrue(entityManager.getEntities().contains(stone3));

    }

    @Test
    void getPlayerCount() {
        assertEquals(0, entityManager.getPlayerCount());
        Player p = new Player(game, 0, 0, 0);
        entityManager.addPlayer(p);
        assertEquals(1, entityManager.getPlayerCount());
    }
}