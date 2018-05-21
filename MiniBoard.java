package UltimateTicTacToe;

import java.awt.Point;

//Types
//0 = empty
//1 = X
//2 = O

class MiniBoard {
//  Members
  private byte[][] board_;
  private byte result_;
  
//  Methods
  public MiniBoard() {
    board_ = new byte[Uttt.board_size_][Uttt.board_size_];
//    board_[0][0] = 1;
//    board_[0][1] = 2;
//    board_[0][2] = 3;
//    board_[1][0] = 4;
//    board_[1][1] = 5;
//    board_[1][2] = 6;
//    board_[2][0] = 7;
//    board_[2][1] = 8;
//    board_[2][2] = 9;
    result_ = 0;
  }
  
  public byte getPosition(Point coordinates) {
    if(result_ == 0) {
      return board_[coordinates.x][coordinates.y];
    } else {
      return result_;
    }
  }
  
  public int placeStone(Point placement, byte type) {
    if ((result_ != 0) || (board_[placement.x][placement.y] != 0)) {
//      illegal
      return -1;
    } else {
//      legal
      type++;
      board_[placement.x][placement.y] = type;
      return 0;
    }
  }
}
