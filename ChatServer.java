import java.net.*;
import java.io.*;

public class ChatServer {
    public static void main(String[] args) throws Exception {
        // Railway provides PORT automatically
        int port = Integer.parseInt(System.getenv().getOrDefault("PORT", "12345"));
        ServerSocket server = new ServerSocket(port);
        System.out.println("Server started on port " + port);

        while (true) {
            Socket client = server.accept();
            System.out.println("New client connected: " + client.getInetAddress());
            // Start a thread for each client
            new Thread(new ClientHandler(client)).start();
        }
    }
}

class ClientHandler implements Runnable {
    private Socket client;
    public ClientHandler(Socket socket) { this.client = socket; }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);

            String msg;
            while ((msg = in.readLine()) != null) {
                System.out.println("Received: " + msg);
                out.println("Friend: " + msg); // echo back for now
            }
        } catch (Exception e) {
            System.out.println("Client disconnected");
        }
    }
}
