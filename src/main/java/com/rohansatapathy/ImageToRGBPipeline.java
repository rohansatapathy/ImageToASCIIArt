package com.rohansatapathy;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
//import org.bytedeco.javacv.*;
//import org.bytedeco.javacv.Frame;

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

    private Color[][] getRGBArrayFromImage(BufferedImage img) {
        Color[][] RGBArray = new Color[img.getHeight()][img.getWidth()];
        for (int row = 0; row < RGBArray.length; row++){
            for (int col = 0; col < RGBArray[0].length; col++) {
                // Swap row and col in img.getRGB() because the col refers to the x value and the row refers to the
                // y value of the pixel
                RGBArray[row][col] = new Color(img.getRGB(col, row));
            }
        }

        return RGBArray;
    }

    public Color[][] getRGBArrayFromFile(String fileName) {
        try {
            File file = this.getFileFromResource(fileName);
            BufferedImage img = ImageIO.read(file);

            return getRGBArrayFromImage(img);

        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
        return new Color[0][0];
    }

    public Color[][] getRGBArrayFromFile(String fileName, int targetWidth) {
        try {
            File file = this.getFileFromResource(fileName);
            BufferedImage img = resizeImage(ImageIO.read(file), targetWidth);

            return getRGBArrayFromImage(img);

        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
        return new Color[0][0];
    }

//    public Color[][] getRGBArrayFromWebcam(int targetWidth) {
//        try {
//            FrameGrabber grabber = new OpenCVFrameGrabber(1);
//            grabber.start();
//
//            Frame frame = grabber.grabFrame();
//            Java2DFrameConverter converter = new Java2DFrameConverter();
//            BufferedImage img = converter.getBufferedImage(frame);
//
//            return getRGBArrayFromImage(resizeImage(img, targetWidth));
//        } catch (FrameGrabber.Exception e) {
//            e.printStackTrace();
//        }
//
//        return new Color[0][0];
//    }
}
