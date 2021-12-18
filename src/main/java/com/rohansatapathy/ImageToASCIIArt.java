package com.rohansatapathy;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;
import org.apache.commons.cli.*;

import java.awt.*;

public class ImageToASCIIArt {

    public static void main(String[] args) {

        // Create an Options object to store the command line options
        Options options = new Options();

        // Add color flag
        options.addOption("c", "color", false, "Print ASCII art in color");
        boolean printInColor = false;

        // Add input file option
        Option inputFileOption = Option.builder("i")
                .longOpt("input-file")
                .hasArg()
                .argName("file")
                .desc("Use given file to generate ASCII art")
                .build();
        options.addOption(inputFileOption);
        String inputFilePath = null;

        // Add the pixel character width option
        Option pixelCharWidthOption = Option.builder("p")
                .longOpt("pixel-character-width")
                .hasArg()
                .argName("int")
                .desc("Number of characters per pixel; the optimal value will depend on your terminal font and viewing"
                        + " window [default: 3]")
                .build();
        options.addOption(pixelCharWidthOption);
        int pixelCharWidth = 3;  // Use this as default value

        // Add the width option
        Option imageWidthOption = Option.builder("w")
                .longOpt("width")
                .hasArg()
                .argName("int")
                .desc("Rescale the ASCII art to the given width in characters [default: 80]")
                .build();
        options.addOption(imageWidthOption);
        int pixelArrayWidth = 80 / pixelCharWidth;  // Assume default as 80-character terminal window

        // Generate the help option
        HelpFormatter formatter = new HelpFormatter();
        options.addOption("h", "help", false, "Display help information");

        // Parse command line arguments
        try {
            CommandLineParser parser = new DefaultParser();
            CommandLine cmd = parser.parse(options, args);

            if (cmd.hasOption("c")) {
                printInColor = true;
            }
            if (cmd.hasOption("i")) {
                inputFilePath = cmd.getOptionValue("input-file");
            }
            if (cmd.hasOption("p")) {
                pixelCharWidth = Integer.parseInt(cmd.getOptionValue("p"));
            }
            if (cmd.hasOption("w")) {
                pixelArrayWidth = Integer.parseInt(cmd.getOptionValue("w")) / pixelCharWidth;
            }
            if (cmd.hasOption("h")) {
                displayHelpInfoAndExit(formatter, options);
            }
        } catch (ParseException e) {
            // The default behavior should be to display the help info and exit
            displayHelpInfoAndExit(formatter, options);
        }

        // Get RGBArray from image source
        ImageToRGBPipeline imgToRGB = new ImageToRGBPipeline();
        Color[][] RGBArray = imgToRGB.getRGBArrayFromFile(inputFilePath, pixelArrayWidth);

        // Convert RGBArray to brightness array
        RGBToBrightnessPipeline RGBTToBrightness = new RGBToBrightnessPipeline();
        double[][] brightnessArray = RGBTToBrightness.getBrightnessArray(RGBArray);

        // Convert brightness array to ASCII array
        BrightnessToASCIIPipeline brightnessToASCII = new BrightnessToASCIIPipeline();
        char[][] ASCIIArray = brightnessToASCII.getASCIIArray(brightnessArray);

        // Print out ASCII art
        Attribute textColor;
        char character;
        Color color;
        for (int row = 0; row < ASCIIArray.length; row++) {
            for (int col = 0; col < ASCIIArray[0].length; col++) {
                character = ASCIIArray[row][col];
                if (printInColor) {
                    color = RGBArray[row][col];
                    textColor = Attribute.TEXT_COLOR(color.getRed(), color.getGreen(), color.getBlue());
                    for (int i = 0; i < pixelCharWidth; i++) {
                        System.out.print(Ansi.colorize(Character.toString(character), textColor, Attribute.BOLD()));
                    }
                } else {
                    for (int i = 0; i < pixelCharWidth; i++) {
                        System.out.print(character);
                    }
                }
            }
            System.out.println();
        }
    }

    private static void displayHelpInfoAndExit(HelpFormatter formatter, Options options) {
        String header = "Convert image file to ASCII art and print it to the terminal\n\n";
        String footer = "\nPlease report issues at https://github.com/rohansatapathy/WebcamToASCIIArt";
        formatter.printHelp(
                "java -jar ImageToASCIIArt.jar [options] --input-file <file>",
                header,
                options,
                footer);
        System.exit(0);
    }
}
