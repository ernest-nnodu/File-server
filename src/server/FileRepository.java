package server;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FileRepository {

    private final String currentDir = System.getProperty("user.dir");
    private final Path path = Paths.get(currentDir, "src", "server", "data");
    private final Path folderPath = Paths.get(currentDir, "File Server",
            "task", "src", "server", "data");

    public FileRepository() {
        try {
            Files.createDirectories(path);
            System.out.println("Folder created successfully!");
        } catch (IOException ex) {
            System.out.println("Unable to create folder!");
        }
    }

    public void addFile(File file) {
        Path filePath = getFilePath(file.getName());
        try {
            Files.createFile(filePath);
            Files.writeString(filePath, file.getContent());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Optional<File> getFile(String name) {
        return Optional.empty();
    }

    public void deleteFile(File file) {
    }

    public boolean fileExists(String name) {
        return Files.exists(getFilePath(name));
    }

    private Path getFilePath(String name) {
        return Paths.get(path.toString(), name);
    }
}
