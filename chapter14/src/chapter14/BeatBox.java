package chapter14;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.sound.midi.*;
import java.io.*;
import java.util.*;

public class BeatBox extends Application {
    private Scene scene;
    private Pane mainPanel;
    private BorderPane background;
    private GridPane gridPane;
    private VBox buttonBox;
    private VBox nameBox;
    private Button start;
    private Button stop;
    private Button reset;
    private Button randomize;
    private Button increaseTempo;
    private Button decreaseTempo;
    private Sequencer sequencer;
    private Sequence sequence;
    private Track track;
    private MenuBar menuBar;
    private Menu menu;
    private MenuItem openMenuItem;
    private MenuItem saveMenuItem;
    private VBox menuBox;
    private ArrayList<CheckBox> checkBoxes;

    final private String[] INSTRUMENT_NAMES = {"Bass Drum", "Closed Hi-Hat", "Open Hi-Hat", "Acoustic Snare", "Crash Cymbal",
            "Hand Clap", "High Tom", "High Bongo", "Maracas", "Whistle", "Low Conga",
            "Cowbell", "Vibraslap", "Low-mid Tom", "High Agogo", "Open Hi Congo"};
    final private int[] INSTRUMENT_CODE = {35, 42, 26, 38, 49, 39, 50, 60, 70, 72, 64, 56, 58, 47, 67, 63};
    final private int WIDTH = 750;
    final private int HEIGHT = 500;
    final private Border EMPTY_BORDER = new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));


    @Override
    public void start(Stage primaryStage) {
        // Instantiate Objects
        primaryStage.setTitle("BeatBox App");
        background = new BorderPane();
        scene = new Scene(background, WIDTH, HEIGHT);
        gridPane = new GridPane();
        gridPane.setVgap(1.0);
        gridPane.setHgap(2.0);
        gridPane.setAlignment(Pos.TOP_LEFT);
        gridPane.setPadding(new Insets(10, 20, 10, 20));
        mainPanel = new Pane(gridPane);
        buttonBox = new VBox();
        buttonBox.setSpacing(2.5);
        buttonBox.setAlignment(Pos.TOP_CENTER);
        buttonBox.setPadding(new Insets(10, 20, 10, 20));
        nameBox = new VBox();
        nameBox.setSpacing(2.5);
        nameBox.setAlignment(Pos.TOP_CENTER);
        nameBox.setPadding(new Insets(10, 20, 10, 20));
        start = new Button("Begin");
        start.setMaxWidth(Double.MAX_VALUE);
        stop = new Button("End");
        stop.setMaxWidth(Double.MAX_VALUE);
        reset = new Button("Reset");
        reset.setMaxWidth(Double.MAX_VALUE);
        randomize = new Button("Randomize Pattern");
        randomize.setMaxWidth(Double.MAX_VALUE);
        increaseTempo = new Button("Increase Tempo");
        decreaseTempo = new Button("Decrease Tempo");
        increaseTempo.setMaxWidth(Double.MAX_VALUE);
        decreaseTempo.setMaxWidth(Double.MAX_VALUE);
        checkBoxes = new ArrayList<CheckBox>();
        menuBar = new MenuBar();
        menu = new Menu("File");
        openMenuItem = new MenuItem("Open");
        saveMenuItem = new MenuItem("Save");
        menu.getItems().add(openMenuItem);
        menu.getItems().add(saveMenuItem);
        menuBar.getMenus().add(menu);
        menuBox = new VBox(menuBar);
        background.setTop(menuBox);



        // set background border
        background.setBorder(EMPTY_BORDER);
        // register event handlers
        start.setOnAction(new StartHandler());
        stop.setOnAction(new StopHandler());
        reset.setOnAction(new ResetHandler());
        randomize.setOnAction(new RandomisePatternHandler());
        increaseTempo.setOnAction(new IncreaseTempoHandler());
        decreaseTempo.setOnAction(new DecreaseTempoHandler());
        openMenuItem.setOnAction(new OpenFilerHandler());
        saveMenuItem.setOnAction(new SaveFileHandler());
        // Add buttons to the buttonBox
        buttonBox.getChildren().add(start);
        buttonBox.getChildren().add(stop);
        buttonBox.getChildren().add(reset);
        buttonBox.getChildren().add(randomize);
        buttonBox.getChildren().add(decreaseTempo);
        buttonBox.getChildren().add(increaseTempo);
        // Add instrument labels to the namebox
        for (String instrument : INSTRUMENT_NAMES) {
            nameBox.getChildren().add(new Label(instrument));
        }
        // Add buttonBox and nameBox to the background
        background.setRight(buttonBox);
        background.setLeft(nameBox);

        // Add 256 checkbox to checkBoxes
        for (int i = 0; i < INSTRUMENT_CODE.length; i++) {
            for (int j = 0; j < INSTRUMENT_CODE.length; j++) {
                CheckBox checkBox = new CheckBox();
                checkBox.setSelected(false);
                checkBoxes.add(checkBox);
                gridPane.add(checkBox, i, j);
            }
        }
        // Add mainPanel to the background
        background.setCenter(gridPane);
        // Setup the sequencer, sequence, and track
        setUpMidi();
        // set stage parameters
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setWidth(WIDTH);
        primaryStage.setHeight(HEIGHT);
        primaryStage.show();

    }



    public void setUpMidi() {
        // Get sequencer, sequence, and track
        try {
            sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequence = new Sequence(Sequence.PPQ, 4);
            track = sequence.createTrack();
            sequencer.setTempoInBPM(120.0f);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    };
    public void buildTrackAndStart() {
        int[] trackList;
        sequence.deleteTrack(track); // Remove old track and create a new one
        track = sequence.createTrack();
        for (int i = 0; i < INSTRUMENT_CODE.length; i++) { // Repeat for each of the 16 rows/instruments
            trackList = new int[INSTRUMENT_CODE.length];
            int key = INSTRUMENT_CODE[i]; // Get the MIDI number
            for (int j = 0; j < INSTRUMENT_CODE.length; j++) {
                // If the Checkbox has been selected put the value into the trackList array
                // Otherwise the instrument isn't meant to play so set it to 0
                CheckBox checkBox = checkBoxes.get(j + (INSTRUMENT_CODE.length * i));
                trackList[j] = checkBox.isSelected() ? key : 0;
            }
            createTracks(trackList); // For this instrument add all 16 beats, make events and add them to the track
            track.add(createEvent(176, 1, 127, 0, 16));
        }
        // Add an event at beat 16 otherwise the BeatBox might not go the full 16 beats before it starts over
        track.add(createEvent(192, 9, 1, 0, 15));
        try {
            sequencer.setSequence(sequence);
            sequencer.setLoopCount(sequencer.LOOP_CONTINUOUSLY);
            sequencer.start();
            sequencer.setTempoInBPM(120);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    };
    private void createTracks(int[] list) {
        // Create beats for a single instrument. If the value is zero don't play the instrument at that beat
        for (int i = 0; i < list.length; i++) {
            if (list[i] != 0) {
                // Create the note on, note off events and add them to the track
                track.add(createEvent(144, 9, list[i], 100, i));
                track.add(createEvent(128, 9, list[i], 100, i + 1));
            }
        }
    }




    private MidiEvent createEvent(int command, int channel, int data1, int data2, int time) {
        MidiEvent event = null;
        try {
            ShortMessage message = new ShortMessage();
            message.setMessage(command, channel, data1, data2);
            event = new MidiEvent(message, time);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return event;
    }

    private void serializeObject(File file, Object object) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(object);
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }
    private void deserializeObject(File file) {
       boolean[] checkBoxState = new boolean[checkBoxes.size()];
       try {
           FileInputStream fileInputStream = new FileInputStream(file);
           ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
           checkBoxState = (boolean[]) objectInputStream.readObject();

       } catch (Exception ex) {
           ex.printStackTrace();
       }
       for (int i = 0; i < checkBoxState.length; i++) {
            CheckBox checkBox = (CheckBox) checkBoxes.get(i);
            if (checkBoxState[i]) {
                checkBox.setSelected(true);
            } else {
                checkBox.setSelected(false);
            }
       }
       sequencer.stop();
       buildTrackAndStart();
    }

    // Handlers

    private class StartHandler implements EventHandler<ActionEvent> {

        /**
         * Invoked when a specific event of the type for which this handler is
         * registered happens.
         *
         * @param event the event which occurred
         */
        @Override
        public void handle(ActionEvent event) {
            buildTrackAndStart();
        }
    }

    private class StopHandler implements EventHandler<ActionEvent> {

        /**
         * Invoked when a specific event of the type for which this handler is
         * registered happens.
         *
         * @param event the event which occurred
         */
        @Override
        public void handle(ActionEvent event) {
            sequencer.stop();
        }
    }

    private class ResetHandler implements EventHandler<ActionEvent> {
        /**
         * Invoked when a specific event of the type for which this handler is
         * registered happens.
         *
         * @param event the event which occurred
         */
        @Override
        public void handle(ActionEvent event) {
            sequencer.stop();
            // Set all the checkboxes to off
            for (CheckBox checkBox : checkBoxes) {
                checkBox.setSelected(false);
            }
        }
    }

    private class RandomisePatternHandler implements EventHandler<ActionEvent> {
        /**
         * Invoked when a specific event of the type for which this handler is
         * registered happens.
         *
         * @param event the event which occurred
         */
        @Override
        public void handle(ActionEvent event) {
            sequencer.stop();
            // Set all the checkboxes to off
            for (CheckBox checkBox : checkBoxes) {
                if (Math.random() > 0.5) {
                    checkBox.setSelected(false);
                } else {
                    checkBox.setSelected(true);
                }
            }
        }
    }

    private class IncreaseTempoHandler implements EventHandler<ActionEvent> {

        /**
         * Invoked when a specific event of the type for which this handler is
         * registered happens.
         *
         * @param event the event which occurred
         */
        @Override
        public void handle(ActionEvent event) {
            sequencer.setTempoFactor(sequencer.getTempoFactor() * 1.03f);
        }
    }

    private class DecreaseTempoHandler implements EventHandler<ActionEvent> {

        /**
         * Invoked when a specific event of the type for which this handler is
         * registered happens.
         *
         * @param event the event which occurred
         */
        @Override
        public void handle(ActionEvent event) {
            sequencer.setTempoFactor(sequencer.getTempoFactor() * 0.97f);
        }
    }

    private class OpenFilerHandler implements EventHandler<ActionEvent> {
        // bring up a file dialog box
        // let the user navigate to and choose a beat box pattern to open
        /**
         * Invoked when a specific event of the type for which this handler is
         * registered happens.
         *
         * @param event the event which occurred
         */
        @Override
        public void handle(ActionEvent event) {
            File wkdir = new File(System.getProperty("user.dir"));
            FileChooser deserialize = new FileChooser();
            deserialize.setTitle("Deserialize Beatbox Pattern");
            deserialize.setInitialDirectory(wkdir);
            deserialize.getExtensionFilters().add(new FileChooser.ExtensionFilter("SER", "*.ser"));
            deserializeObject(deserialize.showOpenDialog(null));
        }
    }

    private class SaveFileHandler implements EventHandler<ActionEvent> {
        // bring up a file dialog box
        // user saves the current beat box pattern to file
        /**
         * Invoked when a specific event of the type for which this handler is
         * registered happens.
         *
         * @param event the event which occurred
         */
        @Override
        public void handle(ActionEvent event) {
            boolean[] checkBoxState = new boolean[checkBoxes.size()];
            for (int i = 0; i < checkBoxes.size(); i++) {
                CheckBox state = (CheckBox) checkBoxes.get(i);
                checkBoxState[i] = state.isSelected();
            }
            File wkdir = new File(System.getProperty("user.dir"));
            FileChooser serialize = new FileChooser();
            serialize.setTitle("Serialize Beatbox Pattern");
            serialize.setInitialDirectory(wkdir);
            serialize.setInitialFileName("Checkbox.ser");
            serializeObject(serialize.showSaveDialog(null), checkBoxState);

        }
    }



}



