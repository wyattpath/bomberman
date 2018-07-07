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
        String tempPath = ImageLoader.class.getClassLoader().getResource("./images/" + path).getPath();

        assert tempPath != null : "Path is invalid!";

        try {

            return ImageIO.read(new FileInputStream(tempPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
