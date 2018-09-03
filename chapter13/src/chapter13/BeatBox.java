package chapter13;

import javax.sound.midi.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BeatBox {

    private JFrame frame;
    private JPanel mainPanel;
    private BorderLayout layout;
    private GridLayout grid;
    private JPanel background;
    private Box buttonBox;
    private Box nameBox;
    private JButton start;
    private JButton stop;
    private JButton increaseTempo;
    private JButton decreaseTempo;
    private Sequencer sequencer;
    private Sequence sequence;
    private Track track;
    ArrayList<JCheckBox> checkBoxList;
    private String[] instrumentNames = {"Bass Drum", "Closed Hi-Hat", "Open Hi-Hat", "Acoustic Snare", "Crash Cymbal",
            "Hand Clap", "High Tom", "High Bongo", "Maracas", "Whistle", "Low Conga",
            "Cowbell", "Vibraslap", "Low-mid Tom", "High Agogo", "Open Hi Congo"};
    int[] instruments = {35, 42, 26, 38, 49, 39, 50, 60, 70, 72, 64, 56, 58, 47, 67, 63};

    public static void main() {
        BeatBox beatBox = new BeatBox();
        beatBox.buildGui();
    }

    private void buildGui() {
        // instantiate gui objects
        frame = new JFrame("BeatBox App");
        layout = new BorderLayout();
        grid = new GridLayout(16, 16);
        grid.setHgap(2);
        grid.setVgap(1);
        mainPanel = new JPanel(grid);
        background = new JPanel(layout);
        buttonBox = new Box(BoxLayout.Y_AXIS);
        nameBox = new Box(BoxLayout.Y_AXIS);
        start = new JButton("Start Playing");
        stop = new JButton("Stop Playing");
        increaseTempo = new JButton("Increase Tempo");
        decreaseTempo = new JButton("Decrease Tempo");
        checkBoxList = new ArrayList<JCheckBox>();

        // Set background border
        background.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // register listeners
        start.addActionListener(new StartListener());
        stop.addActionListener(new StopListener());
        increaseTempo.addActionListener(new IncreaseTempoListener());
        decreaseTempo.addActionListener(new DecreaseTempoListener());
        // Add buttons to buttonBox
        buttonBox.add(start);
        buttonBox.add(stop);
        buttonBox.add(increaseTempo);
        buttonBox.add(decreaseTempo);
        // Add instrument labels to nameBox
        for (String instrument : instrumentNames) {
            nameBox.add(new Label(instrument));
        }
        // Add buttonBox and nameBox to the background panel
        background.add(BorderLayout.EAST, buttonBox);
        background.add(BorderLayout.WEST, nameBox);

        // Add background panel to the frame
        frame.getContentPane().add(background);

        // Add 256 check boxes to the checkBoxList and main panel
        for (int i = 0; i < 256; i++) {
            JCheckBox checkBox = new JCheckBox();
            checkBox.setSelected(false);
            checkBoxList.add(checkBox);
            mainPanel.add(checkBox);
        }
        // Add main panel to the background panel
        background.add(BorderLayout.CENTER, mainPanel);
        // Setup the sequencer, sequence, and track
        setUpMidi();
        // Set frame parameters
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

    public void setUpMidi() {
        try {
            // get sequencer, sequence, and track
            sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequence = new Sequence(Sequence.PPQ, 4);
            track = sequence.createTrack();
            sequencer.setTempoInBPM(120.0f);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void buildTrackAndStart() {
        int[] trackList;
        sequence.deleteTrack(track); // remove old track and create a new one
        track = sequence.createTrack();
        for (int i = 0; i < instruments.length; i++) { // repeat for each of the 16 rows/instruments. instruments contains the MIDI number
            trackList = new int[instruments.length];
            int key = instruments[i]; //  instruments contains the MIDI number
            for (int j = 0; j < instruments.length; j++) { // for each beat in the row
                // If the checkbox has been selected put the value in the trackList array.
                // Otherwise the instrument isn't mean to play so set to 0
                JCheckBox checkBox = checkBoxList.get(j + (instruments.length * i));

                if (checkBox.isSelected()) {
                    System.out.println("Key " + key + "has been added");
                    trackList[j] = key;
                } else {
                    trackList[j] = 0;
                }

            }
            createTracks(trackList); // for this instrument and all 16 beats, make events and add them to the track
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


    }

    private class StartListener implements ActionListener {

        /**
         * Invoked when an action occurs.
         *
         * @param e the event to be processed
         *          <p>
         *          Causes the BeatBox to begin playing
         */
        public void actionPerformed(ActionEvent e) {
            System.out.println("Start playing");
            buildTrackAndStart();
        }
    }

    private class StopListener implements ActionListener {
        /**
         * Invoked when an action occurs.
         *
         * @param e the event to be processed
         *          Causes the BeatBox to stop playing
         */
        public void actionPerformed(ActionEvent e) {
            System.out.println("Stop playing");
            sequencer.stop();
        }
    }

    private class IncreaseTempoListener implements ActionListener {
        /**
         * Invoked when an action occurs.
         *
         * @param e the event to be processed
         *          Increases the tempo of the music being played
         */
        public void actionPerformed(ActionEvent e) {
            float tempo = sequencer.getTempoFactor();
            sequencer.setTempoFactor(tempo * 1.03f);
            System.out.println("Increase Tempo");
        }
    }

    private class DecreaseTempoListener implements ActionListener {
        /**
         * Invoked when an action occurs.
         *
         * @param e the event to be processed
         *          <p>
         *          Decreases the tempo of the music being played
         */
        public void actionPerformed(ActionEvent e) {
            float tempo = sequencer.getTempoFactor();
            sequencer.setTempoFactor(tempo * 0.97f);
            System.out.println("Decrease Tempo");
        }
    }

    private void createTracks(int[] list) {
        // create beats for a single instrument. If the value is zero, don't play the instrument
        // at that beat
        for (int i = 0; i < list.length; i++) {
            int key = list[i];
            if (key != 0) {
                // Create the note on and note off events and add them to the track
                track.add(createEvent(144, 9, key, 100, i));
                System.out.println("Track added");
                track.add(createEvent(128, 9, key, 100, i + 1));
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


}
