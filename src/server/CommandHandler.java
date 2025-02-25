package server;

public class CommandHandler {

    private final FileService fileService;

    public CommandHandler() {

        fileService = new FileService();
    }

    public String execute(String command, String fileName) {
        /*return switch (command) {
            case "add" -> fileService.addFile(fileName);
            case "get" -> fileService.getFile(fileName);
            case "delete" -> fileService.deleteFile(fileName);
            default -> "Command not recognised!";
        };*/
        return "";
    }
}
