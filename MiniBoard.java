package UltimateTicTacToe;

class MiniBoard {

	/* Static Variables */
	public static final byte EMPTY = 0;
	public static final byte X = 1;
	public static final byte O = 2;

	/* Instance Variables */
	public final int BOARD_SIZE;
	private byte[][] board;
	private byte result;
	private int numMoves;

	/* Constructors */
	public MiniBoard(int size) { // Initialize MiniBoard
		BOARD_SIZE = size;
		board = new byte[BOARD_SIZE][BOARD_SIZE];
		for (int c = 0; c < board.length; c++) {
			for (int d = 0; d < board[0].length; d++) {
				board[c][d] = MiniBoard.EMPTY;
			}
		}
		result = MiniBoard.EMPTY;
		numMoves = 0;
	}

	/* Instance Methods */
	public byte getPosition(MyPoint position) {
		if (result == MiniBoard.EMPTY) {
			return board[position.miniDown][position.miniRight];
		} else {
			return result;
		}
	}

	public byte placeStone(MyPoint placement, byte type) {
		board[placement.miniDown][placement.miniRight] = type;
		System.out.println(placement.boardDown+","+placement.boardRight+","+placement.miniDown+","+placement.miniRight);
		numMoves++;
		result = Referee.checkIfWon(board, BOARD_SIZE);
		return result;

		// if ((result != MiniBoard.EMPTY) || (board[placement.mD][placement.mR] != MiniBoard.EMPTY)) { //illegal
		// 	return -1; //Control code for illegal placement
		// } else { //legal
		// }
	}

	public boolean isFull() {
		if (numMoves == (BOARD_SIZE * BOARD_SIZE)) {
			return true;
		} else {
			return false;
		}
	}
}
