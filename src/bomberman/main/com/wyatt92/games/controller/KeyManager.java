package com.wyatt92.games.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

    private boolean[] keys, justPressed, cantPress;
    public boolean UP, DOWN, LEFT, RIGHT;
    public boolean aUP, aDOWN, aLEFT, aRIGHT;
    public boolean SPACE;

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

        UP = keys[KeyEvent.VK_W];
        DOWN = keys[KeyEvent.VK_S];
        LEFT = keys[KeyEvent.VK_A];
        RIGHT = keys[KeyEvent.VK_D];
        SPACE = keys[KeyEvent.VK_SPACE];

        aUP = keys[KeyEvent.VK_UP];
        aDOWN = keys[KeyEvent.VK_DOWN];
        aLEFT = keys[KeyEvent.VK_LEFT];
        aRIGHT = keys[KeyEvent.VK_RIGHT];
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
