import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

// API for buttons creation
public class Buttons {

    // returns new button with the specified arguments
    public JButton createButton(String label, String foregroundColor, String fontFamily, int fontSize) {
        // creates new button with label
        JButton btn = new JButton(label);
        // sets button fontfamily and font size
        btn.setFont(new Font(fontFamily, Font.BOLD, fontSize));
        // sets button text color
        btn.setForeground(Color.decode(foregroundColor));
        // returns button with properties
        return btn;
    }

    // listener that changes color on hover
    public MouseAdapter onHover(JButton btn, String colorOnHover, String colorNotHover) {
        // creates new button listener
        MouseAdapter MoA = new MouseAdapter() {
            // on hover action
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setForeground(Color.decode(colorOnHover));
            }

            // when not in hover action
            @Override
            public void mouseExited(MouseEvent e) {
                btn.setForeground(Color.decode(colorNotHover));
            }
        };
        // returns button with added properties
        return MoA;
    }
}