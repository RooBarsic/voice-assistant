package voice.assistante.client.commands;

public class RunMusicCommand implements Command {
    @Override
    public boolean isCommandActivated(String message) {
        return message.startsWith("/music");
    }

    @Override
    public String getTransformedCommand(String message) {
        if (message.equals("/music")) {
            return "start C:\\Users\\Farrukh_Karimov\\Desktop\\Barsic\\voice-assistant-client\\music\\Егор_Крид_Папина_дочка_OST_Завтрак_у_папы_.webm";
        }
        if (message.startsWith("/music")) {
            String[] split = message.split("/music ", 2);
            return "start C:\\Users\\Farrukh_Karimov\\Desktop\\Barsic\\voice-assistant-client\\music\\" + split[1];
        }
        return message;
    }
}
