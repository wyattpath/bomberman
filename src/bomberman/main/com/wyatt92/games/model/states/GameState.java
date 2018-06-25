package com.wyatt92.games.model.states;

import com.wyatt92.games.model.Assets;
import com.wyatt92.games.model.Game;

import javax.sound.sampled.Clip;
import java.awt.*;

public class GameState extends State
{


    public GameState(Game game) {
        this.game = game;
    }

    @Override
    public void update()
    {
        game.update();

    }

    @Override
    public void draw(Graphics g)
    {
        game.draw(g);
    }

    @Override
    protected void playLoopMusic()
    {
        Assets.game_bgMusic.setFramePosition(0);
        Assets.game_bgMusic.start();
        Assets.game_bgMusic.loop(Clip.LOOP_CONTINUOUSLY);
    }

    @Override
    protected void stopLoopMusic()
    {
        Assets.game_bgMusic.stop();
    }
}
