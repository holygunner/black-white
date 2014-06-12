package com.image_convertor;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class AppThread extends Thread {

    private static BufferedImage img;
    private static String filePath;
    private static int threadsCount;

    public static void main(String[] args) throws IOException, InterruptedException {
        String filePath = "U:/! JAVA vol.2/- задания и повтор теории (с 10-06-14)/1 Threads/Black&White Image Convertor by MultiThreads/image/Daft+Punk+sweet.jpg";
        int threadsCount = 4;


        img = ImageInput.getImage(filePath);
        AppThread.filePath = filePath;
        AppThread.threadsCount = threadsCount;

//        ImageOutput.setImage(filePath);

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
//        System.out.println(threadN);

        try {
            ImageBlackAndWhiteConverter.convertColor(currentThreadNumber);
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
