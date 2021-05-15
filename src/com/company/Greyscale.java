package com.company;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Greyscale {
    BufferedImage image;
    static int width;
    static int height;

    static void greyScale(BufferedImage image, byte[] pixel) {
        width = image.getWidth();
        height = image.getHeight();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color c = new Color(image.getRGB(j, i));
                int red = (int) (c.getRed() * 0.299);
                int green = (int) (c.getGreen() * 0.587);
                int blue = (int) (c.getBlue() * 0.114);
                Color newColor = new Color(red + green + blue,
                        red + green + blue, red + green + blue);
                image.setRGB(j, i, newColor.getRGB());
            }
        }
        File ouptut = new File("D:\\Out.jpg");
        System.out.println("Done");
        try {
            ImageIO.write(image, "jpg", ouptut);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
