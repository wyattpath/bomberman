package com.wyatt92.games.model.states;


import com.wyatt92.games.model.Model;
import com.wyatt92.games.model.World;

import java.awt.*;

public abstract class State implements Model
{
    private static State currentState = null;

    public State() {
    }

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

    @Override
    public abstract void update();

    @Override
    public abstract void draw(Graphics g);

    protected abstract void playLoopMusic();

    protected abstract void stopLoopMusic();
}
