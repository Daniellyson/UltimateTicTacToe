package UltimateTicTacToe;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

public class Cell {
  private Point coordinate_;
  public JButton button_;
  private Game game_;
//  private Gui gui_;
  
  private Color background_default_ = Color.GREEN;
  
  public Cell(int x, int y, Game game, Gui gui) {
    game_ = game;
    assert game != null;
    assert game_ != null;
//    gui_ = gui;
    coordinate_ = new Point(x, y);
    String name = String.valueOf(coordinate_.x) + "-" + String.valueOf(coordinate_.y);
    button_ = new JButton(name);
    button_.setOpaque(true);
    button_.setBackground(background_default_);
    button_.setBounds(10 + 65*x + (x / Uttt.board_size_) * 20, 10 + 65*y + (y / Uttt.board_size_) * 20, 60, 60);//x axis, y axis, width, height     
    button_.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        System.out.println("button: " + name + " was pressed!");
        assert game_ != null;
        assert coordinate_ != null;
        
        if(game_.isPlacementAllowed(coordinate_) == true) {
          game_.result_ = game_.board_.placeStone(coordinate_, (byte)( (game_.turn_%2)+1 ));
          setCell();
          game_.turn_++;
          game_.updatePlayZone(coordinate_);
          game.board_.display();
        } else {
          System.out.println("Cell: Sry but this place is not allowed");
        }
        
        byte is_won = Game.checkIfWon(game_.board_.status_);
        if (is_won != Uttt.E) {
          System.out.println("game result is: " + game_.result_);
          System.out.println("the game is won");
        }
      }  
    });  
  }

  public int setCell(){
//    button_.setText(String.valueOf(game_.turn_%2));
    button_.setBackground(getCurrentColor());
    return 0;
  }
  
  public int setPlayable(boolean set_flag) {
    LineBorder border = new LineBorder(Color.BLACK, 1);;
    if((game_.result_ == 0) && (set_flag == true) && (button_.getBackground() == background_default_)) {
      border = new LineBorder(getCurrentColor(), 5);
    }
    button_.setBorder(border);
    return 0;
  }
  
  public Color getCurrentColor() {
    if((game_.turn_%2) == 0) {
      return Color.RED;
    } else {
      return Color.BLUE;
    }
  }
}

