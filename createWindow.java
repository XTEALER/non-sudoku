
import javax.swing.JOptionPane;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Color;

public class createWindow {
    JFrame Frame;

    // method constructor, use to create a new frame
    public createWindow(String header, String background, boolean resizable, int width, int height) {
        // creates new Jframe with title
        Frame = new JFrame(header);
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Frame.setMinimumSize(new Dimension(width, height));
        Frame.setBackground(Color.decode(background));
        Frame.setResizable(resizable);
    }

    // use for information messages
    public void msgScreen(String title, String body) {
        JOptionPane.showMessageDialog(Frame, body, title, JOptionPane.INFORMATION_MESSAGE);
        return;
    }

    // use for information messages
    public void msgScreen(Object[] labels) {
        JOptionPane.showMessageDialog(Frame, labels[1].toString(), labels[0].toString(),
                JOptionPane.INFORMATION_MESSAGE);
        return;
    }

    // use for error messages
    public void errorScreen(String title, String body) {
        JOptionPane.showMessageDialog(Frame, body, title, JOptionPane.ERROR_MESSAGE);
        return;
    }

    // returns jpanel with background and visible default
    public JPanel createPanel(String backgroundColor, boolean visible) {
        // create new panel with gridbaglayout
        JPanel panel = new JPanel(new GridBagLayout());
        // sets default background color
        panel.setBackground(Color.decode(backgroundColor));
        // sets visible property true or false
        panel.setVisible(visible);
        // returns JPanel with assigned properties
        return panel;
    }
}