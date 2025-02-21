package server;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FileRepository {

    private final List<File> files;

    public FileRepository() {
        files = new ArrayList<>();
    }

    public void addFile(File file) {
        files.add(file);
    }

    public Optional<File> getFile(String name) {
        return files.stream()
                .filter(file -> file.getName().equals(name))
                .findFirst();
    }

    public boolean fileExists(String name) {
        return files.stream()
                .anyMatch(file -> file.getName().equals(name));
    }
}
