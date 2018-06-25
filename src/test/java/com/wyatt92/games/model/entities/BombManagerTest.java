package com.wyatt92.games.model.entities;

import com.wyatt92.games.model.Game;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BombManagerTest
{

    @Test
    void addBomb()
    {
        Game game = new Game("world1.txt");
        Bomb b = new Bomb(game, 3, 4,2);
        assertTrue(game.getBombManager().getBombs().isEmpty());
        game.getBombManager().addBomb(b);
        assertTrue(game.getBombManager().getBombs().contains(b));

    }
}