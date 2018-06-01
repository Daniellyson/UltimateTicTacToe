package UltimateTicTacToe;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Cell {
  Point coordinate_;
  JButton button_;
  
  public Cell(int x, int y) {
    coordinate_ = new Point(x+1, y+1);
    String name = String.valueOf(coordinate_.x) + "-" + String.valueOf(coordinate_.y);
    button_ = new JButton(name);
    button_.setBounds(10 + 65*x, 10 + 65*y, 60, 60);//x axis, y axis, width, height     
    button_.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        System.out.println("button: " + name + " was pressed!");
      }  
    });  
  }
}
