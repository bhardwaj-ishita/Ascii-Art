package com.company;

import java.awt.*;
import java.io.File; //This class represents file and directory path names in general.
import java.io.IOException; //To handle errors
import java.awt.image.BufferedImage; //To hold the image we create the BufferedImage object. This object is used to store an image in RAM.
import javax.imageio.ImageIO; //To perform the image read write operation. This class has static methods to read and write an image.

import java.awt.Color;
import java.util.Arrays;

import static com.company.Greyscale.greyScale;
import static com.company.PixelArray.FastRGB;
import static com.company.PixelArray.GetRGB;

public class Main {

    public static void resize(String inputImagePath,
                       String outputImagePath, int scaledWidth, int scaledHeight)
            throws IOException {
        // reads input image
        File inputFile = new File(inputImagePath);
        BufferedImage inputImage = ImageIO.read(inputFile);

        // creates output image
        BufferedImage outputImage = new BufferedImage(scaledWidth,
                scaledHeight, inputImage.getType());

        // scales the input image to the output image
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
        g2d.dispose();

        // extracts extension of output file
        String formatName = outputImagePath.substring(outputImagePath
                .lastIndexOf(".") + 1);

        // writes to output file
        ImageIO.write(outputImage, formatName, new File(outputImagePath));
    }

    public static void main(String[] args) {

        BufferedImage image = null;
        //For storing image in RAM

        //READ IMAGE
        try
        {
            //image file path
            File input_file = new File("D:\\Ishita\\Github\\Ascii Art\\out\\res\\images\\ascii-pineapple.jpg");

            /* create an object of BufferedImage type and
               pass as parameter the width, height and image int type.TYPE_INT_ARGB
               means that we are representing the Alpha, Red, Green and Blue component
               of the image pixel using 8 bit integer value. */

            image = new BufferedImage(640, 480 , BufferedImage.TYPE_INT_ARGB);

            // Reading input file
            image = ImageIO.read(input_file);
            //resize("D:\\Ishita\\Github\\Ascii Art\\out\\res\\images\\ascii-pineapple.jpg","D:\\Ishita\\Github\\Ascii Art\\out\\res\\images\\out.jpg", 4, 3);
            System.out.println("Reading complete.");
        }
        catch(IOException e)
        {
            System.out.println("Error: " + e);
        }


        //STORING THE PIXEL INFORMATION
        int[][] pixel = new int[image.getWidth()][image.getHeight()];

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                //Retrieving contents of a pixel
                pixel[x][y] = image.getRGB(x, y);

                //get alpha, red, green, blue values;
                int a = (pixel[x][y] >> 24) & 0xff;
                int r = (pixel[x][y] >> 16) & 0xFF;
                int g = (pixel[x][y] >> 8) & 0xFF;
                int b = (pixel[x][y] & 0xFF);

                // Calculate average:
                float average = ((r + g + b) / 3);

                pixel[x][y] = (int) average;
            }
        }

        String ascii = "`^\",:;Il!i~+_-?][}{1)(|\\/tfjrxnuvczXYUJCLQ0OZmwqpdbkhao*#MW&8%B@$";

        char[][] result = new char[image.getWidth()][image.getHeight()];
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                System.out.print(ascii.charAt(pixel[x][y]/4));
                result[x][y] = ascii.charAt(pixel[x][y]/4);
            }
            System.out.println();
        }

        //System.out.println(String.valueOf(result));

        // WRITE IMAGE
        try {
            // Output file path
            File output_file = new File("D:\\Ishita\\Github\\Ascii Art\\out\\res\\images\\out.jpg");

            // Writing to file taking type and path as

            BufferedImage bufferedImage = new BufferedImage(3000, 2500,
                    BufferedImage.TYPE_INT_RGB);
            Graphics graphics = bufferedImage.getGraphics();
            graphics.setColor(Color.LIGHT_GRAY);
            graphics.fillRect(0, 0, 3000, 2500);
            graphics.setColor(Color.BLACK);
            graphics.setFont(new Font("Arial", Font.PLAIN, 8));
            for(int i = 0; i < image.getWidth(); i++) {
                graphics.drawChars(result[i],0,image.getHeight(),10, 25 + (i*10));
            }

            //graphics.drawString(String.valueOf(result),10, 25);
            ImageIO.write(bufferedImage, "jpg", output_file);

            System.out.println("Writing complete.");
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }
}
