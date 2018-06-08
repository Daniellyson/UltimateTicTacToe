package UltimateTicTacToe;

import javax.swing.*;
import java.awt.Font;

class MatchLocal {
	/*Instance Variables*/
	public final int BOARD_SIZE;
	private String[] playerName;

	private Board board;
	private Gui gui;

	private int moves;
	private byte turn;
	private boolean processing;
	private boolean decided;

	/* Constructors */
	public MatchLocal(int size, String[] name) {
		BOARD_SIZE = size;

		playerName = name;
		board = new Board(BOARD_SIZE);
		processing = true;
		decided = false;
		moves = 0; // Number of moves played
		turn = MiniBoard.X; // whose turn is next
		gui = new Gui(BOARD_SIZE, playerName, this);
	}

	/* Static Methods */
	public static void main(String[] args) {
		String[] s = new String[2];
		s[0] = "me";
		s[1] = "you";
		MatchLocal match = new MatchLocal(3, s);
		match.start();
	}

	/* Instance Methods */

	public void start() {
		gui.syncGui(board, true, null);
		processing = false;
	}

	public void moveplaced(MyPoint placement) {
		processing = true;
		byte temp;
		temp = board.placeStone(placement, turn);
		if (temp != MiniBoard.EMPTY) {
			end(temp);
		}
		moves++;
		updateTurn();
		gui.syncGui(board, Referee.canIPlayAnywhere(placement, board), placement);
		processing = false;
	}

	public void end(byte winner) {
		decided = true;
		JFrame frame = new JFrame("We have a winner");
		JLabel label = new JLabel("Winner : " + playerName[winner - 1]);
		label.setFont(new Font("Serif", Font.BOLD, 30));
		frame.getContentPane().add(label);
		frame.setSize(500, 200);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public boolean isProcessing() {
		return processing;
	}

	public boolean isDecided() {
		return decided;
	}

	private void updateTurn() {
		if (turn == MiniBoard.X) {
			turn = MiniBoard.O;
		} else {
			turn = MiniBoard.X;
		}
	}

	public byte getTurn() {
		return turn;
	}

	public int getMoves() {
		return moves;
	}
}
