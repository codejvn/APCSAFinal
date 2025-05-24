///class to handle the markers used to represent the players in the game
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.swing.JComponent;

public class Marker extends JComponent{
  private Image image;
  private static int STARTING_POS_X = 23;
  private static int STARTING_POS_Y = 23;
  private Image look;
  private int x;
  private int y;
  
  public Marker(Image b, int x, int y) {
    look = b;
    this.x = x;
    this.y = y;
  }
  @Override
  public void paintComponent(Graphics g){//maybe add smth to check if image is null b4 trying to do smth?
  // System.out.println("paintcomponent in marker");
    g.drawImage(look, x, y, null);
  }
  public void setX(int x){
    this.x = x;
  }
  public void setY(int y){
    this.y = y;
  }
  // public void paintMarker(Graphics g){
  //   //change this later to add a limit to how many players there are
  //   //also set coordinates at some point
  //   for(Image i: markers){
  //   g.drawImage(i, 0, 0, null);
  //     }
  // }
  
}