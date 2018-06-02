package com.wyatt92.games.view;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    // The following "off" vars are used for the off-screen double-buffered image.
    private Dimension dimOff;
    private Image imgOff;
    private Graphics grpOff;

    private GameFrame frame;

    private String title;
    private Dimension dim;

    Graphics g;

    /**
     * Constructor
     *
     * @param dim
     */
    public GamePanel(String title, Dimension dim) {
        this.title = title;
        this.dim = dim;
        configureGameFrame();
        configureGamePanel();
        makeVisible();

    }

    private void configureGameFrame() {
        this.frame = new GameFrame();
        frame.setSize(dim.width, dim.height);
        frame.setDefaultCloseOperation(GameFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);


    }

    // METHODS

    private void configureGamePanel() {
        // Set a LayoutManager
        this.setPreferredSize(dim);
        this.setMaximumSize(dim);
        this.setMinimumSize(dim);
        this.setLayout(new BorderLayout(3, 3));
        this.setSize(dim.width, dim.height);

    }

    private void makeVisible() {
        frame.add(this);
        frame.pack();
        frame.setVisible(true);

    }


    // GETTERS AND SETTERS






}
