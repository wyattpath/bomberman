package com.wyatt92.games.model;

import java.awt.*;
import java.util.Observer;

/**
 *  Base Interface of all models. Updates and draws objects.
 */
public interface Model
{
    void update();

    void draw(Graphics g);

}
