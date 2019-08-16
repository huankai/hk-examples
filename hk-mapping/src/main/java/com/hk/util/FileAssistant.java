package com.hk.util;

import com.hk.commons.util.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author kevin
 * @date 2018-05-29 15:19
 */
public class FileAssistant {

    /**
     * @param text
     * @param outputFile
     */
    public static void write(String text, File outputFile) {
        try {
            FileUtils.writeStringToFile(outputFile, text, StandardCharsets.UTF_8);
            print(outputFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void print(String outputPath) {
        System.out.println(String.format("[C] %s %s", outputPath, "\n"));
    }
}
