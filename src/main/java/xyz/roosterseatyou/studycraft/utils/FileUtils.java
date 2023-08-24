package xyz.roosterseatyou.studycraft.utils;

import java.io.File;
import java.io.FileReader;

public class FileUtils {
    public static String readFile(File file) {
        StringBuilder json = new StringBuilder();
        FileReader reader = null;
        try {
            reader = new FileReader(file);
            int character;
            while((character = reader.read()) != -1) {
                json.append((char) character);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json.toString();
    }
}
