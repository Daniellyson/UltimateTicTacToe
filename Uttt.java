package UltimateTicTacToe;

import java.util.Scanner; 

class Uttt {
//  Members
  public static Scanner scanner_;
  public static final int board_size_ = 3;
  
//  Methods
  public static void main(String[] args) {
    scanner_ = new Scanner(System.in);  
    System.out.println("Welcome! To the game of Ultimate Tic Tac Toe");
    int control = 0;
    String command;
      
    while(control == 0) {
      Game game;
      System.out.println("start: Start a new game");
      System.out.println("rules: How to play");
      System.out.println("quit: Quit the game");
      
//      get input
      System.out.println("Enter your next command");  
      command = scanner_.nextLine();  
      
//      process input    
      switch(command) {
        case "start" :
//          create game object, start
          game = new Game();
          System.out.println("start game");
          control = game.start();
          break;
        case "rules" :
          System.out.println("Rules");
          break;
        case "quit" :
          System.out.println("Quitting");
          control = 1;
          break;
        default :
          System.out.println("BUG");
      }
    }

    System.out.println("Control code: " + control);
    end();
  }
  
  public static void end() {
      System.out.println("Thank you for playing!");
  }
  
  public static int getBoardSize() {
    return board_size_;
  }
}
