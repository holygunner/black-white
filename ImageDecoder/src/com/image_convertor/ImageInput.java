package com.image_convertor;

import javax.imageio.ImageIO;
import java.applet.Applet;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageInput {
	
    static synchronized BufferedImage getImage (String filePath) {
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(new File(filePath));
        }   catch (IOException ex) {
            System.out.println("file not found");
        }
        return bufferedImage;
    }

}
