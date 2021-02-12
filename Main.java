package com.company;

import java.awt.*;
import java.io.File; //This class represents file and directory path names in general.
import java.io.IOException; //To handle errors
import java.awt.image.BufferedImage; //To hold the image we create the BufferedImage object. This object is used to store an image in RAM.
import javax.imageio.ImageIO; //To perform the image read write operation. This class has static methods to read and write an image.

import java.awt.Color;

public class Main {

    public static void main(String[] args) {

        int width = 640; //width of the image
        int height = 480; //height of the image

        //For storing image in RAM
        BufferedImage image = null;

        //READ IMAGE
        try
        {
            //image file path
            File input_file = new File("D:\\ascii-pineapple.jpg");

            /* create an object of BufferedImage type and
               pass as parameter the width, height and image int type.TYPE_INT_ARGB
               means that we are representing the Alpha, Red, Green and Blue component
               of the image pixel using 8 bit integer value. */
            image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

            // Reading input file
            image = ImageIO.read(input_file);

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

                // Normalize and gamma correct:
                //float rr = (float) Math.pow(r / 255.0, 2.2);
                //float gg = (float) Math.pow(g / 255.0, 2.2);
                //float bb = (float) Math.pow(b / 255.0, 2.2);

                // Calculate average:
                float average = ((r + g + b)/3);

                // Gamma compand and rescale to byte range:
                int grayLevel = (int) (255.0 * Math.pow(average, 1.0 / 2.2));
                int gray = (grayLevel << 16) + (grayLevel << 8) + grayLevel;
                image.setRGB(x, y, gray);

                pixel[x][y] = gray;
            }
        }

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                System.out.println(pixel[x][y]);
            }
        }

        // WRITE IMAGE
        try
        {
            // Output file path
            File output_file = new File("D:\\Out.jpg");

            // Writing to file taking type and path as
            ImageIO.write(image, "jpg", output_file);

            System.out.println("Writing complete.");
        }
        catch(IOException e)
        {
            System.out.println("Error: "+e);
        }





    }
}
