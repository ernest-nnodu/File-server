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
        if (!fileExists(name)) {
            return Optional.empty();
        }

        Path filePath = getFilePath(name);
        String fileContent = "";

        try {
            fileContent = Files.readString(filePath);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return Optional.of(new File(name, fileContent));
    }

    public void deleteFile(String name) {
        Path filePath = getFilePath(name);

        try {
            Files.delete(filePath);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public boolean fileExists(String name) {
        return Files.exists(getFilePath(name));
    }

    private Path getFilePath(String name) {
        return Paths.get(path.toString(), name);
    }
}
