package com.wyatt92.games.view;

import com.wyatt92.games.model.*;
import com.wyatt92.games.model.states.State;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

/**
 * Creates a GameFrame and a GamePanel. Configures the GamePanel
 * Represents the visualization of the data that model contains.
 */
public class GamePanel extends JPanel {

    private String title;
    private int frameWidth;
    private int frameHeight;

    /**
     * Constructor
     *
     * @param title game title
     * @param frameWidth width of frame
     * @param frameHeight height of frame
     */
    public GamePanel(String title, int frameWidth, int frameHeight) {
        this.title = title;
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        configureGamePanel();
    }

    private void configureGamePanel() {
        // Set a LayoutManager
        this.setBackground(Color.WHITE);
        this.setOpaque(true);
        this.setSize(frameWidth, frameHeight);
        this.setPreferredSize(new Dimension(frameWidth, frameHeight));
        this.setMaximumSize(new Dimension(frameWidth, frameHeight));
        this.setMinimumSize(new Dimension(frameWidth, frameHeight));
        this.setFocusable(true);
        this.setDoubleBuffered(true);
        requestFocus();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(State.getCurrentState() != null){
            State.getCurrentState().draw(g);
        }
        g.dispose();
    }
}
