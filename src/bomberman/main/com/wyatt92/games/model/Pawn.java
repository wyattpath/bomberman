package com.wyatt92.games.model;

import java.awt.*;

/**
 * The Pawn is the physical representation of a player or an AI within the world.
 *
 */
public abstract class Pawn extends Actor{

    protected int health;

    public Pawn(float x, float y) {
        super(x, y);
    }
}
