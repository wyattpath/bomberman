package com.wyatt92.games.view;

import com.wyatt92.games.model.Model;

import javax.swing.*;
import java.awt.*;

public class View
{
    private JFrame frame;
    private JPanel panel;
    private static final int FRAMEWIDTH = 13 * 64;
    private static final int FRAMEHEIGHT = 13 * 64 + 32;
    private static final String TITLE = "Bomberman";
    private Model model;

    public View(Model model) {
        this.model = model;
        frame = new JFrame();
        frame.setPreferredSize(new Dimension(FRAMEWIDTH, FRAMEHEIGHT));
    }

    public void repaint(){
        frame.repaint();
    }

    public void makeVisible() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setFocusable(true);
		frame.setVisible(true);
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
        frame.setContentPane(panel);
        frame.revalidate();
    }

    public JFrame getFrame()
    {
        return frame;
    }

    public void setFrame(GameFrame frame)
    {
        this.frame = frame;
    }
}
