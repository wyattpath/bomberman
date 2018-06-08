package com.wyatt92.games.model;

import com.wyatt92.games.controller.Game;

import java.awt.*;

public class Player extends Pawn {

//    private boolean isMoving = false;
    private Game game;

    public Player(Game game,  float x, float y) {
        super(x, y);
        this.game = game;

    }

    @Override
    public void update() {
        if(game.getKeyManager().UP) y--;
        if(game.getKeyManager().DOWN) y++;
        if(game.getKeyManager().LEFT) x--;
        if(game.getKeyManager().RIGHT) x++;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(Assets.player, (int) x ,(int) y, null);
    }
}
