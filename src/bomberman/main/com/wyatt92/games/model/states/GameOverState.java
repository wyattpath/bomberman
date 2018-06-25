package com.wyatt92.games.model.states;



import com.wyatt92.games.model.Assets;
import com.wyatt92.games.model.Game;
import com.wyatt92.games.model.ui.UIManager;
import com.wyatt92.games.model.utils.Animation;

import javax.sound.sampled.Clip;
import java.awt.*;

public class GameOverState extends State
{
    private UIManager uiManager;
    private Game game;
    private Animation animGameOver;

    public GameOverState(Game game, UIManager uiManager)
    {
        super(game);
        this.game = game;
        this.uiManager = uiManager;
        animGameOver = new Animation(10000, Assets.gameOver);
        animGameOver.setRandom(true);
    }

    @Override
    public void update()
    {
        animGameOver.update();
        uiManager.update();
    }

    @Override
    public void draw(Graphics g)
    {
        if(game != null)
        {
            g.setColor(Color.BLACK);
            g.fillRect(0,0, game.getWidth(), game.getHeight());
            g.drawImage(animGameOver.getCurrentFrame(), 0,0, game.getWidth(), game.getHeight(), null);
//            g.setFont(new Font("Unispace", Font.BOLD, 60));
//            g.drawString("Game Over",world.getWidth()/3, world.getHeight()/2);
            uiManager.draw(g);
            g.setColor(Color.BLUE);
            g.setFont(new Font("Unispace", Font.BOLD, 40));
            g.drawString("The Winner is Player " + getWinner(), game.getWidth()/4, 400);
        }


    }

    @Override
    protected void playLoopMusic()
    {
        Assets.gameOver_bgMusic.setFramePosition(0);
        Assets.gameOver_bgMusic.start();
        Assets.gameOver_bgMusic.loop(Clip.LOOP_CONTINUOUSLY);
    }

    @Override
    protected void stopLoopMusic()
    {
        Assets.gameOver_bgMusic.stop();
    }
}
