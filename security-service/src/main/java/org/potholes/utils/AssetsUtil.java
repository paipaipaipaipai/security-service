package org.potholes.utils;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

public class AssetsUtil {

    private static String USER = "dylan_xu";
    private static String IMGSRC = "C:\\Users\\" + USER
        + "\\AppData\\Local\\Packages\\Microsoft.Windows.ContentDeliveryManager_cw5n1h2txyewy\\LocalState\\Assets";
    private static String IMGDEST = "C:\\Users\\" + USER + "\\Pictures\\Assets";

    public static void main(String[] args) throws Exception {
        checkImgdest();
        File directory = new File(IMGSRC);
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                copy(file);
            }
        }
        System.out.println("Success....");
    }

    private static void checkImgdest() {
        File file = new File(IMGDEST);
        if (!file.exists()) {
            file.mkdir();
        }
    }

    private static void copy(File file) throws Exception {
        ByteArrayOutputStream baos = null;
        InputStream is = null;
        OutputStream os = null;
        try {
            BufferedImage src = ImageIO.read(file);
            int width = src.getWidth();
            int height = src.getHeight();
            if (width > height) {
                BufferedImage image = new BufferedImage(height, height, BufferedImage.TYPE_INT_RGB);
                Graphics graphics = image.getGraphics();
                graphics.drawImage(src.getScaledInstance(height, height, Image.SCALE_SMOOTH), 0, 0, null);
                graphics.dispose();
                baos = new ByteArrayOutputStream();
                ImageIO.write(image, "JPG", baos);
                is = new ByteArrayInputStream(baos.toByteArray());
                String fileName = IMGDEST + File.separator + file.getName() + ".JPG";
                os = new FileOutputStream(fileName);
                int len = -1;
                byte[] bt = new byte[1024];
                while ((len = is.read(bt)) != -1) {
                    os.write(bt, 0, len);
                }
                System.out.println(fileName);
            }
        } catch (Exception e) {
            // TODO
            e.printStackTrace();
        } finally {
            if (os != null)
                os.close();
            if (is != null)
                is.close();
            if (baos != null)
                baos.close();
        }
    }

}
