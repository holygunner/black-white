package com.image_convertor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageBlackAndWhiteConverter {
	
    protected static synchronized void convertColor(int currentThreadNumber) throws IOException {
        int red, green, blue;
        int newColor;

        int threadsCount = AppThread.getThreadsCount();
        BufferedImage originalImage = AppThread.getImg();

        int heightFirst = ((originalImage.getHeight())/threadsCount)*(currentThreadNumber-1);
        int heightLast = ((originalImage.getHeight())/threadsCount)*currentThreadNumber;


        System.out.println(heightFirst + "-" + heightLast + "-" + currentThreadNumber);

        System.out.println("image width = " + originalImage.getWidth());

        for (int h=heightFirst; h < heightLast; ++h) {
            for (int w=0; w < originalImage.getWidth(); ++w) {
                red = new Color(originalImage.getRGB(w,h)).getRed();
                green = new Color(originalImage.getRGB(w,h)).getGreen();
                blue = new Color(originalImage.getRGB(w,h)).getBlue();
                newColor = ((red+green+blue)/3);
                Color color = new Color(newColor, newColor, newColor);
                originalImage.setRGB(w,h,color.getRGB());
            }
        }

        ImageIO.write(originalImage, "PNG", new File(AppThread.getFilePath() + "Black&WhiteEdit" + ".png"));

    }

}
