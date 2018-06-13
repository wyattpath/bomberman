package com.wyatt92.games.model.states;

import com.wyatt92.games.controller.Handler;

import java.awt.*;

public class MenuState extends State
{
    public MenuState(Handler handler){
        super(handler);

    }

    @Override
    public void update()
    {
        if(handler.getMouseManager().isLeftPressed()){
            State.setCurrentState(handler.getGameState());
        }
    }

    @Override
    public void draw(Graphics g)
    {
        g.setColor(Color.RED);
        g.fillRect(
                handler.getMouseManager().getMouseX(),
                handler.getMouseManager().getMouseY(),
                8,
                8);
    }
}
