package voice.assistante.client;
import voice.assistante.client.commands.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AssistantClient {
    private static final String PATH = "https://voice-assistant-server.herokuapp.com/commands/pool-first-commands?clientId=";

    private static List<Command> commandList = new ArrayList<>();

    public static void main(String[] args) {
        addCommands();
        System.out.println("Starting Assistant client");
        while (true) {
            String response = BotNetUtils.httpsGETRequest(PATH);
            response = response.replaceAll("(\\r|\\n)", "");
            if (!response.isEmpty()) {
                try {
                    System.out.println("Starting running command : " + response);
                    excCommand(parseCommand(response));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void addCommands() {
        commandList.add(new FirefoxCommand());
        commandList.add(new ChromeCommand());
        commandList.add(new LaptopScreenCommand());
        commandList.add(new RunMusicCommand());
    }

    public static String parseCommand(String response) {
        for (int i = 0; i < commandList.size(); i++) {
            Command command = commandList.get(i);
            if (command.isCommandActivated(response)) {
                return command.getTransformedCommand(response);
            }
        }
        return response;
    }

    public static void excCommand(String command){
        Runtime rt = Runtime.getRuntime();
        try {
            System.out.println("executing command : " + command + "|");
            rt.exec(new String[]{"cmd.exe","/c",command});

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}