package com.wyatt92.games.model;

import java.awt.*;

public class Player extends Pawn {
    public Player(float x, float y) {
        super(x, y);
    }

    public void update() {

    }

    public void draw(Graphics g) {
        g.drawImage(Assets.player, (int) x ,(int) y, null);
    }
}
