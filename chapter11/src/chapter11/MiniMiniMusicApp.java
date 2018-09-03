package chapter11;

import javax.sound.midi.*;

public class MiniMiniMusicApp {

    public static void main() {
        MiniMiniMusicApp mini = new MiniMiniMusicApp();
        mini.play();
    }

    private void play() {

        try {
            // 1. - Get a sequencer and open it
            Sequencer player = MidiSystem.getSequencer();
            player.open();
            // 2. Make a sequence
            Sequence sequence  = new Sequence(Sequence.PPQ, 4);
            // 3. Get a new track from the sequence (track lives in the sequence MIDI data lives in the track
            Track track = sequence.createTrack();
            // 4. Fill the track with MIDI events and give the track to the sequencer
            ShortMessage messageA = new ShortMessage(); // Make a message
            messageA.setMessage(144, 1, 44, 100); // put instruction in the message (play note 44)
            MidiEvent noteOn = new MidiEvent(messageA, 1); // create a new midi event
            track.add(noteOn); // add midi event to track
            player.setSequence(sequence); // add sequence to the sequencer

            ShortMessage messageB = new ShortMessage();
            messageB.setMessage(128, 1, 44, 100);
            MidiEvent noteOff = new MidiEvent(messageB, 16);
            track.add(noteOff);
            player.setSequence(sequence); // add sequence to the sequencer

            // Start
            player.start();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
