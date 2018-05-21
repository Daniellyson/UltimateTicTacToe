//Types....
// 0 = empty
// 1 = X
// 2 = O

class Miniboard {
  private byte[] board;
  private byte result;
  private int[] nummoves;

  public Miniboard() {
    board = new byte[3][3];
    result = 0;
    nummoves = new int[2];
  }

  public byte get (int x, int y) {
    if(result==0) {
      return board[x][y];
    }
    else {
      return result;
    }
  }

  public int setX (int x, int y, byte type) {
    if ( result!=0 || (board[x][y]) != 0 || type<1 || type>2) {
      return -1; //illegal
    }
    else {
      board[x][y] = type;
      nummoves[type-1]++;
      if ( nummoves[0]>=3 || nummoves[1]>=3 ) {
        result = Game.checkifwon(board);
      }
      return 0 //legal
    }
  }
}
