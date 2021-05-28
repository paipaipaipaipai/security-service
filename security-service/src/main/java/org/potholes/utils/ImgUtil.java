package org.potholes.utils;

import java.io.File;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

/***
 * 只有开启了GPS才可以获取到位置信息
 * 
 */
public class ImgUtil {

    private static String IMGSRC = "F:\\PIC";

    public static void main(String[] args) throws Exception {
        File directory = new File(IMGSRC);
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                doRead(file);
                System.out.println("======================");
            }
        }
    }

    private static void doRead(File file) throws Exception {
        Metadata metadata = JpegMetadataReader.readMetadata(file);
        for (Directory directory : metadata.getDirectories()) {
            for (Tag tag : directory.getTags()) {
                String tagName = tag.getTagName(); // 标签名
                String desc = tag.getDescription(); // 标签信息
                System.out.println("[" + tagName + "]:" + desc);
            }
        }
    }
}
