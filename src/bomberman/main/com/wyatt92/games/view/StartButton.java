package com.wyatt92.games.view;

import javax.swing.*;



class StartButton extends JButton
{
    private final Icon startIcon;
    private final Icon startIconEntered;

    StartButton(){
        startIcon = new ImageIcon(SpriteLibrary.btn_start[0]);
        startIconEntered = new ImageIcon(SpriteLibrary.btn_start[1]);
        setIcon(startIcon);
        setRolloverIcon(startIconEntered);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setOpaque(false);
    }

    public void setStartEntered(){
        setIcon(startIconEntered);
    }
    public void setStartExited() {
        setIcon(startIcon);
    }
}
