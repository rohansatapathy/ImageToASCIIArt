package com.rohansatapathy;

import java.awt.*;

public class ImageToRGBMain {
    public static void main(String[] args) {

        ImageToRGBPipeline imgToRGB = new ImageToRGBPipeline();
        Color[][] RGBArray = imgToRGB.getRGBArrayFromImage("ascii-pineapple.jpeg");

        System.out.println("Successfully constructed RGB array!");
        System.out.println("Width: " + RGBArray.length);
        System.out.println("Height: " + RGBArray[0].length);
        System.out.println("Iterating through pixel contents...");

        for (Color[] row : RGBArray) {
            for (Color color : row) {
                System.out.println("(" + color.getRed() + ", " + color.getGreen() + ", " + color.getBlue() + ")");
            }
        }

    }
}
