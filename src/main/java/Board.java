

import java.awt.Point;
import java.lang.reflect.Array;
import java.util.ArrayList;

class Board {
//  Members
//  private ArrayList<ArrayList<MiniBoard>> uboard = new ArrayList<>();
  private MiniBoard[][] uboard_ = new MiniBoard[Uttt.board_size_][Uttt.board_size_];
//  private byte[][] status_ = new byte[Uttt.getBoardSize()][Uttt.board_size_];

//  Methods
  public Board() {
    //init of the board
    for (int i = 0 ; i < Uttt.board_size_ ; i++) {
      for (int j = 0 ; j < Uttt.board_size_ ; j++) {
        uboard_[i][j] = new MiniBoard();
      }
    }
  }

  public int placeStone(Point point, byte type) {
    point.x--;
    point.y--;
    uboard_[point.x / Uttt.board_size_][point.y / Uttt.board_size_].placeStone(new Point(point.x % Uttt.board_size_,  point.y % Uttt.board_size_), type);
    return 0;
  }



  public void display() {
    System.out.println("The current Game position is:");
    System.out.println("");
    int size = Uttt.board_size_;
    System.out.println("  1 2 3     4 5 6      7 8 9");
    System.out.println("┌───────────┬───────────┬───────────┐");

    for(int row =0; row < (3 * size); row++) {

      for(int column= 0; column < (3 * size); column++) {

          if (column == 0) System.out.print("│");

        System.out.print(" "+getPosition(new Point(column, row))+" ");
          if (column == 8) System.out.print(" │");

        if(((column%size) == size-1) && !(column == (3*size - 1))) System.out.print(" │");
      }

      if((row %size) == size-1){
          if (row == 8){
              System.out.print("\n└───────────┴───────────┴───────────┘");
          }
          else{
              System.out.print("\n├───────────┼───────────┼───────────┤");
          }
      }
      System.out.println();
    }
  }
  
  public char getPosition(Point position) {
    int x = position.x;
    int y = position.y;
    int size = Uttt.board_size_;
    return uboard_[x/size][y/size].getPosition(new Point(x%3, y%3));
  }
}