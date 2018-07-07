package com.wyatt92.games.controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Registers Keys.
 */
class GameKeyListener extends KeyAdapter{

    private final boolean[] keys;
    private boolean[] justPressed;
    private boolean[] cantPress;
    public boolean W, S, A, D;
    public boolean SPACE;
    public boolean UP, DOWN, LEFT, RIGHT;
    public boolean CTRL;



    public GameKeyListener() {
        keys = new boolean[256];
        justPressed = new boolean[keys.length];
        cantPress = new boolean[keys.length];

    }


    private boolean keyJustPressed(int keyCode){
        if(keyCode >= 0 && keyCode < keys.length){
            return justPressed[keyCode];
        }
        return false;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() >= 0 && e.getKeyCode() < keys.length)
        {
            keys[e.getKeyCode()] = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        if(e.getKeyCode() >= 0 && e.getKeyCode() < keys.length)
        {
            keys[e.getKeyCode()] = false;
        }
    }

    void update(){

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


        UP = keys[KeyEvent.VK_UP];
        DOWN = keys[KeyEvent.VK_DOWN];
        LEFT = keys[KeyEvent.VK_LEFT];
        RIGHT = keys[KeyEvent.VK_RIGHT];


        SPACE = keyJustPressed(KeyEvent.VK_SPACE);
        CTRL = keyJustPressed(KeyEvent.VK_CONTROL);
    }
}
