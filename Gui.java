package UltimateTicTacToe;

import java.awt.event.*;
import javax.swing.*;  

public class Gui {
  public JFrame menu_;
  public JFrame rules_;
  private JFrame board_;
  public Cell[][] cells_ = new Cell[Uttt.board_size_ * Uttt.board_size_][Uttt.board_size_ * Uttt.board_size_];;
  private Game game_;
  public int control_ = 0;
    
  public void initGui() {

    initMenu();
    initRules();
    
    Uttt.is_gui_active_ = true;
    menu_.setVisible(true);//making the frame visible
  }
  
  public int initMenu() {
    menu_ = new JFrame("Uttt menu");//creating instance of JFrame  
    
    JButton start = new JButton("Start");//creating instance of JButton  
    start.setBounds(100,100,200, 50);//x axis, y axis, width, height     
    start.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        game_ = new Game();
        assert game_ != null;
        game_.init();
        initBoard();
        menu_.setVisible(true);
        board_.setVisible(true);
      }  
    });         
    menu_.add(start);//adding button in JFrame  

    JButton rules = new JButton("Rules");//creating instance of JButton  
    rules.setBounds(100,200,200, 50);//x axis, y axis, width, height
    rules.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        menu_.setVisible(true);
        rules_.setVisible(true);
      }  
    });
    menu_.add(rules);//adding button in JFrame  
    
    JButton quit = new JButton("Quit");//creating instance of JButton  
    quit.setBounds(100,300,200, 50);//x axis, y axis, width, height  
    quit.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        Uttt.control_ = 2;
      }  
    });
    menu_.add(quit);//adding button in JFrame  
              
    menu_.setSize(400,500);//400 width and 500 height  
    menu_.setLayout(null);//using no layout managers  
    return 0;
  }
  
  public int initRules() {
    rules_ = new JFrame("Uttt rules");//creating instance of JFrame  
    
    JTextArea text = new JTextArea();
    text.setBounds(30,50, 600,400); 
    text.setText("Rules\n\n");
    text.append("Each small 3-by-3 tic-tac-toe board is referred to as a local board, \n");
    text.append("and the larger 3-by-3 board is referred to as the global board.\n"
              + "\n");
    text.append("The game starts with X playing wherever they want in any of the 81 empty spots.\n"
              + "This move 'sends' their opponent to its relative location.\n"
              + "\n"
              + "For example, if X played in the top right square of their local board, \n"
              + "then O needs to play next in the local board at the top right of the global board. \n"
              + "O can then play in any one of the nine available spots in that local board, \n"
              + "each move sending X to a different local board.\n"
              + "\n");
    text.append("If a move is played, so that it is to win a local board, \n" +
                "by the rules of normal tic-tac-toe, \n"
              + "then the entire local board is marked as a victory \n"
              + "for the player in the global board.\n"
              + "\n");
    text.append("Once the outcome of a local board is decided (win or draw), \n"
              + "no more moves may be played in that board. \n"
              + "If a player is sent to such a board, then that player may play in any other board.\n"
              + "Game play ends when either a player wins the global board, \n"
              + "or there are no legal moves remaining.\n");
    text.append("\n");  
    
    JButton back = new JButton("back");//creating instance of JButton  
    back.setBounds(650,100,100, 50);//x axis, y axis, width, height     
    back.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        rules_.setVisible(false);
        menu_.setVisible(true);
      }  
    });         
    rules_.add(back);//adding button in JFrame  
    
    rules_.add(text);//adding text in JFrame  
    rules_.setSize(800,500);//width and height  
    rules_.setLayout(null);//using no layout managers  
    return 0;
  }

  public int initBoard() {
    board_ = new JFrame("Uttt board");//creating instance of JFrame  
    
    JButton back = new JButton("back");//creating instance of JButton  
    back.setBounds(800,100,100, 50);//x axis, y axis, width, height     
    back.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        menu_.setVisible(true);
        board_.setVisible(false);
      }  
    });         
    board_.add(back);//adding button in JFrame  
    
    //buttons:
    int size = Uttt.board_size_;
    for(int x = 0; x < (size * size); x++) {
      for(int y = 0; y < (size * size); y++) {
        assert game_ != null;
        cells_[x][y] = new Cell(x, y, game_, this);
        board_.add(cells_[x][y].button_);//adding button in JFrame  
      }
    }   
    
    board_.setSize(1000,700);//400 width and 500 height  
    board_.setLayout(null);//using no layout managers  
    
    return 0;
  }
  
  public void setVisible(boolean is_visible) {
    if (menu_ != null) menu_.setVisible(is_visible);
    if (rules_ != null) rules_.setVisible(is_visible);
    if (board_ != null) board_.setVisible(is_visible);
  }
}
