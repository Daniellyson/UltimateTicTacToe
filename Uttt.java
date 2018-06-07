package UltimateTicTacToe;

import java.util.Scanner;

class Uttt {
  //  Members
  public static Scanner scanner_;
  public static int control_ = 0;
  public static boolean is_gui_active_ = false;
  public static Gui gui_;
  
//  Constants
  public static boolean start_gui_;
  public static final int board_size_ = 3;
  public static final int cell_size_ = 50;
  public static final byte E = 0; //empty
  public static final byte X = 1;
  public static final byte O = 2;
  

  //  Methods
  public static void main(String[] args) {
    scanner_ = new Scanner(System.in);
    System.out.println("Welcome! To the game of Ultimate Tic Tac Toe");
    String command;
	  Game game;
	  
	  start_gui_ = true;
    if(start_gui_ == true) {
      gui_ = new Gui();
      gui_.initGui();
      while(control_ == 0) {
        try{
          Thread.sleep(1000);
        } catch(InterruptedException ex) {
          Thread.currentThread().interrupt();
        }
      }
      gui_.setVisible(false);//making the frame visible
      System.out.println("Awake again, Gui has ended it seems");
    } else {
      while(control_ == 0) {
        System.out.println("");
        System.out.println("So what you wanna do ? (for example type 'start' to start)");
        System.out.println("->start: Start a new game");
        System.out.println("->rules: How to play");
        System.out.println("->quit: Quit the game, works while playing too");
        System.out.print("Enter your choice: ");
  
//              get input
        command = scanner_.nextLine();
        command = command.toLowerCase();
  
//              process input
        switch(command) {
          case "start" :
//                    create game object, start
            game = new Game();
            System.out.println("");
            System.out.println("Starting game...");
            control_ = game.start();
            break;
          case "rules" :
//            JFrame rules = gui.initRules();
//            menu.setVisible(false);//making the frame visible 
//            rules.setVisible(true);//making the frame visible 
            System.out.println("");
            System.out.println("Rules");
  		      System.out.println("-----");
  		      System.out.println("Each small 3-by-3 tic-tac-toe board is referred to as a local board, and the larger 3-by-3 board is referred to as the global board.");
      		  System.out.println("The game starts with X playing wherever they want in any of the 81 empty spots. This move 'sends' their opponent to its relative location. For example, if X played in the top right square of their local board, then O needs to play next in the local board at the top right of the global board. O can then play in any one of the nine available spots in that local board, each move sending X to a different local board.");
  		      System.out.println("If a move is played so that it is to win a local board by the rules of normal tic-tac-toe, then the entire local board is marked as a victory for the player in the global board.");
  		      System.out.println("Once the outcome of a local board is decided (win or draw), no more moves may be played in that board. If a player is sent to such a board, then that player may play in any other board.");
  		      System.out.println("Game play ends when either a player wins the global board, or there are no legal moves remaining.");
            break;
          case "quit" :
            System.out.println("");
            System.out.println("Quitting");
            control_ = 1;
            break;
          default :
            System.out.println("No such option. Try again");
        }
//        menu.setVisible(true);//making the frame visible 
      }
  
      //System.out.println("Control code: " + control); // whats the point of this, it will always be 1
    }
    end();
  }

  public static void end() {
      gui_ = null;
      System.out.println("Thank you for playing!");
  }

  public static int getBoardSize() {
    return board_size_;
  }
}
