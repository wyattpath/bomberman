package com.wyatt92.games.model;

import java.awt.*;

public class Player extends Pawn {

    private boolean isMoving = false;

    public Player(float x, float y) {
        super(x, y);

    }

    public void update() {

    }


    public void move(String direction) {
        if(!isMoving){
            isMoving =true;
            if ("up".equals(direction)) {
                y--;

            } else if ("down".equals(direction)) {
                y++;

            } else if ("left".equals(direction)) {
                x--;

            } else if ("right".equals(direction)) {
                x++;

            }
        }
        isMoving = false;
    }

    public void draw(Graphics g) {
        g.drawImage(Assets.player, (int) x ,(int) y, null);
    }
}
