import java.io.*;
import java.net.*;

public class ChatClient {
    public static void main(String[] args) {
        try {
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
            
            // ask username
            System.out.print("Enter your name: ");
            String userName = console.readLine();

            // ❌ This port is incorrect. Your server is on port 12345.
            // ✅ Change the port number below to 12345 to connect to your server.
            Socket socket = new Socket("chatapp-production-706d.up.railway.app", 8080);

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // send name to server as prefix
            new Thread(() -> {
                String msg;
                try {
                    while ((msg = in.readLine()) != null) {
                        // ✅ Ignore if it’s your own message
                        if (!msg.startsWith(userName + ":")) {
                            System.out.println(msg);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            // sending messages
            String userInput;
            while ((userInput = console.readLine()) != null) {
                out.println(userName + ": " + userInput);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
