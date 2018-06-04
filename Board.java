package UltimateTicTacToe;

import java.awt.Point;

class Board {
//  Members
  private MiniBoard[][] uboard_ = new MiniBoard[Uttt.board_size_][Uttt.board_size_];
  public byte[][] status_ = new byte[Uttt.getBoardSize()][Uttt.board_size_];
  public static char[] disp_ = {'.','X','O'};
  public Gui gui_;

//  Methods
  public Board() {
    for (int i = 0 ; i < Uttt.board_size_ ; i++) {
      for (int j = 0 ; j < Uttt.board_size_ ; j++) {
        uboard_[i][j] = new MiniBoard();
      }
    }
  }

  public int placeStone(Point point, byte type) { //here 'point' is 9x9 limit(0 to 8)
    byte result;
    // '/'=chooses the Miniboard ; '%'=where in the chosen MiniBoard
    Point pp = new Point(point.x % Uttt.board_size_,  point.y % Uttt.board_size_);
    result = uboard_[point.x / Uttt.board_size_][point.y / Uttt.board_size_].placeStone( pp  , type);
    
    if (result > 0) {
      int x = point.x / Uttt.board_size_;
      int y = point.y / Uttt.board_size_;
      for(int c=0; c < Uttt.board_size_; c++) {
        for(int d=0; d < Uttt.board_size_; d++) {
          Uttt.gui_.cells_[c + Uttt.board_size_ * x][d + Uttt.board_size_ * y].setCell();
        }
      }
    }
    if (result != Uttt.E) {
      status_[point.x / Uttt.board_size_][point.y / Uttt.board_size_] = result;
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
        System.out.print( disp_[ getPosition(new Point(d, c)) ] );
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
