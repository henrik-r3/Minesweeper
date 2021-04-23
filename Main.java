import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import java.awt.Toolkit;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Minesweeper");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1,1);
        frame.setLocation(0,0);
        frame.setResizable(false);
        frame.setVisible(false);

        JList list = new JList(new String[] { "Leicht", "Mittel", "Schwer" });

        JOptionPane.showConfirmDialog(null, list, "Wähle einen Schwierigkeitsgrad aus!\n", JOptionPane.PLAIN_MESSAGE);

        String difficulty = Arrays.toString(list.getSelectedIndices());

        if (difficulty.contains("0")) {
            frame.setSize(500,550);
        } else if (difficulty.contains("1")) {
            frame.setSize(800,850);
        } else if (difficulty.contains("2")) {
            frame.setSize(1000,1050);
        } else {
            frame.setSize(500,550);
        }

        frame.setLocation(
            (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2 - (int) frame.getSize().getWidth() / 2,
            (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2
                    - (int) frame.getSize().getHeight() / 2);
                    frame.setVisible(true);

        new Game(frame, difficulty);
        
    }

}
