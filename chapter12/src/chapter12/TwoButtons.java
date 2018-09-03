package chapter12;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TwoButtons {
    private JFrame frame;
    private JLabel label;
    private JButton actionButton;
    private JButton labelButton;
    private circlePane pane;

    public static void main() {
        TwoButtons gui = new TwoButtons();
        gui.go();
    }

    private void go() {
        frame = new JFrame();
        label = new JLabel("I'm a label");
        actionButton = new JButton("Change Circle");
        labelButton = new JButton("Change label");
        pane = new circlePane();

        // set frame defaults
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setVisible(true);
        // Register both buttons
        actionButton.addActionListener(new ColorListener());
        labelButton.addActionListener(new LabelListener());

        // Add label, pane, and buttons to frame
        frame.getContentPane().add(BorderLayout.SOUTH, actionButton);
        frame.getContentPane().add(BorderLayout.EAST, labelButton);
        frame.getContentPane().add(BorderLayout.WEST, label);
        frame.getContentPane().add(BorderLayout.CENTER, pane);



    }


    class LabelListener implements ActionListener {

        /**
         * Invoked when an action occurs.
         *
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            label.setText("New Label");
        }
    }

    class ColorListener implements ActionListener {

        /**
         * Invoked when an action occurs.
         *
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            pane.repaint();
        }
    }

}

class circlePane extends JPanel {
    public void paintComponent(Graphics g) {
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
         Graphics2D g2d = (Graphics2D) g;
         // Set random colour gradient
        int red = (int) (Math.random() * 255);
        int green = (int) (Math.random() * 255);
        int blue = (int) (Math.random() * 255);

        Color startCol = new Color(red, green, blue);
        red = (int) (Math.random() * 255);
        green = (int) (Math.random() * 255);
        blue = (int) (Math.random() * 255);
        Color endCol = new Color(red, green, blue);
        GradientPaint gradient = new GradientPaint(150, 150, startCol, 200, 200, endCol);
        g2d.setPaint(gradient);
        int x = (int) (Math.random() * 300);
        int y = (int) (Math.random() * 300);

        g2d.fillOval(x, y, 100, 100);
    }
}
