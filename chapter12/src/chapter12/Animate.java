package chapter12;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionListener;
public class Animate {
    private int x = 1;
    private int y = 1;
    private JFrame frame;
    private AnimatePanel panel;

    public static void main() {
        Animate gui = new Animate();
        gui.go();
    }

    private void go() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new AnimatePanel();
        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.setSize(500, 270);
        frame.setVisible(true);

        for (int i = 0; i < 124; i++, x++, y++) {
            x++;
            panel.repaint();
            try {
                Thread.sleep(50);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private class AnimatePanel extends JPanel {

        public void paintComponent(Graphics g) {
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, 500, 250);
            g.setColor(Color.BLUE);
            g.fillRect(x, y, 500 - x * 2, 250 - y * 2);
        }
    }
}
