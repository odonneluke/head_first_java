package com.chapter15;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class DailyAdviceServer {

    final static private short ADVICE_SERVER_PORT_NUMBER = 5050;

    public static void startAdviceServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(ADVICE_SERVER_PORT_NUMBER);

            while (true) {
                Socket client = serverSocket.accept();
                PrintWriter printWriter = new PrintWriter(client.getOutputStream());
                printWriter.println(getAdvice());
                printWriter.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static String getAdvice() {
        return AdviceSlipRequest.parseSlipResponse(AdviceSlipRequest.GetSlipAdvice()).getAdvice();
    }

    public static void main(String[] args) {
        DailyAdviceServer.startAdviceServer();
    }


}
