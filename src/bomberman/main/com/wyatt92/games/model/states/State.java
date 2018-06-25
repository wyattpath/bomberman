package com.wyatt92.games.model.states;


import com.wyatt92.games.model.Model;
import com.wyatt92.games.model.Game;

import java.awt.*;

/**
 * This class sets the State that will be updated and drawn.
 * Plays different background music depending on the state.
 */
public abstract class State implements Model
{
    private static State currentState = null;
    private static State gameState;
    protected Game game;
    private int winner;

    public State(Game game) {
        this.game = game;
    }

    protected State()
    {
    }


    protected abstract void playLoopMusic();

    protected abstract void stopLoopMusic();

    @Override
    public abstract void update();

    @Override
    public abstract void draw(Graphics g);

    public static State getCurrentState()
    {
        return currentState;
    }

    public static void setCurrentState(State state) {
        if(currentState!=null)
        {
            currentState.stopLoopMusic();
        }
        currentState = state;
        currentState.playLoopMusic();
    }

    public static State getGameState()
    {
            return gameState;
    }

    public static void setGameState(State gameState)
    {
        State.gameState = gameState;
    }

    public void setWinner(int winner)
    {
        this.winner = winner;
    }

    public int getWinner()
    {
        return winner;
    }
}
