package com.wyatt92.games.model.states;

import com.wyatt92.games.model.Assets;
import com.wyatt92.games.model.World;

import javax.sound.sampled.Clip;
import java.awt.*;

public class GameState extends State
{


    public GameState(World world) {
        this.world = world;
    }

    @Override
    public void update()
    {
        world.update();

    }

    @Override
    public void draw(Graphics g)
    {
        world.draw(g);
    }

    @Override
    protected void playLoopMusic()
    {
        Assets.game_bgMusic.start();
        Assets.game_bgMusic.loop(Clip.LOOP_CONTINUOUSLY);
    }

    @Override
    protected void stopLoopMusic()
    {
        Assets.game_bgMusic.stop();
    }
}
