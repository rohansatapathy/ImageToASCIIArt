package com.rohansatapathy;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;

public class ImageToRGBPipeline {

    private File getFileFromResource(String fileName) throws URISyntaxException {

        ClassLoader classLoader = this.getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("File '" + fileName + "' not found!");
        } else {
            return new File(resource.toURI());
        }
    }

    public Color[][] getRGBArrayFromImage(String fileName) {
        try {
            File file = this.getFileFromResource(fileName);
            BufferedImage img = ImageIO.read(file);

            Color[][] RGBArray = new Color[img.getWidth()][img.getHeight()];
            for (int row = 0; row < RGBArray.length; row++){
                for (int col = 0; col < RGBArray[0].length; col++) {
                    RGBArray[row][col] = new Color(img.getRGB(row, col));
                }
            }

            return RGBArray;

        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
        return new Color[0][0];
    }
}
