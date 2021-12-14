package com.rohansatapathy;

import java.awt.*;

public class RGBToBrightnessPipeline {

    public double[][] getBrightnessArray(Color[][] RGBArray) {
        double[][] brightnessArray = new double[RGBArray.length][RGBArray[0].length];

        for (int row = 0; row < RGBArray.length; row++) {
            for (int col = 0; col < RGBArray[0].length; col++) {
                // Convert RGB to brightness by taking mean of all values and scaling down to 0.0 -> 1.0 scale
                Color color = RGBArray[row][col];
                double brightness = (color.getRed() + color.getGreen() + color.getBlue()) / 3.0 / 255.0;
                brightnessArray[row][col] = brightness;
            }
        }

        return brightnessArray;
    }
}
