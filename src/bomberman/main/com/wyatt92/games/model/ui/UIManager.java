package com.wyatt92.games.model.ui;

import com.wyatt92.games.model.Model;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class UIManager implements Model
{
    private ArrayList<UIObject> objects;

    public UIManager() {
        objects = new ArrayList<UIObject>();
    }

    @Override
    public void update(){
        for(UIObject o : objects){
            o.update();
        }
    }

    @Override
    public void draw(Graphics g) {
        for(UIObject o : objects){
            o.draw(g);
        }
    }

    public void onMouseMove(MouseEvent e) {
        for(UIObject o : objects){
            o.onMouseMove(e);
        }
    }

    public void onMouseRelease(MouseEvent e) {
        for(UIObject o : objects){
            o.onMouseRelease(e);
        }
    }

    public void addObject(UIObject o) {
        objects.add(o);
    }

    public void removeObject(UIObject o) {
        objects.remove(o);
    }

    // GETTERS and SETTERS


    public ArrayList<UIObject> getObjects()
    {
        return objects;
    }

    public void setObjects(ArrayList<UIObject> objects)
    {
        this.objects = objects;
    }
}
