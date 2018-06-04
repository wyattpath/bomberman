package com.wyatt92.games.model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class ImageLoader {
    public static BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(new FileInputStream("./src/bomberman/resources/" + path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
