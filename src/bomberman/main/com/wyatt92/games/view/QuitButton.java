package com.wyatt92.games.view;

import javax.swing.*;

class QuitButton extends JButton
{
    private Icon  quitIcon, quitIconEntered;

    QuitButton(){
        quitIcon = new ImageIcon(Assets.btn_quit[0]);
        quitIconEntered = new ImageIcon(Assets.btn_quit[1]);
        setRolloverIcon(quitIconEntered);
        setIcon(quitIcon);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setOpaque(false);
    }

    public void setQuitEntered(){
        setIcon(quitIconEntered);
    }

    public void setQuitExited() {
        setIcon(quitIcon);
    }
}
