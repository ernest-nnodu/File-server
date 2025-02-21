package server;

public class FileService {

    private final FileRepository fileRepository;

    public FileService() {
        fileRepository = new FileRepository();
    }

    public String addFile(String name) {
        if (!name.matches("file[1-9]{1}|file10")) {
            return "Cannot add the file " + name;
        }

        if (fileRepository.fileExists(name)) {
            return "Cannot add the file " + name;
        }

        fileRepository.addFile(new File(name));
        return "The file " + name + " added successfully";
    }
}
