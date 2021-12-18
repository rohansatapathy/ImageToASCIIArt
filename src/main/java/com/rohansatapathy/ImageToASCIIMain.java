package com.rohansatapathy;

import java.awt.*;
import com.diogonunes.jcolor.*;

public class ImageToASCIIMain {
    public static void main(String[] args) {

        // Get RGBArray
        ImageToRGBPipeline imgToRGB = new ImageToRGBPipeline();
        Color[][] RGBArray = imgToRGB.getRGBArrayFromWebcam(400);

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
        Attribute textColor;
        char character;
        boolean COLOR = false;
        for (int row = 0; row < ASCIIArray.length; row++) {
            for (int col = 0; col < ASCIIArray[0].length; col++) {
                character = ASCIIArray[row][col];
                if (COLOR) {
                    color = RGBArray[row][col];
                    textColor = Attribute.TEXT_COLOR(color.getRed(), color.getGreen(), color.getBlue());
                    for (int i = 0; i < 3; i++) {
                        System.out.print(Ansi.colorize(Character.toString(character), textColor, Attribute.BOLD()));
                    }
                } else {
                    for (int i = 0; i < 3; i++) {
                        System.out.print(character);
                    }
                }

            }
            System.out.println();
        }
    }
}
