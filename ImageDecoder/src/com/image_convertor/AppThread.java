package com.image_convertor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class AppThread extends Thread {

    private static BufferedImage img;
    private static String filePath;
    private static int threadsCount;

    public static void main(String[] args) throws IOException, InterruptedException {
        String filePath = "U:/! JAVA vol.2/- задания и повтор теории (с 10-06-14)/1 Threads/Black&White Image Convertor by MultiThreads Fix/image/28CF6A39A11B-22.jpg";
        int threadsCount = 10;


        img = ImageInput.getImage(filePath);
        AppThread.filePath = filePath;
        AppThread.threadsCount = threadsCount;

        ArrayList<AppThread> appThreadsList = new ArrayList<AppThread>();
        for (int i=0; i<threadsCount; ++i) {
            AppThread appThread = new AppThread();
            appThread.setName((i+1) + "");
            appThreadsList.add(appThread);
        }

        for (int i=0; i<threadsCount; ++i) {
            appThreadsList.get(i).start();
        }

    }

    public void run() {

        int currentThreadNumber = Integer.parseInt(Thread.currentThread().getName());

        try {

            int red, green, blue;
            int newColor;

            int threadsCount = AppThread.getThreadsCount();
            BufferedImage originalImage = AppThread.getImg();

            int heightFirst = ((originalImage.getHeight())/threadsCount)*(currentThreadNumber-1);
            int heightLast = ((originalImage.getHeight())/threadsCount)*currentThreadNumber;


        System.out.println(heightFirst + "-" + heightLast + "-" + currentThreadNumber);
        System.out.println("image width = " + originalImage.getHeight());

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
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    protected static synchronized BufferedImage getImg() {
        return AppThread.img;
    }

    protected static synchronized String getFilePath() {
        return AppThread.filePath;
    }

    protected static synchronized int getThreadsCount() {
        return AppThread.threadsCount;
    }
}
