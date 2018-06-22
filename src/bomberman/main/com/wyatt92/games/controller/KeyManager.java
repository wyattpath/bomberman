package com.wyatt92.games.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

public class KeyManager implements KeyListener {

    private boolean[] keys, justPressed, cantPress;
    public boolean W, S, A, D;
    public boolean SPACE;
    public boolean UP, DOWN, LEFT, RIGHT;
    public boolean CTRL;

    public KeyManager() {
        keys = new boolean[256];
        justPressed = new boolean[keys.length];
        cantPress = new boolean[keys.length];
    }

    public void update(){
        for(int i = 0; i < keys.length; i++){
            if(cantPress[i] && !keys[i]){
                cantPress[i] = false;
            } else if(justPressed[i]){
                cantPress[i] = true;
                justPressed[i] = false;
            }
            if(!cantPress[i] && keys[i]){
                justPressed[i] = true;
            }
        }

        W = keys[KeyEvent.VK_W];
        S = keys[KeyEvent.VK_S];
        A = keys[KeyEvent.VK_A];
        D = keys[KeyEvent.VK_D];
        SPACE = keyJustPressed(KeyEvent.VK_SPACE);

        UP = keys[KeyEvent.VK_UP];
        DOWN = keys[KeyEvent.VK_DOWN];
        LEFT = keys[KeyEvent.VK_LEFT];
        RIGHT = keys[KeyEvent.VK_RIGHT];
        CTRL = keyJustPressed(KeyEvent.VK_CONTROL);
    }

    public boolean keyJustPressed(int keyCode){
        if(keyCode >= 0 && keyCode < keys.length){
            return justPressed[keyCode];
        }
        return false;
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() >= 0 && e.getKeyCode() < keys.length)
            keys[e.getKeyCode()] = true;

    }

    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() >= 0 && e.getKeyCode() < keys.length)
            keys[e.getKeyCode()] = false;
    }
}
