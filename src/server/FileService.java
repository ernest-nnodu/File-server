package server;

import java.util.Optional;

public class FileService {

    private final FileRepository fileRepository;

    public FileService() {

        fileRepository = new FileRepository();
    }

    public String addFile(String name, String content) {

        if (fileRepository.fileExists(name)) {
            return "creating the file was forbidden!";
        }

        fileRepository.addFile(new File(name, content));
        return "file was created!";
    }

    public String getFile(String name) {
        Optional<File> file = fileRepository.getFile(name);

        return file.isPresent() ? "The file " + name + " was sent" :
                "The file " + name + " not found";
    }

    public String deleteFile(String name) {

        Optional<File> fileToDelete = fileRepository.getFile(name);

        if (fileToDelete.isEmpty()) {
            return "The file " + name + " not found";
        }

        fileRepository.deleteFile(fileToDelete.get());

        return "The file " + name + " was deleted";
    }
}
