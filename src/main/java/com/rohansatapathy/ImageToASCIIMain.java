package com.rohansatapathy;

import java.awt.*;

public class ImageToASCIIMain {
    public static void main(String[] args) {

        // Get RGBArray
        ImageToRGBPipeline imgToRGB = new ImageToRGBPipeline();
        Color[][] RGBArray = imgToRGB.getRGBArrayFromImage("ascii-pineapple.jpeg", 300);

        System.out.println("Successfully constructed RGB array!");
        System.out.println("Width: " + RGBArray[0].length);
        System.out.println("Height: " + RGBArray.length);
        System.out.println("Iterating through pixel contents...");

        Color color;
        for (int i = 0; i < 5; i++) {
            color = RGBArray[0][i];
            System.out.println("(" + color.getRed() + ", " + color.getGreen() + ", " + color.getBlue() + ")");
        }
        System.out.println("...\n");

        // Convert RGBArray to brightness array
        RGBToBrightnessPipeline RGBTToBrightness = new RGBToBrightnessPipeline();
        double[][] brightnessArray = RGBTToBrightness.getBrightnessArray(RGBArray);

        System.out.println("Successfully constructed brightness array!");
        System.out.println("Iterating through brightness contents...");

        double brightness;
        for (int i = 0; i < 5; i++) {
            brightness = brightnessArray[0][i];
            System.out.println(brightness);
        }
        System.out.println("...\n");

        // Convert brightness array to ASCII array
        BrightnessToASCIIPipeline brightnessToASCII = new BrightnessToASCIIPipeline();
        char[][] ASCIIArray = brightnessToASCII.getASCIIArray(brightnessArray);

        System.out.println("Successfully constructed ASCII array!");
        System.out.println("Iterating through ASCII contents...");

        char ASCIIChar;
        for (int i = 0; i < 5; i++) {
            ASCIIChar = ASCIIArray[0][i];
            System.out.println(ASCIIChar);
        }
        System.out.println("...\n");

        // Print out image
        for (char[] row : ASCIIArray) {
            for (char character : row) {
                for (int i = 0; i < 3; i++) {
                    System.out.print(character);
                }
            }
            System.out.println();
        }
    }
}
