package com.wyatt92.games.model;

import com.wyatt92.games.view.ImageLoader;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;

class ImageLoaderTest {

    private BufferedImage testbImg;

    @Test
    public void testLoadImage() {
        testbImg = ImageLoader.loadImage("test.png");
        assert testbImg != null : "There was a problem loading your image.";
    }

}