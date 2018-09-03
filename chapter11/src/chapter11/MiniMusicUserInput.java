package chapter11;

import javax.sound.midi.*;
import java.util.Scanner;

public class MiniMusicUserInput {

    public static void main() {
        int[] musicArgs = getInstructions();
        int instrument = musicArgs[0];
        int note = musicArgs[1];
        System.out.println("Instrument value: " + instrument + "\nNote value: " + note);
        MiniMusicUserInput miniMusicUserInput = new MiniMusicUserInput();
        miniMusicUserInput.play(instrument, note);

    }

    public void play(int instrument, int note) {
        try {
            Sequencer player = MidiSystem.getSequencer();
            player.open();
            Sequence sequence = new Sequence(Sequence.PPQ, 4);
            Track track = sequence.createTrack();

            MidiEvent event = null;

            ShortMessage first = new ShortMessage();
            first.setMessage(192, 1, instrument, 0); // change instrument type to instrument
            MidiEvent changeInstrument = new MidiEvent(first, 1);

            ShortMessage a = new ShortMessage();
            a.setMessage(144, 1, note, 100); // Note on
            MidiEvent noteOn = new MidiEvent(a, 1);
            track.add(noteOn);

            ShortMessage b = new ShortMessage();
            b.setMessage(128, 1, note, 100); // Note off
            MidiEvent noteOff = new MidiEvent(b, 16);
            track.add(noteOff);
            player.setSequence(sequence);
            player.start();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static int[] getInstructions() {
        int[] musicArgs = new int[2];
        Scanner s = new Scanner(System.in);
        System.out.println("Enter Instrument Type (0 - 127) and Note (0-127)");
        for (int i = 0; i < musicArgs.length; i++) {
            musicArgs[i] = Integer.parseInt(s.nextLine());
        }
        return musicArgs;
    }
}
