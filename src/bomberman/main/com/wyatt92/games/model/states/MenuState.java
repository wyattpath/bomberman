package com.wyatt92.games.model.states;

import com.wyatt92.games.controller.Handler;

import java.awt.*;

public class MenuState extends State
{
    Rectangle start;

    public MenuState(Handler handler){
        super(handler);
        start = new Rectangle(100,100);

        start.x = 200;
        start.y = 200;
    }

    @Override
    public void update()
    {
        if(handler.getMouseManager().isLeftPressed() && start.contains(handler.getMouseManager().getMousePosition())){
            State.setCurrentState(handler.getGameState());
        }
    }

    @Override
    public void draw(Graphics g)
    {
        g.setColor(Color.RED);
        g.fillRect(start.x,start.y,start.width,start.height);
        g.fillRect(
                handler.getMouseManager().getMouseX(),
                handler.getMouseManager().getMouseY(),
                8,
                8);
    }


}
