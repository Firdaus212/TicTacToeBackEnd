package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TicTacToeGame implements ActionListener {
	private CheckRowAndColumn checkRowAndColumn;
	public JFrame frame = new JFrame();
	private JPanel button_panel = new JPanel();
	public JLabel textfield = new JLabel();
	public JButton[] buttons;
	public boolean player1_turn;
	private boolean singlePlayerMode;
	private int boardSize;
	private Random random = new Random();

	private JButton restartButton;
	private JButton modeButton;
	private String playerSymbol;

	public TicTacToeGame(int size, boolean singlePlayerMode, CheckRowAndColumn checkRowAndColumn) {
		this.boardSize = size;
		this.singlePlayerMode = singlePlayerMode;
		this.checkRowAndColumn = checkRowAndColumn;

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 800);
		frame.getContentPane().setBackground(new Color(50, 50, 50));
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);

		if (singlePlayerMode) {
			playerSymbol = choosePlayerSymbol();
		}

		textfield.setBackground(new Color(25, 25, 25));
		textfield.setForeground(new Color(25, 255, 0));
		textfield.setFont(new Font("Ink Free", Font.BOLD, 75));
		textfield.setHorizontalAlignment(JLabel.CENTER);
		textfield.setText("Tic-Tac-Toe");
		textfield.setOpaque(true);

		button_panel.setLayout(new GridLayout(size, size));
		button_panel.setBackground(new Color(150, 150, 150));

		buttons = new JButton[size * size];

		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new JButton();
			button_panel.add(buttons[i]);
			buttons[i].setFont(new Font("MV Boli", Font.BOLD, 120));
			buttons[i].setFocusable(false);
			buttons[i].addActionListener(this);
		}

		restartButton = new JButton("Restart");
		restartButton.addActionListener(e -> restartGame());

		modeButton = new JButton("Switch Mode");
		modeButton.addActionListener(e -> switchMode());

		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new FlowLayout());
		bottomPanel.add(restartButton);
		bottomPanel.add(modeButton);

		frame.add(textfield, BorderLayout.NORTH);
		frame.add(button_panel, BorderLayout.CENTER);
		frame.add(bottomPanel, BorderLayout.SOUTH);

		firstTurn();
	}

	private String choosePlayerSymbol() {
		String[] options = { "X", "O" };
		int choice = JOptionPane.showOptionDialog(null, "Choose your symbol", "Symbol Selection",
				JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		return options[choice];
	}

	public void firstTurn() {
		player1_turn = random.nextBoolean();
		if (singlePlayerMode) {
			textfield.setText("Single mode " + playerSymbol + " turn");
		} else {
			textfield.setText(player1_turn ? "X turn" : "O turn");
		}
		if (singlePlayerMode && !player1_turn) {
			JButton clickedButton = new JButton();
			clickedButton.setText("X");
			makeCPUMove(clickedButton);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton clickedButton = (JButton) e.getSource();
		if (clickedButton.getText().isEmpty()) {
			if (singlePlayerMode) {
				clickedButton.setText(playerSymbol == "X" ? "X" : "O");
				textfield.setText("Single mode " + playerSymbol + " turn");
				makeCPUMove(clickedButton);
			} else {
				clickedButton.setText(player1_turn ? "X" : "O");
				player1_turn = !player1_turn;
				textfield.setText(player1_turn ? "X turn" : "O turn");
			}

			checkRowAndColumn.checkWin(boardSize, buttons);
			checkRowAndColumn.checkDraw(boardSize, buttons, frame);
		}
	}

	public void makeCPUMove(JButton clickedButton) {
		ArrayList<Integer> emptyCells = new ArrayList<>();
		for (int i = 0; i < buttons.length; i++) {
			if (buttons[i].getText().isEmpty()) {
				emptyCells.add(i);
			}
		}

		if (!emptyCells.isEmpty()) {
			int randomIndex = random.nextInt(emptyCells.size());
			buttons[emptyCells.get(randomIndex)].setText(playerSymbol == "X" ? "O" : "X");
		}
	}

	public void restartGame() {
		frame.dispose();
		new GameInitializer();
	}

	public void switchMode() {
		frame.dispose();
		new GameInitializer();
	}
}
