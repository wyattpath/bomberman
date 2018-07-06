package com.wyatt92.games.view;

import javax.swing.*;



public class StartButton extends JButton
{
    Icon startIcon, startIconEntered;

    StartButton(){
        startIcon = new ImageIcon(Assets.btn_start[0]);
        startIconEntered = new ImageIcon(Assets.btn_start[1]);
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
