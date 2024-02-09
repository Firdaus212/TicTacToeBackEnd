package main;

import javax.swing.SwingUtilities;

public class TicTacToeApplication {
	
	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GameInitializer();
            }
        });
    }
}
