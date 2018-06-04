package com.wyatt92.games.model;

import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;

class ImageLoaderTest {

    private BufferedImage testbImg;

    @Test
    public void testLoadImage() {
        testbImg = ImageLoader.loadImage("./src/bomberman/resources/test.png");
        assert testbImg != null : "There was a problem loading your image.";
    }

}