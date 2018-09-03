package chapter12;

import javax.swing.*;
import java.awt.*;

public class SimpleGui1C {
    JFrame frame;
    MyDrawPanel panel;

    public void go() {
        frame = new JFrame();
        panel = new MyDrawPanel();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setVisible(true);

       frame.getContentPane().add(panel);

    }

    public static void main() {
        SimpleGui1C gui = new SimpleGui1C();
        gui.go();
    }
}


class MyDrawPanel extends JPanel {
    public void paintComponent(Graphics g) {

        //g.fillRect(20, 50, 25, 25); // 20 pixels from the left edge, 50 pixels from the top edge
        //g.setColor(Color.orange);
        g.fillRect(0, 0, this.getWidth(), this.getHeight()); // paint a black background

        // paint a random coloured circle
        int red = (int) (Math.random() * 255);
        int green = (int) (Math.random() * 255);
        int blue = (int) (Math.random() * 255);

        Color color1 = new Color(red, blue, green);
        g.setColor(color1);
        g.fillOval(70, 70, 50, 50);

        Graphics2D g2d = (Graphics2D) g;

        red = (int) (Math.random() * 255);
        green = (int) (Math.random() * 255);
        blue = (int) (Math.random() * 255);

        Color startColour = new Color(red, blue, green);

        red = (int) (Math.random() * 255);
        green = (int) (Math.random() * 255);
        blue = (int) (Math.random() * 255);

        Color endColour = new Color(red, blue, green);

        GradientPaint gradient = new GradientPaint(150, 150, startColour, 280, 280, endColour);
        g2d.setPaint(gradient);
        g2d.fillOval(150, 150, 50, 50);

    }
}
