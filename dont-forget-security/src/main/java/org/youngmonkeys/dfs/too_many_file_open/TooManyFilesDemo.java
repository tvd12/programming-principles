package org.youngmonkeys.dfs.too_many_file_open;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class TooManyFilesDemo {

    public static void main(String[] args) {
        List<InputStream> streams = new ArrayList<>();
        String filePath = "test.txt";

        try {
            try (FileWriter writer = new FileWriter(filePath)) {
                writer.write("This is a test file.\n");
            }

            int count = 0;
            while (true) {
                InputStream in = new FileInputStream(filePath);
                streams.add(in);
                count++;
                if (count % 100 == 0) {
                    System.out.println("Opened " + count + " files...");
                }
            }

        } catch (Exception e) {
            System.err.println("Exception after opening many files: " + e.getMessage());
            e.printStackTrace();
        } finally {
            System.out.println("Cleaning up...");
            for (InputStream in : streams) {
                try {
                    in.close();
                } catch (IOException ignored) {}
            }
        }
    }
}
