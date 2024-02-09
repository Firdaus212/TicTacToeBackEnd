import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameInitializer  extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField sizeField;
    private JButton startButton;

    public GameInitializer() {
        setTitle("Tic Tac Toe Size Input");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1));

        JLabel sizeLabel = new JLabel("Enter the size of the board (e.g., 3 for 3x3):");
        sizeField = new JTextField();
        startButton = new JButton("Start Tic Tac Toe");

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startTicTacToe();
            }
        });

        add(sizeLabel);
        add(sizeField);
        add(startButton);

        setVisible(true);
    }

    private void startTicTacToe() {
        try {
            int size = Integer.parseInt(sizeField.getText());
            boolean singlePlayerMode = promptForGameMode();
            CheckRowAndColumn checkRowAndColumn = new CheckRowAndColumn();
            new TicTacToeGame(size, singlePlayerMode, checkRowAndColumn);
            dispose(); // Close the size input GUI
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for the size.");
        }
    }

    private boolean promptForGameMode() {
        int choice = JOptionPane.showConfirmDialog(this, "Do you want to play against CPU?", "Game Mode", JOptionPane.YES_NO_OPTION);
        return choice == JOptionPane.YES_OPTION;
    }
}