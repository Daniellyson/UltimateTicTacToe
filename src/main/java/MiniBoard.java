
import java.awt.Point;

//Types
//0 = empty
//1 = X
//2 = O

class MiniBoard {
//  Members
  private char[][] board_ = new char[Uttt.board_size_][Uttt.board_size_];
  private char[] types_ = new char[2];
  private char result_ = 0;
  private char empty_space_ = '·';
  
//  Methods
  public MiniBoard() {
    types_[0] = 'X';
    types_[1] = 'O';
    
    for(int c=0; c< board_.length;c++) {
      for(int d=0; d< board_[0].length;d++) {
        board_[c][d] = empty_space_;
      }
    }
    System.out.println("board 00 should be: - and is: " + board_[0][0]);
  }
  
  public char getPosition(Point coordinates) {
    if(result_ == 0) {
      return board_[coordinates.x][coordinates.y];
    } else {
      return result_;
    }
  }
  
  public int placeStone(Point placement, byte type) {
    System.out.println("Placing stone");
    if ((result_ != 0) || (board_[placement.x][placement.y] != empty_space_)) {
//      illegal
//      System.out.println("illegal placement");
      return -1;
    } else {
//      legal
//      System.out.println("legal placement");
      board_[placement.x][placement.y] = types_[type];
      return 0;
    }
  }
}
