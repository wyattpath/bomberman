package com.wyatt92.games.model.states;

import com.wyatt92.games.controller.Handler;
import com.wyatt92.games.model.Model;

import java.awt.*;

public abstract class State implements Model
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

    @Override
    public abstract void update();

    @Override
    public abstract void draw(Graphics g);
}
