package com.wyatt92.games.view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel {

    // The following "off" vars are used for the off-screen double-buffered image.
    private Dimension dimOff;
    private Image imgOff;
    private Graphics gOff;

    private GameFrame gmf;

    private String title;
    private int frameWidth;
    private int frameHeight;
    private int panelHeight;
    private final int menuBarHeight = 20;


    /**
     * Constructor
     *
     * @param title
     * @param frameWidth
     * @param frameHeight
     */
    public GamePanel(String title, int frameWidth, int frameHeight) {
        this.title = title;
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.panelHeight = frameHeight - menuBarHeight;

        configureGameFrame();
        configureGamePanel();
        makeVisible();
        this.setFocusable(true);

    }

    private void configureGameFrame() {
        gmf = new GameFrame();
        gmf.getContentPane().add(this);
        gmf.pack();
        Graphics g = getGraphics();
        gmf.setSize(frameWidth, frameHeight);
        gmf.setTitle(title);
        gmf.setResizable(false);
        gmf.setPreferredSize(new Dimension(frameWidth, frameHeight));
        gmf.setDefaultCloseOperation(GameFrame.EXIT_ON_CLOSE);
        gmf.setLocationRelativeTo(null);
        gmf.setVisible(true);
    }

    // METHODS

    private void configureGamePanel() {

        // Set a LayoutManager
        this.setBackground(Color.WHITE);
        this.setOpaque(true);
        this.setPreferredSize(new Dimension(frameWidth, frameHeight));
        this.setMaximumSize(new Dimension(frameWidth, frameHeight));
        this.setMinimumSize(new Dimension(frameWidth, frameHeight));


    }

    private void makeVisible() {


    }

    public void update(Graphics g) {

        // create graphics if null
        if (gOff == null || frameWidth != dimOff.width || frameHeight != dimOff.height) {
            dimOff = new Dimension(frameWidth, frameHeight);
            imgOff = createImage(frameWidth, frameHeight);
            gOff = imgOff.getGraphics();
        }

        // Fill in background with black
        gOff.setColor(Color.BLACK);
        gOff.fillRect(0, 0, frameWidth, frameHeight);
        gOff.setColor(Color.RED);
        gOff.drawRect(50,50,100,100);
        g.drawImage(imgOff, 0, 0, this);
    }

    public void render(Graphics g) {

    }


    // GETTERS AND SETTERS
    public GamePanel getGamePanel() {
        return this;
    }

    public Image getBufferedImage() {
        return imgOff;
    }


}
