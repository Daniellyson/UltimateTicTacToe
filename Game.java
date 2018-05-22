package UltimateTicTacToe;

import java.awt.Point;

class Game {
//  Members
  private String[] player_name_;
//  private String pOname;
  private int result_;
  private Board board_;
  private byte turn_;
  private Point play_zone_;

  
//  Methods
  public Game () {
    player_name_ = new String[2]; //1%2 = X, 0%2 = O
//    input name of both players and initialize instance variables
  }
  
  public void init() {
    board_ = new Board();    
    turn_ = 0; // 1%2= X, 0%2= O
    player_name_[0] = "O";
    player_name_[1] = "X";
    play_zone_ = new Point(-1, -1);
  }
  
  public int start() {
    System.out.println("init game");
    init();
    System.out.println("run game");
    return run();
  }
  
  public int run() {
    while (result_ == 0) {
      turn_++;
      System.out.println("Turn: " + turn_);
      board_.display();
      
//      if(result_ == 0) return 3;
      System.out.println(player_name_[turn_ % 2] + " it is your move");
      if(play_zone_.x != -1) {
        System.out.println("You have to play in row " + play_zone_.x + " and column " + play_zone_.y);
      } else {
        System.out.println("You can play everywhere!");
      }
      
//      get input
      Point placed_stone = getPlacement();
      
      if (placed_stone.x == -2) {
        return 1;
      }
      result_ = board_.placeStone(placed_stone, (byte)(turn_%2));
    }
    return 0;
  }
  
  public static byte checkIfWon(byte[][] b) {
//    returns 0(nobody), 1(X) or 2(O), depending on who won
    return 0;
  }
  
//  private methods
  private Point getPlacement() {
    System.out.println("Where do you want to play in field: row " + play_zone_.x + " and column " + play_zone_.y);
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
    System.out.println("X is:" + placement.x + " and y is: " + placement.y);
    
    if(placement.x < 0) return false;
    if(placement.y < 0) return false;
    if(placement.x >= (3 * Uttt.board_size_)) return false;
    if(placement.y >= (3 * Uttt.board_size_)) return false;
    if(board_.getPosition(placement) != Uttt.empty_space_) return false;
    return true;
  }
  
}
