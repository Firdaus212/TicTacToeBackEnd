package main;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CheckRowAndColumn {
	public void checkWin(int boardSize, JButton[] buttons) {
		String[][] board = new String[boardSize][boardSize];
		int index = 0;
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				board[i][j] = buttons[index++].getText();
			}
		}

		if (checkRows(board, boardSize, buttons) || checkColumns(board, boardSize, buttons)
				|| checkDiagonals(board, boardSize, buttons)) {
			endGame(buttons);
		}
	}

	public void checkDraw(int boardSize, JButton[] buttons, JFrame frame) {
		boolean isBoardFull = true;
		for (JButton button : buttons) {
			if (button.getText().isEmpty()) {
				isBoardFull = false;
				break;
			}
		}
		if (isBoardFull) {
			JOptionPane.showMessageDialog(frame, "The game is a draw! Would you like to play again?");
			restartGame(frame);
		}
	}

	private void endGame(JButton[] buttons) {
		for (JButton button : buttons) {
			button.setEnabled(false);
		}
	}

	private boolean checkRows(String[][] board, int boardSize, JButton[] buttons) {
		for (int i = 0; i < boardSize; i++) {
			String firstCell = board[i][0];
			boolean win = true;
			for (int j = 1; j < boardSize; j++) {
				if (!firstCell.equals(board[i][j]) || firstCell.isEmpty()) {
					win = false;
					break;
				}
			}
			if (win) {
				highlightWinningCells(i, 0, 0, 1, boardSize, buttons);
				return true;
			}
		}
		return false;
	}

	private boolean checkColumns(String[][] board, int boardSize, JButton[] buttons) {
		for (int i = 0; i < boardSize; i++) {
			String firstCell = board[0][i];
			boolean win = true;
			for (int j = 1; j < boardSize; j++) {
				if (!firstCell.equals(board[j][i]) || firstCell.isEmpty()) {
					win = false;
					break;
				}
			}
			if (win) {
				highlightWinningCells(0, i, 1, 0, boardSize, buttons);
				return true;
			}
		}
		return false;
	}

	private boolean checkDiagonals(String[][] board, int boardSize, JButton[] buttons) {
		String firstCell = board[0][0];
		boolean win = true;
		for (int i = 1; i < boardSize; i++) {
			if (!firstCell.equals(board[i][i]) || firstCell.isEmpty()) {
				win = false;
				break;
			}
		}
		if (win) {
			highlightWinningCells(0, 0, 1, 1, boardSize, buttons);
			return true;
		}

		firstCell = board[0][boardSize - 1];
		win = true;
		for (int i = 1; i < boardSize; i++) {
			if (!firstCell.equals(board[i][boardSize - i - 1]) || firstCell.isEmpty()) {
				win = false;
				break;
			}
		}
		if (win) {
			highlightWinningCells(0, boardSize - 1, 1, -1, boardSize, buttons);
			return true;
		}

		return false;
	}

	private void highlightWinningCells(int startRow, int startCol, int rowIncrement, int colIncrement, int boardSize,
			JButton[] buttons) {
		for (int i = 0; i < boardSize; i++) {
			buttons[startRow * boardSize + startCol].setBackground(Color.GREEN);
			startRow += rowIncrement;
			startCol += colIncrement;
		}
	}

	private void restartGame(JFrame frame) {
		frame.dispose();
		new GameInitializer();
	}
}