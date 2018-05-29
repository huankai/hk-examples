package com.hk.util;

import java.io.*;

/**
 * @author: huangkai
 * @date 2018-05-29 15:19
 */
public class FileAssistant {

    private static final int EOF = -1;

    private static final int BUFFER_SIZE = 1024 * 1024 / 2;

    public static void write(String text, File file) {
        BufferedReader reader = null;
        BufferedWriter writer = null;
        try {
            reader = new BufferedReader(new StringReader(text));
            writer = new BufferedWriter(new FileWriter(file));
            char[] buffer = new char[BUFFER_SIZE];
            int read;
            while ((read = reader.read(buffer)) != EOF) {
                writer.write(buffer, 0, read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
                if(writer != null) {
                    writer.close();
                }

            } catch (Exception e) {

            }
        }
    }
}
