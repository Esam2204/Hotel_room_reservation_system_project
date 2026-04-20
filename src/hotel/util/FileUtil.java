package hotel.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    public static List<String> readLines(String path) {
        List<String> lines = new ArrayList<>();
        File file = new File(path);

        // если файл не существует — вернуть пустой список
        if (!file.exists()) {
            return lines;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                if (!currentLine.trim().isEmpty()) {
                    lines.add(currentLine);
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return lines;
    }

    public static void writeLines(String path, List<String> lines) {
        try {
            File file = new File(path);
            File parentDirectory = file.getParentFile();

            // создать папку если её нет
            if (parentDirectory != null && !parentDirectory.exists()) {
                parentDirectory.mkdirs();
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (String line : lines) {
                    writer.write(line);
                    writer.newLine();
                }
            }

        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }

    public static void appendLine(String path, String line) {
        try {
            File file = new File(path);
            File parentDirectory = file.getParentFile();

            // создать папку если её нет
            if (parentDirectory != null && !parentDirectory.exists()) {
                parentDirectory.mkdirs();
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                writer.write(line);
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error appending file: " + e.getMessage());
        }
    }
}