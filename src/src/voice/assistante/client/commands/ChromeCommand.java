package voice.assistante.client.commands;

public class ChromeCommand implements Command {
    @Override
    public boolean isCommandActivated(String message) {
        return message.startsWith("/chrome");
    }

    @Override
    public String getTransformedCommand(String message) {
        if (message.equals("/chrome")) {
            return "start chrome";
        }
        if (message.startsWith("/chrome")) {
            String[] split = message.split("/chrome", 2);
            return "start chrome" + split[1];
        }
        return message;
    }
}
