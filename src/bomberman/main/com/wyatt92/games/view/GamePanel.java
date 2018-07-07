package com.wyatt92.games.view;

import com.wyatt92.games.model.Model;
import com.wyatt92.games.model.entities.*;
import com.wyatt92.games.model.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Creates a GameFrame and a GamePanel. Configures the GamePanel
 * Represents the visualization of the data that model contains.
 */
class GamePanel extends JPanel
{
    private final Model model;
    private static Animation animBlast;
    private static Animation animBomb;
    private Animation animItemBombStrength;
    private Animation animItemPlayerSpeed;
    private Animation animItemBombCount;
    private ArrayList<Animation> itemAnimations;
    private Animation animDown, animUp, animLeft, animRight;


    private ArrayList<BufferedImage> tileImages;
    /**
     * Constructor
     *
     */
    public GamePanel(Model model) {
        this.model = model;
        configureGamePanel();
        setupAnimation();
        setupImages();
    }

    private void setupImages()
    {
        tileImages = new ArrayList<>();
        tileImages.add(0, Assets.grass);
        tileImages.add(1, Assets.dirt);
        tileImages.add(2, Assets.wall);
    }

    private void setupAnimation()
    {
        animBlast = new Animation(200, Assets.blast);
        animBomb = new Animation(500, Assets.bomb);

        itemAnimations = new ArrayList<>();

        animItemBombStrength = new Animation(500, Assets.bombStrength);
        animItemPlayerSpeed = new Animation(500, Assets.playerSpeed);
        animItemBombCount = new Animation(500, Assets.bombCount);
        itemAnimations.add(animItemBombStrength);
        itemAnimations.add(animItemPlayerSpeed );
        itemAnimations.add(animItemBombCount);

        animDown = new Animation(200, Assets.player_down);
        animUp = new Animation(200, Assets.player_up);
        animLeft = new Animation(200, Assets.player_left);
        animRight = new Animation(200, Assets.player_right);

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
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        updateAnimation();

        for (int y = 0; y < model.getTileColumns(); y++)
        {
            for (int x = 0; x < model.getTileRows(); x++)
            {
                model.getTile(x, y).setPosition(x * Tile.TILEWIDTH, y * Tile.TILEHEIGHT);
                int id = model.getTile(x, y).getID();
                g.drawImage(tileImages.get(id), model.getTile(x,y).getX(), model.getTile(x, y).getY(), Tile.TILEWIDTH, Tile.TILEHEIGHT, null);
            }
        }

        for (Blast b : model.getBlasts())
        {
            g.drawImage(animBlast.getCurrentFrame(), (int) b.getX() - Tile.TILEWIDTH / 2, (int) b.getY() - Tile.TILEHEIGHT / 2, Tile.TILEWIDTH, Tile.TILEHEIGHT, null);

        }

        for (Bomb bomb : model.getBombs())
        {
            g.drawImage(animBomb.getCurrentFrame(), (int) bomb.getX() - bomb.getWidth() / 2, (int) bomb.getY() - bomb.getHeight() / 2, bomb.getWidth(), bomb.getHeight(), null);
        }


        for (Stone stone : model.getStones())
            g.drawImage(Assets.stone, (int) stone.getX(), (int) stone.getY(), stone.getWidth(), stone.getHeight(), null);


        for (Item i : model.getItems())
        {
            g.drawImage(itemAnimations.get(i.getId()).getCurrentFrame(), (int) i.getX(), (int) i.getY(), i.getWidth(), i.getHeight(), null);
        }


        for (Player p : model.getPlayers())
        {

            if (p.getxMove() < 0)
            {
                g.drawImage(animLeft.getCurrentFrame(), (int) p.getX(), (int) p.getY(), p.getWidth(), p.getHeight(), null);

            } else if (p.getxMove() > 0)
            {
                g.drawImage(animRight.getCurrentFrame(), (int) p.getX(), (int) p.getY(), p.getWidth(), p.getHeight(), null);
            } else if (p.getyMove() < 0)
            {
                g.drawImage(animUp.getCurrentFrame(), (int) p.getX(), (int) p.getY(), p.getWidth(), p.getHeight(), null);

            } else if (p.getyMove() > 0)
            {
                g.drawImage(animDown.getCurrentFrame(), (int) p.getX(), (int) p.getY(), p.getWidth(), p.getHeight(), null);

            } else
            {
                g.drawImage(animDown.getCurrentFrame(), (int) p.getX(), (int) p.getY(), p.getWidth(), p.getHeight(), null);
            }
        }



        g.dispose();
    }
    private void updateAnimation()
    {
        animBlast.update();
        animBomb.update();
        for(Animation a : itemAnimations){
            a.update();
        }

        animDown.update();
        animUp.update();
        animRight.update();
        animLeft.update();
    }

}

