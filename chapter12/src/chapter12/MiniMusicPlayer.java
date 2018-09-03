package chapter12;

import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ControllerEventListener;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Sequence;
import javax.sound.midi.Track;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MiniMusicPlayer implements ActionListener {

    private JFrame frame;
    private JButton button;
    private MusicPanel musicPanel;

    public static void main() {
        MiniMusicPlayer musicPlayer = new MiniMusicPlayer();
        musicPlayer.setGui();

    }

    private void setGui() {
        frame = new JFrame("Music Player");
        button = new JButton("Start Playing");
        musicPanel = new MusicPanel();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setVisible(true);

        button.addActionListener(this);

        frame.getContentPane().add(BorderLayout.SOUTH,button);
        frame.getContentPane().add(BorderLayout.CENTER, musicPanel);

    }

    private void play() {
        try {
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequencer.addControllerEventListener(musicPanel, new int[] {127});
            Sequence sequence = new Sequence(Sequence.PPQ, 4);
            Track track = sequence.createTrack();

            for (int i = 0; i < 60; i += 4) {
                int randInt = (int) ((Math.random() * 50) + 1);
                track.add(makeMidiEvent(144, 1, randInt, 100, i));
                track.add(makeMidiEvent(176, 1, 127, 0, i));
                track.add(makeMidiEvent(128, 1, randInt, 100, i + 2));

            }

            sequencer.setSequence(sequence);
            sequencer.start();
            sequencer.setTempoInBPM(120.0f);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private MidiEvent makeMidiEvent(int command, int channel, int data1, int data2, int time) {
        MidiEvent event = null;

        try {
            ShortMessage msg = new ShortMessage();
            msg.setMessage(command, channel, data1, data2);
            event = new MidiEvent(msg, time);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return event;
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    public void actionPerformed(ActionEvent e) {
        play();
    }

    class MusicPanel extends JPanel implements ControllerEventListener {
        private boolean controllerEventTriggered = false;

        /**
         * Invoked when a {@link Sequencer} has encountered and processed a
         * control-change event of interest to this listener. The event passed in is
         * a {@code ShortMessage} whose first data byte indicates the controller
         * number and whose second data byte is the value to which the controller
         * was set.
         *
         * @param event the control-change event that the sequencer encountered in
         *              the sequence it is processing
         * @see Sequencer#addControllerEventListener(ControllerEventListener, int[])
         * @see MidiChannel#controlChange(int, int)
         * @see ShortMessage#getData1
         * @see ShortMessage#getData2
         */
        public void controlChange(ShortMessage event) {
            setControllerEventTriggered(true);
            musicPanel.repaint();
        }

        public void setControllerEventTriggered(boolean controllerEventTriggered) {
            this.controllerEventTriggered = controllerEventTriggered;
        }

        public boolean isControllerEventTriggered() {
            return controllerEventTriggered;
        }

        public void paintComponent(Graphics g) {
            if (isControllerEventTriggered()) {


                int red = (int) (Math.random() * 255);
                int green = (int) (Math.random() * 255);
                int blue = (int) (Math.random() * 255);

                int x = (int) (Math.random() * 200);
                int y = (int) (Math.random() * 200);

                int height = (int) (Math.random() * 150);
                int width = (int) (Math.random() * 150);
                Color color = new Color(red, green, blue);
                g.setColor(color);
                g.fillRect(x, y, width, height);

                setControllerEventTriggered(false);

            }
        }
    }

}
