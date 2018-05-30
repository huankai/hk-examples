package com.hk.util;

import java.io.*;

/**
 * @author: huangkai
 * @date 2018-05-29 15:19
 */
public class FileAssistant {

    private static final int EOF = -1;

    private static final int BUFFER_SIZE = 1024 * 1024 / 2;

    public static void write(String text, File outputFile) {
        try (BufferedReader reader = new BufferedReader(new StringReader(text));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            char[] buffer = new char[BUFFER_SIZE];
            int read;
            while ((read = reader.read(buffer)) != EOF) {
                writer.write(buffer, 0, read);
            }
            print(outputFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void print(String outputPath) {
//        outputPath = outputPath.replace("\\", "/");
//        while (outputPath.contains("/..")) {
//            int index = outputPath.indexOf("../");
//            String lpath = outputPath.substring(0, index - 1);
//            String rpath = outputPath.substring(index + 2);
//            outputPath = lpath.substring(0, lpath.lastIndexOf("/")) + rpath;
//        }
//        outputPath = outputPath.replace("/.", "");
        System.out.println(String.format("[C] %s %s", outputPath, "\n"));
    }
}
