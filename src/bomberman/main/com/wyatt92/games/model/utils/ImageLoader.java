package com.wyatt92.games.model.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Loads images.
 */
public class ImageLoader {
    public static BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(new FileInputStream("./src/bomberman/main/com/wyatt92/resources/images/" + path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
