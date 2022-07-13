package voice.assistante.client.commands;

public interface Command {
    boolean isCommandActivated(String message);
    String getTransformedCommand(String message);
}
