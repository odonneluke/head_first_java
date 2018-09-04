import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class ChatServer {

    final static public String CHAT_SERVER_IP_ADDRESS = "127.0.0.1";
    final static public short CHAT_SERVER_PORT_NUMBER = 4555;
    private List<Writer> clientOutputStreams;

    public static void main(String[] args) {
        ChatServer server = new ChatServer();
        server.startServer();
    }

    private void startServer() {
        clientOutputStreams = new ArrayList<Writer>();
        try {
            ServerSocket serverSocket = new ServerSocket(CHAT_SERVER_PORT_NUMBER);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream());
                clientOutputStreams.add(printWriter);
                ExecutorService exec = Executors.newSingleThreadExecutor();
                exec.execute(new ReadFromClient(clientSocket));

            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }


    }

    private void sendToAllClients(String message) {
        // Send message to each connected client
        System.out.println("Writing to clients.....");
        System.out.println(message);
        clientOutputStreams.forEach(writer -> {
            PrintWriter printWriter = (PrintWriter) writer;
            printWriter.println(message);
            printWriter.flush();
        });
    }

    private class ReadFromClient implements Runnable {
        BufferedReader bufferedReader;
        Socket clientSocket;

        /**
         * Connect to and read input from a client
         * @param socket Client connecting Socket
         */
        private ReadFromClient(Socket socket) {
            try {
                clientSocket = socket;
                InputStreamReader inputStreamReader = new InputStreamReader(clientSocket.getInputStream());
                bufferedReader = new BufferedReader(inputStreamReader);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        /**
         * When an object implementing interface <code>Runnable</code> is used
         * to create a thread, starting the thread causes the object's
         * <code>run</code> method to be called in that separately executing
         * thread.
         * <p>
         * The general contract of the method <code>run</code> is that it may
         * take any action whatsoever.
         *
         * @see Thread#run()
         */
        @Override
        public void run() {
            System.out.println("Reading from client");
            String message = null;
            try {
                while ((message = bufferedReader.readLine()) != null) {
                    System.out.println(message);
                    sendToAllClients(message);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }


}
