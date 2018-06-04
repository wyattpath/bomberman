package com.wyatt92.games.model;

/**
 * The Pawn is the physical representation of a player or an AI within the world.
 *
 */
public abstract class Pawn extends Actor{

    protected int health;
    private enum Direction {UP, DOWN, LEFT, RIGHT};
    private Direction mDirection;

    public Pawn(float x, float y) {
        super(x, y);
    }

    public Direction getDirection() {
        return mDirection;
    }

    public void setDirection(Direction direction) {
        mDirection = direction;
    }
}
