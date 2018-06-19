package com.wyatt92.games.view;

import com.wyatt92.games.model.*;
import com.wyatt92.games.model.states.State;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

public class GamePanel extends JPanel {

    // The following "off" vars are used for the off-screen double-buffered image.
    private Dimension dimOff;
    private BufferedImage imgOff;
    private Graphics gOff;

    private GameFrame gmf;

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
        init();
        configureGameFrame();
        configureGamePanel();
    }

    private void init() {
        this.gmf = new GameFrame(title, frameWidth, frameHeight);
    }

    private void configureGameFrame() {
        gmf.getContentPane().add(this);
        gmf.pack();
        gmf.setSize(frameWidth, frameHeight);
        gmf.setTitle(title);
        gmf.setResizable(false);
        gmf.setPreferredSize(new Dimension(frameWidth, frameHeight));
        gmf.setDefaultCloseOperation(GameFrame.EXIT_ON_CLOSE);
        gmf.setLocationRelativeTo(null);
        gmf.setVisible(true);
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

    // METHODS
    public void update() {

    }



    public void draw(Graphics g) {
        if(g == null)
        {
            createImage(WIDTH, HEIGHT);
            return;
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

//        Graphics2D g2d = (Graphics2D) g.create();
//        g.create();
        if(State.getCurrentState() != null){
            State.getCurrentState().draw(g);
        }

        g.dispose();
    }
}
