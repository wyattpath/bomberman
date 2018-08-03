package com.wyatt92.games.view;

import com.wyatt92.games.model.Model;
import com.wyatt92.games.model.entities.*;
import com.wyatt92.games.model.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Creates a GameFrame and a GamePanel. Configures the GamePanel
 * Represents the visualization of the data that model contains.
 */
public class GamePanel extends JPanel
{
    private static Animation animBlast;
    private static Animation animBomb;
    private final Model model;
    private Animation animItemBombStrength;
    private Animation animItemPlayerSpeed;
    private Animation animItemBombCount;
    private ArrayList<Animation> itemAnimations;
    private Animation animP1Down, animP1Up, animP1Left, animP1Right;
    private Animation animP2Down, animP2Up, animP2Left, animP2Right;


    private ArrayList<BufferedImage> tileImages;

    /**
     * Constructor
     */
    public GamePanel(Model model)
    {
        this.model = model;
        configureGamePanel();
        setupAnimation();
        setupImages();
    }

    private void setupImages()
    {
        tileImages = new ArrayList<>();
        tileImages.add(0, SpriteLibrary.grass);
        tileImages.add(1, SpriteLibrary.dirt);
        tileImages.add(2, SpriteLibrary.wall);
    }

    private void setupAnimation()
    {
        animBlast = new Animation(200, SpriteLibrary.blast);
        animBomb = new Animation(500, SpriteLibrary.bomb);

        itemAnimations = new ArrayList<>();

        animItemBombStrength = new Animation(500, SpriteLibrary.bombStrength);
        animItemPlayerSpeed = new Animation(500, SpriteLibrary.playerSpeed);
        animItemBombCount = new Animation(500, SpriteLibrary.bombCount);
        itemAnimations.add(animItemBombStrength);
        itemAnimations.add(animItemPlayerSpeed);
        itemAnimations.add(animItemBombCount);

        animP1Down = new Animation(200, SpriteLibrary.p1_down);
        animP1Up = new Animation(200, SpriteLibrary.p1_up);
        animP1Left = new Animation(200, SpriteLibrary.p1_left);
        animP1Right = new Animation(200, SpriteLibrary.p1_right);

        animP2Down = new Animation(200, SpriteLibrary.p2_down);
        animP2Up = new Animation(200, SpriteLibrary.p2_up);
        animP2Left = new Animation(200, SpriteLibrary.p2_left);
        animP2Right = new Animation(200, SpriteLibrary.p2_right);

    }

    private void configureGamePanel()
    {
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
        drawTiles(g);
        drawBlasts(g);
        drawBombs(g);
        drawStones(g);
        drawItems(g);
        drawPlayers(g);

        g.dispose();
    }

    private void updateAnimation()
    {
        animBlast.update();
        animBomb.update();
        for (Animation a : itemAnimations)
        {
            a.update();
        }

        animP1Down.update();
        animP1Up.update();
        animP1Right.update();
        animP1Left.update();

        animP2Down.update();
        animP2Up.update();
        animP2Right.update();
        animP2Left.update();
    }

    private void drawTiles(Graphics g)
    {
        for (int y = 0; y < model.getTileColumns(); y++)
        {
            for (int x = 0; x < model.getTileRows(); x++)
            {
                model.getTile(x, y).setPosition(x * Tile.TILEWIDTH, y * Tile.TILEHEIGHT);
                int id = model.getTile(x, y).getID();
                g.drawImage(tileImages.get(id), model.getTile(x, y).getX(), model.getTile(x, y).getY(), Tile.TILEWIDTH, Tile.TILEHEIGHT, null);
            }
        }
    }

    private void drawBlasts(Graphics g)
    {
        for (Blast b : model.getBlasts())
        {
            g.drawImage(animBlast.getCurrentFrame(), (int) b.getX() - Tile.TILEWIDTH / 2, (int) b.getY() - Tile.TILEHEIGHT / 2, Tile.TILEWIDTH, Tile.TILEHEIGHT, null);

        }
    }

    private void drawBombs(Graphics g)
    {
        for (Bomb bomb : model.getBombs())
        {
            g.drawImage(animBomb.getCurrentFrame(), (int) bomb.getX() - bomb.getWidth() / 2, (int) bomb.getY() - bomb.getHeight() / 2, bomb.getWidth(), bomb.getHeight(), null);
        }
    }

    private void drawStones(Graphics g)
    {
        for (Stone stone : model.getStones())
            g.drawImage(SpriteLibrary.stone, (int) stone.getX(), (int) stone.getY(), stone.getWidth(), stone.getHeight(), null);

    }

    private void drawItems(Graphics g)
    {
        for (Item i : model.getItems())
        {
            g.drawImage(itemAnimations.get(i.getId()).getCurrentFrame(), (int) i.getX(), (int) i.getY(), i.getWidth(), i.getHeight(), null);
        }

    }

    private void drawPlayers(Graphics g)
    {
        Player p1 = model.getPlayer(0);
        if (p1 != null)
        {
            if (p1.getxMove() < 0)
            {
                g.drawImage(animP1Left.getCurrentFrame(), (int) p1.getX(), (int) p1.getY(), p1.getWidth(), p1.getHeight(), null);

            } else if (p1.getxMove() > 0)
            {
                g.drawImage(animP1Right.getCurrentFrame(), (int) p1.getX(), (int) p1.getY(), p1.getWidth(), p1.getHeight(), null);
            } else if (p1.getyMove() < 0)
            {
                g.drawImage(animP1Up.getCurrentFrame(), (int) p1.getX(), (int) p1.getY(), p1.getWidth(), p1.getHeight(), null);

            } else if (p1.getyMove() > 0)
            {
                g.drawImage(animP1Down.getCurrentFrame(), (int) p1.getX(), (int) p1.getY(), p1.getWidth(), p1.getHeight(), null);

            } else
            {
                g.drawImage(animP1Down.getCurrentFrame(), (int) p1.getX(), (int) p1.getY(), p1.getWidth(), p1.getHeight(), null);
            }
        }

        Player p2 = model.getPlayer(1);
        if (p2 != null)
        {
            if (p2.getxMove() < 0)
            {
                g.drawImage(animP2Left.getCurrentFrame(), (int) p2.getX(), (int) p2.getY(), p2.getWidth(), p2.getHeight(), null);

            } else if (p2.getxMove() > 0)
            {
                g.drawImage(animP2Right.getCurrentFrame(), (int) p2.getX(), (int) p2.getY(), p2.getWidth(), p2.getHeight(), null);
            } else if (p2.getyMove() < 0)
            {
                g.drawImage(animP2Up.getCurrentFrame(), (int) p2.getX(), (int) p2.getY(), p2.getWidth(), p2.getHeight(), null);

            } else if (p2.getyMove() > 0)
            {
                g.drawImage(animP2Down.getCurrentFrame(), (int) p2.getX(), (int) p2.getY(), p2.getWidth(), p2.getHeight(), null);

            } else
            {
                g.drawImage(animP2Down.getCurrentFrame(), (int) p2.getX(), (int) p2.getY(), p2.getWidth(), p2.getHeight(), null);
            }
        }
    }
}





