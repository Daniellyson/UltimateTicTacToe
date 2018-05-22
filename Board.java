package UltimateTicTacToe;

import java.awt.Point;

class Board {
//  Members
  private MiniBoard[][] uboard_ = new MiniBoard[Uttt.board_size_][Uttt.board_size_];
  private byte[][] status_ = new byte[Uttt.getBoardSize()][Uttt.board_size_];

//  Methods
  public Board() {
    for (int i = 0 ; i < Uttt.board_size_ ; i++) {
      for (int j = 0 ; j < Uttt.board_size_ ; j++) {
        uboard_[i][j] = new MiniBoard();
      }
    }
  }

  public int placeStone(Point point, byte type) {
    byte temp;
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
    for(int c=0; c < (3 * size); c++) {
      for(int d= 0; d < (3 * size); d++) {
//        display code is always ugly if you want to look it a certain way :C
        System.out.print(getPosition(new Point(d, c)));
        if(((d%size) == size-1) && !(d == (3*size - 1))) System.out.print("-");
      }
      if((c%size) == size-1) System.out.println("");
      System.out.println("");
    }
  }

  public byte getPosition(Point position) {
    int x = position.x;
    int y = position.y;
    int size = Uttt.board_size_;
    return uboard_[x/size][y/size].getPosition(new Point(x%3, y%3));
  }

  public byte getStatus(Point p) {
    return status_[p.x][p.y];
  }
}
