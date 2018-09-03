package chapter12;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleAnimation implements ActionListener {

    private JFrame frame;
    private JButton actionButton;
    private DrawPanel panel;
    private int x = 25;
    private int y = 25;

    public static void main() {
        SimpleAnimation gui = new SimpleAnimation();
        gui.go();
    }

    private void go() {
        frame = new JFrame();
        actionButton = new JButton("Begin Animation");
        panel = new DrawPanel();

        // Set frame defaults
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setVisible(true);

        // Register listener on button
        actionButton.addActionListener(this);

        // Add button and pane to content
        frame.getContentPane().add(BorderLayout.SOUTH, actionButton);
        frame.getContentPane().add(BorderLayout.CENTER, panel);
    }


    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    public void actionPerformed(ActionEvent e) {

            x = 25;
            y = 25;

        for (int i = 0; i < 250; i++) {
            x++;
            y++;

            panel.repaint();

            try {

                Thread.sleep(25);

            } catch (Exception ex) {

                System.out.println("Thread error " + ex);
                ex.printStackTrace();

            }
        }
    }

    class DrawPanel extends JPanel {

        public void paintComponent(Graphics g) {
            g.setColor(Color.white);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());

            g.setColor(Color.BLUE);
            g.fillOval(x, y, 50, 50);
        }
    }
}
