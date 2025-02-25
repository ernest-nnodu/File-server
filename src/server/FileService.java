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

        return file.map(File::getContent).orElse("");
    }

    public String deleteFile(String name) {
        if (!fileRepository.fileExists(name)) {
            return "";
        }

        fileRepository.deleteFile(name);

        return "the file was successfully deleted!";
    }
}
