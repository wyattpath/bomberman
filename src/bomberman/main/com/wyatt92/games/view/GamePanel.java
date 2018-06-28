package com.wyatt92.games.view;

import com.wyatt92.games.model.*;
import com.wyatt92.games.model.entities.*;
import com.wyatt92.games.model.entities.Item;
import com.wyatt92.games.model.tiles.Tile;

import javax.swing.*;
import java.awt.*;

/**
 * Creates a GameFrame and a GamePanel. Configures the GamePanel
 * Represents the visualization of the data that model contains.
 */
public class GamePanel extends JPanel
{
    private Model model;

    /**
     * Constructor
     *
     */
    public GamePanel(Model model) {
        this.model = model;
        configureGamePanel();
    }

    private void configureGamePanel() {
        // Set a LayoutManager
        this.setBackground(Color.WHITE);
        this.setOpaque(true);
        this.setFocusable(true);
        this.setDoubleBuffered(true);
        requestFocus();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for(int x = 0; x < model.getTileRows(); x++){
            for(int y = 0; y < model.getTileColumns(); y++){
                model.getTile(x,y).setPosition(x*Tile.TILEWIDTH, y*Tile.TILEHEIGHT);
                model.getTile(x,y).draw(g);
            }
        }

        for(Blast b : model.getBlasts()){
            b.draw(g);
        }
        for(Bomb i : model.getBombs())
            i.draw(g);

        for(Stone stone : model.getStones())
            stone.draw(g);

        for(Player p : model.getPlayers())
            p.draw(g);

        for(Item i: model.getItems())
            i.draw(g);
        g.dispose();
    }
}
