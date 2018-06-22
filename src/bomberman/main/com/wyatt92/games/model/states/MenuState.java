package com.wyatt92.games.model.states;

import com.wyatt92.games.model.ui.Clicker;
import com.wyatt92.games.controller.Handler;
import com.wyatt92.games.model.Assets;
import com.wyatt92.games.model.ui.UIImageButton;
import com.wyatt92.games.model.ui.UIManager;

import java.awt.*;

public class MenuState extends State
{
    private UIManager uiManager;

    public MenuState(Handler handler){
        super(handler);

        uiManager = new UIManager(handler);
        handler.getMouseManager().setUiManager(uiManager);
        uiManager.addObject(new UIImageButton(400, 400, 228, 35, Assets.btn_start, new Clicker(){
            @Override
            public void onClick()
            {
                handler.getMouseManager().setUiManager(null);
                State.setCurrentState(handler.getGameState());
            }
        }));
        uiManager.addObject(new UIImageButton(400, 435, 228, 35, Assets.btn_quit, new Clicker(){
            @Override
            public void onClick()
            {
                System.exit(0);
            }
        }));
    }

    @Override
    public void update()
    {
        uiManager.update();
    }

    @Override
    public void draw(Graphics g)
    {
//        g.setColor(Color.RED);
//        g.fillRect(start.x,start.y,start.width,start.height);
//        g.fillRect(
//                handler.getMouseManager().getMouseX(),
//                handler.getMouseManager().getMouseY(),
//                8,
//                8);
        g.setColor(Color.BLUE);
        g.fillRect(0,0,handler.getWidth(), handler.getWidth());
        uiManager.draw(g);
    }


}
