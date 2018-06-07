package com.wyatt92.games.view;

import com.wyatt92.games.model.Assets;

public class View
{
    private final GameFrame gameFrame;
    private ViewPanel currentPanel = null;

    public View(GameFrame gameframe) {
        this.gameFrame = gameframe;
        initView();
    }

    private void initView() {
//        gameFrame.getContentPane().add(gamePanel);

        Assets.init();
    }

    public void setPanel(ViewPanel panel) {
        currentPanel = panel;
        gameFrame.setContentPane(panel);
        gameFrame.revalidate();
    }

    public ViewPanel getPanel(){
        return currentPanel;
    }


}
