package com.wyatt92.games.view;

import javax.swing.*;
import java.awt.*;

public abstract class ViewPanel extends JPanel
{

    public abstract void update();

    public abstract void draw(Graphics g);
}
