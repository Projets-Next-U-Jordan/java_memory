package fr.thejordan.dev.helper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImageLoader {

    public static BufferedImage loadImage(String path) {
        File file = new File(path);
        if (file.exists()) {
            try {
				return ImageIO.read(file);
			} catch (IOException e) {
				return null;
			}
        }
        return null;
    }
	
    public static List<Card> loadImages(String folderPath) {
        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        List<Card> images = new ArrayList<Card>();
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && isImageFile(file.getName())) {
                    try {
                        BufferedImage image = ImageIO.read(file);
                        images.add(new Card(file.getName(), image));
                    } catch (IOException e) {
                        System.err.println("Error loading image: " + file.getName());
                    }
                }
            }
        } else {
            System.err.println("Folder does not exist or is empty.");
        }
        return images;
    }

    private static boolean isImageFile(String fileName) {
        return fileName.toLowerCase().endsWith(".jpg") ||
               fileName.toLowerCase().endsWith(".jpeg") ||
               fileName.toLowerCase().endsWith(".png") ||
               fileName.toLowerCase().endsWith(".gif") ||
               fileName.toLowerCase().endsWith(".bmp") ||
               fileName.toLowerCase().endsWith(".webp");
    }
}