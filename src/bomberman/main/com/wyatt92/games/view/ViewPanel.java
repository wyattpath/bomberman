package com.wyatt92.games.view;

import com.wyatt92.games.model.Model;

import javax.swing.*;
import java.awt.*;

public class ViewPanel extends JPanel
{
    protected Model model;

    public ViewPanel(Model model){
        this.model = model;

        this.setBackground(Color.WHITE);
        this.setOpaque(true);
        this.setFocusable(true);
        this.setDoubleBuffered(true);
        requestFocus();
    }

}
