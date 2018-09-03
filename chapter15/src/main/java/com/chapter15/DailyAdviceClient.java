package com.chapter15;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;


public class DailyAdviceClient extends Application {
    private Scene scene;
    private BorderPane mainPane;
    private TextArea adviceTextArea;
    private Button adviceButton;
    private Socket socket;
    private InputStreamReader streamReader;
    private BufferedReader bufferedReader;
    final static private String ADVICE_SERVER_IP_ADDRESS = "127.0.0.1";
    final static private short ADVICE_SERVER_PORT_NUMBER = 5050;

    final private int WIDTH = 750;
    final private int HEIGHT = 500;


    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     *
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     *
     * @param primaryStage the primary stage for this application, onto which
     *                     the application scene can be set. The primary stage will be embedded in
     *                     the browser if the application was launched as an applet.
     *                     Applications may create other stages, if needed, but they will not be
     *                     primary stages and will not be embedded in the browser.
     * @throws Exception if something goes wrong
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Instantiate Object
        primaryStage.setTitle("Daily Advice App");
        mainPane = new BorderPane();
        scene = new Scene(mainPane, WIDTH, HEIGHT);
        adviceTextArea = new TextArea();
        adviceTextArea.setPrefColumnCount(20);
        adviceTextArea.setPrefRowCount(20);
        ScrollPane adviceScroller = new ScrollPane(adviceTextArea);
        adviceButton = new Button("Get Advice");

        // Set adviceTextArea properties
        adviceTextArea.setWrapText(true);
        adviceTextArea.setFont(Font.font("sanserif", FontWeight.BOLD, 24));
        // Set the Scroller properties
        adviceScroller.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        adviceScroller.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        // Add event handlers
        adviceButton.setOnAction(new AdviceClientEventHandler());
        // Add Button and Scroll pane to the main pane
        mainPane.setCenter(adviceScroller);
        mainPane.setBottom(adviceButton);
        // set the stage properties
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setWidth(WIDTH);
        primaryStage.setHeight(HEIGHT);
        primaryStage.show();

    }

    private  void connectToAdviceServer() {
        try {
            socket = new Socket(ADVICE_SERVER_IP_ADDRESS, ADVICE_SERVER_PORT_NUMBER);
            streamReader = new InputStreamReader(socket.getInputStream());
            bufferedReader = new BufferedReader(streamReader);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void closeConnection() {
        try {
            bufferedReader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }




    private class AdviceClientEventHandler implements EventHandler<ActionEvent> {

        /**
         * Invoked when a specific event of the type for which this handler is
         * registered happens.
         *
         * @param event the event which occurred
         */
        @Override
        public void handle(ActionEvent event) {
            // Clear any Text displayed on the adviceTextArea
            adviceTextArea.clear();
            // Get Advice stream from the server and add to the display
            try {
                connectToAdviceServer();
                adviceTextArea.setText(bufferedReader.readLine());
                bufferedReader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
