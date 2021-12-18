package com.rohansatapathy;

public class BrightnessToASCIIPipeline {

    String BRIGHTNESSCHARS = "`^\",:;Il!i~+_-?][}{1)(|\\/tfjrxnuvczXYUJCLQ0OZmwqpdbkhao*#MW&8%B@$";

    public char[][] getASCIIArray(double[][] brightnessArray) {
        char[][] ASCIIArray = new char[brightnessArray.length][brightnessArray[0].length];

        for (int row = 0; row < brightnessArray.length; row++) {
            for (int col = 0; col < brightnessArray[0].length; col++) {
                double brightness = brightnessArray[row][col];

                // Scale the brightness value to get an index from 0 to the number of brightness chars
                int brightnessIndex = (int)(brightness * BRIGHTNESSCHARS.length());

                // If the pixel is of full brightness, the index will be out of bounds so compensate for that
                if (brightnessIndex == BRIGHTNESSCHARS.length()) {
                    brightnessIndex--;
                }

                char brightnessChar = BRIGHTNESSCHARS.charAt(brightnessIndex);
                ASCIIArray[row][col] = brightnessChar;
            }
        }

        return ASCIIArray;
    }
}
