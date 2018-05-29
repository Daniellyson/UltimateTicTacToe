//package UltimateTicTacToe;

import java.awt.Point;

//Types
//0 = empty
//1 = X
//2 = O

class MiniBoard {
//  Members
  private byte[][] board_ = new byte[Uttt.board_size_][Uttt.board_size_];
  //private char[] types_ = new char[2];
  private byte result_ = Uttt.E;
  //private char empty_space_ = Uttt.empty_space_;

//  Methods
  public MiniBoard() {
    //types_[0] = 'X';
    //types_[1] = 'O';

    for(int c=0; c< board_.length;c++) {
      for(int d=0; d< board_[0].length;d++) {
        board_[c][d] = Uttt.E;
      }
    }
    //System.out.println("board 00 should be: - and is: " + board_[0][0]);
  }

  public byte getPosition(Point coordinates) {
    if(result_ == 0) {
      return board_[coordinates.x][coordinates.y];
    } else {
      return result_;
    }
  }

  public byte placeStone(Point placement, byte type) {
    System.out.println("Placing stone");
    if ((result_ != 0) || (board_[placement.x][placement.y] != Uttt.E)) {
      //illegal
      //System.out.println("illegal placement");
      return -1;
    } else {
      //legal
      //System.out.println("legal placement");
      board_[placement.x][placement.y] = type;
      result_= Game.checkIfWon(board_);
      return result_;
    }
  }
}
