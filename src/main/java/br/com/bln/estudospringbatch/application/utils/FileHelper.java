package br.com.bln.estudospringbatch.application.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

public class FileHelper {

    public static String getFileInResources(String path) {
        try {
            return new String(
                    Files.readAllBytes(
                            Paths.get(Objects.requireNonNull(FileHelper.class.getClassLoader().getResource(path)).getPath())
                    )
            );
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
