package chapter14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class DailyAdviceClient {
    public void startDailyAdviceClient() {
        try {
            Socket socket = new Socket("127.0.0.0.1", 5050);
            InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String advice = reader.readLine();
            System.out.println("Today you should:");

        } catch (IOException ex) {
            ex.printStackTrace();
        }



    }
}
