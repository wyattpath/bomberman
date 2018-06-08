package com.wyatt92.games.view;

import com.wyatt92.games.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

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
        Assets.init();
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
//         create graphics if null
//        if (gOff == null) {
//            dimOff = new Dimension(frameWidth, frameHeight);
//            imgOff = createImage(frameWidth, frameHeight);
//            gOff = imgOff.getGraphics();
//            return;
        if(g == null)
        {
            createImage(WIDTH, HEIGHT);
            return;
        }
        repaint();
                    // Fill in background with black
//        g.setColor(Color.BLACK);
//        g.fillRect(0, 0, frameWidth, frameHeight);
//        gOff.setColor(Color.RED);
//        gOff.drawRect(50,50,100,100);
//        gOff.drawImage(Assets.dirt, 0 , 10, null);
//        if(State.getCurrentState() != null){
//
//            State.getCurrentState().draw(gOff);
//        }
//        g.drawImage(imgOff,0,0,null);
//        imgOff.getGraphics();
//        g.drawImage(imgOff, 0, 0, this);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponents(g);

        Graphics2D g2d = (Graphics2D) g.create();
        if(State.getCurrentState() != null){
            g2d.clearRect(0,0,frameWidth,frameHeight);
            State.getCurrentState().draw(g2d);
        }

//        g2d.setColor(Color.YELLOW);
//        g2d.fillRect(0,0,WIDTH,HEIGHT);
        g2d.dispose();
    }


}
