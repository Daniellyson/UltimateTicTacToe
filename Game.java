package UltimateTicTacToe;

import java.awt.Point;

class Game {
  //  Members
  private String[] player_name_;
  //  private String pOname;
  private int result_;
  private Board board_;
  private byte turn_;
  private Point play_zone_; // a 3x3 telling whhats the next eligible zone


  //  Methods
  public Game () {
    player_name_ = new String[2]; //1%2 = X, 0%2 = O
    //    input name of both players and initialize instance variables
  }

  public void init() {
    board_ = new Board();
    turn_ = 0; // 1%2= X, 0%2= O
    player_name_[Uttt.X - 1] = "X"; //global constant -
    player_name_[Uttt.O - 1] = "O"; //global constant - 1
    play_zone_ = new Point(-1, -1); // this means, can play anywhere
  }

  public int start() {
    System.out.println("initialzing game...");
    init();
    System.out.println("Game has loaded, good luck...");
    return run();
  }

  public int run() {
    while (result_ == 0) {
      System.out.println("Turn: " + (turn_+1));
      board_.display();

      //      if(result_ == 0) return 3;
      System.out.println(player_name_[(turn_ % 2)] + " it is your move");
      if(play_zone_.x != -1) {
        System.out.println("You have to play in zone (" + (play_zone_.x+1) + "," + (play_zone_.y+1) + ")");
      } else {
        System.out.println("You can play anywhere!");
      }

      //      get input
      Point placed_stone = getPlacement(); // returns a point with 9x9 limit(0 to 8)

      if (placed_stone.x == -2) {
        return 1;
      }

      play_zone_.x = placed_stone.x % Uttt.board_size_; //set future playzone according to current placement
      play_zone_.y = placed_stone.y % Uttt.board_size_;
      if ( board_.getStatus(play_zone_)!=Uttt.E ) {
        play_zone_.x = -1;
        play_zone_.y = -1;
      }


      result_ = board_.placeStone(placed_stone, (byte)( (turn_%2)+1 ));
      turn_++;
      if ( result_!=Uttt.E ) {
        board_.display();
        System.out.println("We have a winner!! Player: " + player_name_[result_-1] );
      }
    }
    return 0;
  }
	//returns 0(nobody), 1(X) or 2(O), depending on who won
  public static byte checkIfWon(byte[][] b) {
    byte t;
    for (int i=0 ; i<Uttt.board_size_ ; i++ ) { // check horizontal 1 by 1
      t = b[i][0];
      if (t==Uttt.E) {
        continue;
      }
      for (int j=1 ; j<Uttt.board_size_ ; j++ ) {
        if ( b[i][j]!=t ) {
          break;
        }
        if (j == (Uttt.board_size_ - 1) ) {
          return t;
        }
      }
    }

    for (int i=0 ; i<Uttt.board_size_ ; i++ ) { // check vertical 1 by 1
      t = b[0][i];
      if (t==Uttt.E) {
        continue;
      }
      for (int j=1 ; j<Uttt.board_size_ ; j++ ) {
        if ( b[j][i]!=t ) {
          break;
        }
        if (j == (Uttt.board_size_ - 1) ) {
          return t;
        }
      }
    }
    //int i,j;
    t = b[0][0];
    if (t!=Uttt.E) {
      //count=1;
      //i=1;
      //j=1;
      for ( int j=1  ; j<Uttt.board_size_ ; j++ ) { //check diagonal 1
        if ( b[j][j]!=t ) {
          break;
        }
        if (j == (Uttt.board_size_ - 1) ) {
          return t;
        }
      }
    }
    t = b[0][Uttt.board_size_ - 1];
    if (t!=Uttt.E) {
      //count=1;
      //i = 1;
      //j = (Uttt.board_size_ - 2);
      for ( int j=1 ; j<Uttt.board_size_ ; j++ ) { //check diagonal 2
        if ( b[j][ ((Uttt.board_size_-1) - j) ]!=t ) {
          break;
        }
        if (j == (Uttt.board_size_ - 1) ) {
          return t;
        }
      }
    }
    return Uttt.E;
  }

  //  private methods
  private Point getPlacement() {
    //System.out.println("Where do you want to play in field: row " + play_zone_.x + " and column " + play_zone_.y);
    System.out.println("Where do you want to play( horizontal vertical)");
    boolean valid = false;
    String[] input;
    Point placement = null;
    do {
      input = Uttt.scanner_.nextLine().split(" ");
      if(input.length == 1 && input[0].equalsIgnoreCase("quit")) {
        System.out.println("Quitting the match.");
        placement = new Point(-2, -2);
        valid = true;
        break;
      }
      if(input.length != 2) {
        System.out.println("Your coordinates have to be split into 2 parts by a space, like this '2 3'.");
        continue;
      }
      try {
        placement = new Point(Integer.parseInt(input[0]) - 1, Integer.parseInt(input[1]) - 1);
      } catch (NumberFormatException e) {
        System.out.println("Those are not normal numbers it seems.");
        continue;
      }


      if(!isPlacementAllowed(placement)) {
        System.out.println("The place you want to play at is not allowed.");
        continue;
      }
      valid = true;
    } while(valid == false);
    assert placement != null;
    return placement;
  }

  private Boolean isPlacementAllowed(Point placement) {
    //System.out.println("X is:" + placement.x + " and y is: " + placement.y);

    if(placement.x < 0) return false;
    if(placement.y < 0) return false;
    if(placement.x >= (Uttt.board_size_ * Uttt.board_size_)) return false;
    if(placement.y >= (Uttt.board_size_ * Uttt.board_size_)) return false;
    if(board_.getPosition(placement) != Uttt.E) return false;
    if (play_zone_.x != -1) {
      //play_zone_= 0-2 ; placement= 0-8
      if ( ((placement.x/Uttt.board_size_)!=play_zone_.x) || ((placement.y/Uttt.board_size_)!=play_zone_.y) ) {
        System.out.println("No cheating! Play in specified playzone");
        return false;
      }
    }
    return true;
  }

}
