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

    private BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
        BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
        return outputImage;
    }

    private BufferedImage resizeImage(BufferedImage originalImage, int targetWidth) {
        int targetHeight = originalImage.getHeight() * targetWidth / originalImage.getWidth();
        return resizeImage(originalImage, targetWidth, targetHeight);
    }

    public Color[][] getRGBArrayFromImage(String fileName) {
        try {
            File file = this.getFileFromResource(fileName);
            BufferedImage img = ImageIO.read(file);

            Color[][] RGBArray = new Color[img.getHeight()][img.getWidth()];
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

    public Color[][] getRGBArrayFromImage(String fileName, int targetWidth) {
        try {
            File file = this.getFileFromResource(fileName);
            BufferedImage img = resizeImage(ImageIO.read(file), targetWidth);

            Color[][] RGBArray = new Color[img.getHeight()][img.getWidth()];
            for (int row = 0; row < RGBArray.length; row++){
                for (int col = 0; col < RGBArray[0].length; col++) {
                    // The col gives the x value of the pixel, row gives the y value which is why they're switched
                    // in img.getRGB()
                    RGBArray[row][col] = new Color(img.getRGB(col, row));
                }
            }

            return RGBArray;

        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
        return new Color[0][0];
    }
}
