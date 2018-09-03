import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class ChatClient extends Application {
    private TextArea incoming;
    private TextArea outgoing;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;
    private Socket client;

    private Scene scene;
    private BorderPane mainPane;
    private Button sendButton;




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
        // Instantiate the instance variables
        primaryStage.setTitle("Chat Client");
        mainPane = new BorderPane();
        incoming = new TextArea();
        outgoing = new TextArea();
        sendButton = new Button("Send Message");

        // Set properties for outgoing/incoming
        incoming.setPrefRowCount(15);
        incoming.setPrefColumnCount(50);
        incoming.setEditable(false);
        outgoing.setPrefRowCount(6);
        outgoing.setPrefColumnCount(20);

        Font textBoxFont = Font.font("sanserif", FontWeight.BOLD, 18);
        incoming.setWrapText(true);
        incoming.setFont(textBoxFont);
        outgoing.setWrapText(true);
        outgoing.setFont(textBoxFont);


        ScrollPane incomingScroller = new ScrollPane(incoming);
        incomingScroller.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        incomingScroller.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        ScrollPane outgoingScroller = new ScrollPane(outgoing);
        outgoingScroller.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        outgoingScroller.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        VBox incomingBox = new VBox();
        incomingBox.getChildren().add(new Label("Chat"));
        incomingBox.getChildren().add(incomingScroller);
        VBox outgoingBox = new VBox();
        outgoingBox.getChildren().add(new Label("Enter Message"));
        outgoingBox.getChildren().add(outgoingScroller);


        // Add event handler to the sendButton
        sendButton.setOnAction(this::sendButtonAction);
        // Add incoming, outgoing, & sendButton to the mainPane
        HBox clientBox = new HBox();
        clientBox.getChildren().add(outgoingScroller);
        clientBox.getChildren().add(sendButton);
        mainPane.setCenter(incomingBox);
        mainPane.setBottom(clientBox);


        // Set up the connection to the Server
        connectToServer();

        // Set up the thread to read from the servers socket stream
        ExecutorService exec = Executors.newSingleThreadExecutor();
        exec.execute(this::readFromServer);

        // Set the properties of the primary stages
        scene = new Scene(mainPane);
        primaryStage.setScene(scene);
        primaryStage.setHeight(400);
        primaryStage.setWidth(500);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * Writes the contents of Input text area to the Chat client Server.
     * @param event ActionEvent triggered when the user clicks the sendButton
     *
     */
    private void sendButtonAction(ActionEvent event) {
        writeToServer();
        outgoing.clear();
        outgoing.requestFocus();
    }


    private void connectToServer() {
        try {
            client = new Socket(ChatServer.CHAT_SERVER_IP_ADDRESS, ChatServer.CHAT_SERVER_PORT_NUMBER);
            InputStreamReader inputStreamReader = new InputStreamReader(client.getInputStream());
            bufferedReader = new BufferedReader(inputStreamReader);
            printWriter = new PrintWriter(client.getOutputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Write the contents of the 'Enter Message' box to the server
     */
    private void writeToServer() {
        try {
            printWriter.println(outgoing.getText());
            printWriter.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Read from the chat servers
     */
    private void readFromServer() {
        incoming.appendText(bufferedReader.lines().collect(Collectors.joining("")) + "\n");
    }


}
