package com.wyatt92.games.view;

import com.wyatt92.games.model.*;

import java.awt.*;

/**
 * Creates a GameFrame and a GamePanel. Configures the GamePanel
 * Represents the visualization of the data that model contains.
 */
public class GamePanel extends ViewPanel {

    /**
     * Constructor
     *
     */
    public GamePanel(Model model) {
        super(model);
        configureGamePanel();
    }

    private void configureGamePanel() {
        // Set a LayoutManager
        this.setBackground(Color.WHITE);
        this.setOpaque(true);
        this.setFocusable(true);
        this.setDoubleBuffered(true);
        requestFocus();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        model.draw(g);
        g.dispose();
    }
}
