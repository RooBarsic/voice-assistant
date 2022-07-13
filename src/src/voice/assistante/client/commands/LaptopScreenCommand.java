package voice.assistante.client.commands;

public class LaptopScreenCommand implements Command {
    @Override
    public boolean isCommandActivated(String message) {
        return message.startsWith("/lock") || message.startsWith("/unlock") || message.startsWith("/hibernate");
    }

    @Override
    public String getTransformedCommand(String message) {
        if (message.startsWith("/lock")) {
            return "rundll32.exe user32.dll,LockWorkStation";
        }
        if (message.startsWith("/unlock")) {
            return "rundll32.exe user32.dll,UnlockWorkStation";
        }
        if (message.startsWith("/hibernate")) {
            return "Rundll32.exe powrprof.dll,SetSuspendState";
        }
        return message;
    }
}
