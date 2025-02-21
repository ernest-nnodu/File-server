package server;

public class CommandHandler {

    private final FileService fileService;

    public CommandHandler() {
        fileService = new FileService();
    }

    public String execute(String command, String fileName) {
        switch (command) {
            case "add":
                return fileService.addFile(fileName);
            default:
                return "Command not recognised!";
        }
    }
}
