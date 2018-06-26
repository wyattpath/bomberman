package com.wyatt92.games.view;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Loads images.
 */
public class ImageLoader {
    public static BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(new FileInputStream(ImageLoader.class.getClassLoader().getResource("./images/" + path).getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
