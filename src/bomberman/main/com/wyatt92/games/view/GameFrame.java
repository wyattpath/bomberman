package com.wyatt92.games.view;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    private JPanel contentPane;

    public GameFrame() {
        contentPane = (JPanel) this.getContentPane();
        contentPane.setLayout(new BorderLayout());
    }
}
