

public class AssistantClient {
    private static final String PATH = "https://voice-assistant-server.herokuapp.com/commands/pool-all-commands";
    public static void main(String[] args) {
        System.out.println("Starting Assistant client");
        while (true) {
            String response = BotNetUtils.httpsGETRequest(PATH);
            if (!response.isEmpty()) {
                System.out.println(response);
            }
        }
    }
}
