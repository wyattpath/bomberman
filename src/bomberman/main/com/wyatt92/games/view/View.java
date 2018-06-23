package com.wyatt92.games.view;

public class View
{
    private GameFrame gmf;
    private GamePanel gamePanel;

    public View(String title, int frameWidth, int frameHeight) {
        gmf = new GameFrame(title, frameWidth, frameHeight);
        gamePanel = new GamePanel(title, frameWidth, frameHeight);
        gmf.getContentPane().add(gamePanel);
        gmf.pack();
        gmf.setVisible(true);
    }

    public void repaint(){
        gamePanel.repaint();
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }
}
