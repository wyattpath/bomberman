package com.wyatt92.games.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class GameFrame extends JFrame {

//    private JPanel contentPane;
    private BorderLayout borderLayout1 = new BorderLayout();
    private String title;
    private int width, height;

    public GameFrame(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        this.setTitle(title);
        enableEvents(AWTEvent.WINDOW_EVENT_MASK);
        try {
            initGameFrame();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Component initialization
    private void initGameFrame() throws Exception {
//        contentPane = (JPanel) this.getContentPane();
//        contentPane.setLayout(borderLayout1);
//        this.getContentPane().add(this);
        this.pack();
        this.setSize(width, height);
        this.setTitle(title);
        this.setResizable(false);
        this.setPreferredSize(new Dimension(width, height));
        this.setMinimumSize(new Dimension(width, height));
        this.setDefaultCloseOperation(GameFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    //Overridden so we can exit when window is closed
    protected void processWindowEvent(WindowEvent e) {
        super.processWindowEvent(e);
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            System.exit(0);
        }
    }
}
