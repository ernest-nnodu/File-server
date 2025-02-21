package server;

import java.util.Optional;

public class FileService {

    private final FileRepository fileRepository;

    public FileService() {
        fileRepository = new FileRepository();
    }

    public String addFile(String name) {
        if (!name.matches("file[1-9]|file10")) {
            return "Cannot add the file " + name;
        }

        if (fileRepository.fileExists(name)) {
            return "Cannot add the file " + name;
        }

        fileRepository.addFile(new File(name));
        return "The file " + name + " added successfully";
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
