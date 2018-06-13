package com.wyatt92.games.model.states;

import com.wyatt92.games.controller.Game;
import com.wyatt92.games.controller.Handler;

import java.awt.*;

public abstract class State
{
    private static State currentState = null;

    protected Handler handler;

    public State(Handler handler) {
        this.handler = handler;
    }

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
