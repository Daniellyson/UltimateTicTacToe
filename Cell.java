package UltimateTicTacToe;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;
import java.awt.Color;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

public class Cell {
  private Point coordinate_;
  public JButton button_;
  private Game game_;

  private Color background_default_ = Color.LIGHT_GRAY;
  private ImageIcon icon_x_;
  private ImageIcon icon_o_;
  
  
  public Cell(int x, int y, Game game, Gui gui) {
    game_ = game;
    coordinate_ = new Point(x, y);
    String name = String.valueOf(coordinate_.x) + "-" + String.valueOf(coordinate_.y);
    button_ = new JButton();
    button_.setOpaque(true);
    button_.setBackground(background_default_);

    try {
//      System.out.println("get icon");
//      the images should be in the folder src/UltimateTicTacToe/resources/
//      and the git folder is src/UltimateTicTacToe/
      URL url = getClass().getResource("./resources/icon_x_empty_small.png");
      File imageFile = new File(url.getPath());
      Image image_x = ImageIO.read(imageFile);
//      System.out.println("saved icon");
      icon_x_ = new ImageIcon(image_x);
    } catch (Exception ex) {
      System.out.println(ex);
    }
    
    try {
//    the images should be in the folder src/UltimateTicTacToe/resources/
//    and the git folder is src/UltimateTicTacToe/
      URL url = getClass().getResource("./resources/icon_o_empty_small.png");
      File imageFile = new File(url.getPath());
      Image image_o = ImageIO.read(imageFile);
      icon_o_ = new ImageIcon(image_o);
    } catch (Exception ex) {
      System.out.println(ex);
    }

    button_.setBounds((Uttt.cell_size_ + Uttt.cell_distance_) * x + (x / Uttt.board_size_ + 1) * Uttt.board_distance_,
        (Uttt.cell_size_ + Uttt.cell_distance_) * y + (y / Uttt.board_size_ + 1) * Uttt.board_distance_,
        Uttt.cell_size_, Uttt.cell_size_);// x axis, y axis, width, height

    button_.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println("button: " + name + " was pressed!");
        assert game_ != null;
        assert coordinate_ != null;

        if (game_.isPlacementAllowed(coordinate_) == true) {
          game_.result_ = game_.board_.placeStone(coordinate_, (byte) ((game_.turn_ % 2) + 1));
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

  public int setCell() {
    button_.setIcon(getCurrentIcon());
    button_.setBackground(getCurrentColor());
    return 0;
  }

  public int setPlayable(boolean set_flag) {
    LineBorder border = new LineBorder(Color.BLACK, 1);
    
    if ((game_.result_ == 0) && (set_flag == true) && (button_.getBackground() == background_default_)) {
       border = new LineBorder(getCurrentColor(), 5);
    }
    button_.setBorder(border);
    return 0;
  }

  public Color getCurrentColor() {
    if ((game_.turn_ % 2) == 0) {
      return Color.RED;
    } else {
      return Color.BLUE;
    }
  }

  public ImageIcon getCurrentIcon() {
    if ((game_.turn_ % 2) == 0) {
      return icon_x_;
    } else {
      return icon_o_;
    }
  }
}
