package com.wyatt92.games.view;

import javax.swing.*;

public class QuitButton extends JButton
{
    private final Icon  quitIcon;
    private final Icon quitIconEntered;

    QuitButton(){
        quitIcon = new ImageIcon(SpriteLibrary.btn_quit[0]);
        quitIconEntered = new ImageIcon(SpriteLibrary.btn_quit[1]);
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
