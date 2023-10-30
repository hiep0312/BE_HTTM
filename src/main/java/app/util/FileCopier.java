package app.util;


import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class FileCopier {
    public static void copyFile(Path src, Path target) throws IOException {
        Files.copy(src, target, StandardCopyOption.REPLACE_EXISTING);
    }

    public static void copyFile(InputStream src, Path target) throws IOException {
        Files.copy(src, target, StandardCopyOption.REPLACE_EXISTING);
    }
}
