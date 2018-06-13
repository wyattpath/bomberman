package com.wyatt92.games.model.states;

import java.awt.*;

public abstract class State
{
    private static State currentState = null;

    public static State getCurrentState()
    {
        return currentState;
    }

    public static void setCurrentState(State state) {
        currentState = state;
    }

    public abstract void update();

    public abstract void draw(Graphics g);
}
