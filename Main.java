import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import java.awt.Toolkit;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Minesweeper");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        //frame.setUndecorated(true);
        frame.setVisible(true);

        JList list = new JList(new String[] {"Leicht", "Mittel", "Schwer"});
        
        JOptionPane.showConfirmDialog(
            null, list,
            "Wähle einen Schwierigkeitsgrad aus!\n",
            JOptionPane.PLAIN_MESSAGE);

        String difficulty = Arrays.toString(list.getSelectedIndices());

        new Game(difficulty);
        Game.instance.calculateBombs(); 
    }

    
    
}
