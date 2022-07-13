package voice.assistante.client.commands;

public class FirefoxCommand implements Command {
    @Override
    public boolean isCommandActivated(String message) {
        return message.startsWith("/firefox");
    }

    @Override
    public String getTransformedCommand(String message) {
        if (message.equals("/firefox")) {
            return "start firefox";
        }
        if (message.startsWith("/firefox")) {
            String[] split = message.split("/firefox", 2);
            return "start firefox" + split[1];
        }
        return message;
    }
}
