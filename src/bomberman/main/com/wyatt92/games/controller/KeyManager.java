package com.wyatt92.games.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

    private boolean[] keys;
    public boolean UP, DOWN, LEFT, RIGHT;

    public KeyManager() {
        keys = new boolean[256];
    }

    public void update(){
        UP = keys[KeyEvent.VK_W];
        DOWN = keys[KeyEvent.VK_S];
        LEFT = keys[KeyEvent.VK_A];
        RIGHT = keys[KeyEvent.VK_D];
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;

    }

    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }
}
