package UltimateTicTacToe;

class Referee {

	/*Static Methods*/
	public static boolean isPlacementAllowed(MyPoint placement, MyPoint last, boolean anywhere, Board board) {
		if (last == null) { // first move
			return true;
		}
		if (board.getPosition(placement) != MiniBoard.EMPTY) {
			return false;
		}
		if (!anywhere) {
			if ((placement.boardDown != last.miniDown) || (placement.boardRight != last.miniRight)) {
				return false;
			}
		}
		return true;
	}

	public static boolean canIPlayAnywhere(MyPoint last, Board board) {
		if ((board.getVirtualPosition(last.miniDown, last.miniRight) == MiniBoard.EMPTY) && !(board.getIsFullPosition(last.miniDown, last.miniRight)) ) {
			return false;
		}
		return true;
	}

	public static byte checkIfWon(byte[][] b, int size) {
		byte t;
		for (int i = 0; i < size; i++) { // check horizontal 1 by 1
			t = b[i][0];
			if (t == MiniBoard.EMPTY) {
				continue;
			}
			for (int j = 1; j < size; j++) {
				if (b[i][j] != t) {
					break;
				}
				if (j == (size - 1)) {
					return t;
				}
			}
		}

		for (int i = 0; i < size; i++) { // check vertical 1 by 1
			t = b[0][i];
			if (t == MiniBoard.EMPTY) {
				continue;
			}
			for (int j = 1; j < size; j++) {
				if (b[j][i] != t) {
					break;
				}
				if (j == (size - 1)) {
					return t;
				}
			}
		}
		t = b[0][0];
		if (t != MiniBoard.EMPTY) {
			for (int j = 1; j < size; j++) { //check diagonal 1
				if (b[j][j] != t) {
					break;
				}
				if (j == (size - 1)) {
					return t;
				}
			}
		}
		t = b[0][size - 1];
		if (t != MiniBoard.EMPTY) {
			for (int j = 1; j < size; j++) { //check diagonal 2
				if (b[j][((size - 1) - j)] != t) {
					break;
				}
				if (j == (size - 1)) {
					return t;
				}
			}
		}
		return MiniBoard.EMPTY;
	}
}
