//package UltimateTicTacToe;

import java.awt.Point;

class Board {
//  Members
  private MiniBoard[][] uboard_ = new MiniBoard[Uttt.board_size_][Uttt.board_size_];
  private byte[][] status_ = new byte[Uttt.getBoardSize()][Uttt.board_size_];
  private static char[] disp = {'.','X','O'};

//  Methods
  public Board() {
    for (int i = 0 ; i < Uttt.board_size_ ; i++) {
      for (int j = 0 ; j < Uttt.board_size_ ; j++) {
        uboard_[i][j] = new MiniBoard();
      }
    }
  }

  public int placeStone(Point point, byte type) { //here 'point' is 9x9 limit(0 to 8)
    byte temp;
    // '/'=chooses the Miniboard ; '%'=where in the chosen MiniBoard
    Point pp = new Point(point.x % Uttt.board_size_,  point.y % Uttt.board_size_);
    temp = uboard_[point.x / Uttt.board_size_][point.y / Uttt.board_size_].placeStone( pp  , type);
    if (temp != Uttt.E) {
      status_[point.x / Uttt.board_size_][point.y / Uttt.board_size_] = temp;
      return ( Game.checkIfWon(status_) );
    }
    return 0;
  }

  public void display() {
    System.out.println("The current Game position is:");
    System.out.println("");
    int size = Uttt.board_size_;
    for(int c=0; c < (size * size); c++) {
      System.out.print(c+1 + " ");
      for(int d= 0; d < (size * size); d++) {
        // display code is always ugly if you want to look it a certain way :C
        System.out.print(  disp[ getPosition(new Point(d, c)) ]  );
        if(((d%size) == size-1) && !(d == (size*size - 1))) System.out.print(" ");
      }
      if((c%size) == size-1) System.out.println("");
      System.out.println("");
    }
    System.out.print("+ ");
    for (int d=0; d< (size*size) ; d++ ) {
      System.out.print(d+1);
      if(((d%size) == size-1) && !(d == (size*size - 1))) System.out.print(" ");
    }
    System.out.println("");
    System.out.println("");
  }

  public byte getPosition(Point position) {
    int x = position.x;
    int y = position.y;
    int size = Uttt.board_size_;
    return uboard_[x/size][y/size].getPosition(new Point(x%Uttt.board_size_, y%Uttt.board_size_));
  }

  public byte getStatus(Point p) {
    return status_[p.x][p.y];
  }
}