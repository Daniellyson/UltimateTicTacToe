

import java.awt.Point;

class Board {
//  Members
  private MiniBoard[][] uboard_ = new MiniBoard[Uttt.board_size_][Uttt.board_size_];
//  private byte[][] status_ = new byte[Uttt.getBoardSize()][Uttt.board_size_];

//  Methods
  public Board() {
    for (int i = 0 ; i < Uttt.board_size_ ; i++) {
      for (int j = 0 ; j < Uttt.board_size_ ; j++) {
        uboard_[i][j] = new MiniBoard();
      }
    }
  }

  public int placeStone(Point point, int type) {
    point.x--;
    point.y--;
    uboard_[point.x / Uttt.board_size_][point.y / Uttt.board_size_].placeStone(new Point(point.x % Uttt.board_size_,  point.y % Uttt.board_size_), (byte)(type));
    return 0;
  }

  public void display() {
    System.out.println("The current Game position is:");
    System.out.println("");
    for(int c=0; c < (3 * Uttt.board_size_); c++) {
      for(int d= 0; d < (3 * Uttt.board_size_); d++) {
//        display code is always ugly if you want to look it a certain way :C
        System.out.print(uboard_[c/Uttt.board_size_][d/Uttt.board_size_].getPosition(new Point(c%3, d%3)));
        if(((d%Uttt.board_size_) == Uttt.board_size_-1) && !(d == (3*Uttt.board_size_ - 1))) System.out.print("-");
      }
      if((c%Uttt.board_size_) == Uttt.board_size_-1) System.out.println("");
      System.out.println("");
    }
  }
}