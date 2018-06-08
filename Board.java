package UltimateTicTacToe;

class Board {
	/* Instance Variables */
	public final int BOARD_SIZE;
	private MiniBoard[][] board;
	private byte[][] virtualBoard;

	/* Constructors */
	public Board(int size) {
		BOARD_SIZE = size;

		board = new MiniBoard[BOARD_SIZE][BOARD_SIZE];
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				board[i][j] = new MiniBoard(BOARD_SIZE);
			}
		}

		virtualBoard = new byte[BOARD_SIZE][BOARD_SIZE];
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				virtualBoard[i][j] = MiniBoard.EMPTY;
			}
		}
	}

	/* Instance Methods */
	public byte placeStone(MyPoint placement, byte type) {
		byte temp;
		temp = board[placement.boardDown][placement.boardRight].placeStone(placement, type);
		if (temp != MiniBoard.EMPTY) {
			virtualBoard[placement.boardDown][placement.boardRight] = temp;
			return (Referee.checkIfWon(virtualBoard, BOARD_SIZE));
		}
		return MiniBoard.EMPTY;
	}

	public byte getPosition(MyPoint position) {
		return (board[position.boardDown][position.boardRight].getPosition(position));
	}

	public byte getVirtualPosition(MyPoint vposition) {
		return (virtualBoard[vposition.boardDown][vposition.boardRight]);
	}

	public byte getVirtualPosition(int bD, int bR){
		return(virtualBoard[bD][bR]);
	}
}
