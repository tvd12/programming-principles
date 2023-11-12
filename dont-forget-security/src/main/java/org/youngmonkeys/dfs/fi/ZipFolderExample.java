package org.youngmonkeys.dfs.fi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipFolderExample {
    public static void main(String[] args) {
        String sourceFolderPath = "private"; // Specify the source folder to be zipped
        String zipFilePath = "example.zip"; // Specify the path for the output zip file

        try {
            // Create a FileOutputStream for the zip file
            FileOutputStream fos = new FileOutputStream(zipFilePath);

            // Create a ZipOutputStream
            ZipOutputStream zos = new ZipOutputStream(fos);

            // Create a File object for the source folder
            File sourceFolder = new File(sourceFolderPath);

            // Call the zipFolder method to zip the folder
            zipFolder(sourceFolder, sourceFolder.getName(), zos);

            // Close the ZipOutputStream
            zos.close();

            System.out.println("Folder successfully zipped to " + zipFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void zipFolder(
        File sourceFolder,
        String parentFolder,
        ZipOutputStream zos
    ) throws IOException {
        File[] files = sourceFolder.listFiles();
        if (files == null) {
            return;
        }
        for (File file : files) {
            if (file.isFile()) {
                FileInputStream fis = new FileInputStream(file);
                ZipEntry zipEntry = new ZipEntry( "../" + parentFolder + "/" + file.getName());
                zos.putNextEntry(zipEntry);
                byte[] buffer = new byte[1024];
                int length;
                while ((length = fis.read(buffer)) > 0) {
                    zos.write(buffer, 0, length);
                }
                fis.close();
            }
        }
    }
}
