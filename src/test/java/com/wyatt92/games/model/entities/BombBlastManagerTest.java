package com.wyatt92.games.model.entities;

import com.wyatt92.games.model.Game;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BombBlastManagerTest
{
    private Game game;
    private BombBlastManager bombBlastManager;

    BombBlastManagerTest() {
        game = new Game("world1.txt");
    }

    @Test
    void getBlasts()
    {
        game.getBombBlastManager().getBlasts();
        assertTrue(game.getBombBlastManager().getBlasts().isEmpty());
    }

    @Test
    void addBlast()
    {
        Blast b = new Blast(game, 3, 54);
        assertFalse(game.getBombBlastManager().getBlasts().contains(b));
        game.getBombBlastManager().addBlast(b);
        assertTrue(game.getBombBlastManager().getBlasts().contains(b));
    }
}