import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JPanel;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Dimension;

//class made to handle all of the markers together
public class MarkerManager extends JPanel {
  private Image[] markers;
  private static int STARTING_POS_X = 23;
  private static int STARTING_POS_Y = 23;
  private Image background;
  private int numOfMarkers;
  private  Marker[] pieces;
  private  Player[] players;

  public MarkerManager(Player[] ps) {
    super();
    try {
      Image[] m = {
          ImageIO.read(new File("Images/Circle.png")).getScaledInstance(20, 20, Image.SCALE_DEFAULT),
          ImageIO.read(new File("Images/Square.png")).getScaledInstance(20, 20, Image.SCALE_DEFAULT),
          ImageIO.read(new File("Images/Trapezoid.png")).getScaledInstance(20, 20, Image.SCALE_DEFAULT),
          ImageIO.read(new File("Images/Triangle.png")).getScaledInstance(20, 20, Image.SCALE_DEFAULT),
          ImageIO.read(new File("Images/Pentagon.png")).getScaledInstance(20, 20, Image.SCALE_DEFAULT),
          ImageIO.read(new File("Images/Cross.png")).getScaledInstance(20, 20, Image.SCALE_DEFAULT),
          ImageIO.read(new File("Images/Parallelogram.png")).getScaledInstance(20, 20, Image.SCALE_DEFAULT),
          ImageIO.read(new File("Images/Rhombus.png")).getScaledInstance(20, 20, Image.SCALE_DEFAULT)
      };
      markers = m;
      background = ImageIO.read(new File("Images/Board.jpg"));
      numOfMarkers = ps.length;
      pieces = new Marker[numOfMarkers];
      System.out.println("numMarkers is " + numOfMarkers);
      for (int i = 0; i < numOfMarkers; i++) {
        pieces[i] = new Marker(markers[i], ps[i].getX(), ps[i].getY());

        // System.out.println("reapainted");
      }
      repaint();
      players = ps;
      MarkerUpdate markerUpdate = new MarkerUpdate();
      Thread thread = new Thread(markerUpdate);
      thread.start();
    } catch (IOException e) {
      System.out.println("Sorry, but the program ran into an error.");
      markers = null;
      e.printStackTrace();
    }
  }
  public Dimension getBackgroundSize(){
   
    BufferedImage back = (BufferedImage)background;
     System.out.println("background size given by method: " + new Dimension(back.getWidth(), back.getHeight()));
    return new Dimension(back.getWidth(), back.getHeight()+20);
  }
  public void update() {
    for (int i = 0; i < pieces.length; i++) {
      pieces[i].setX(players[i].getX());
      pieces[i].setY(players[i].getY());
    }
    repaint();
  }

  public void paintComponent(Graphics g) {
    // System.out.println(background);
    // System.out.println("paintcomponent is called");
    super.paintComponent(g);
    g.drawImage(background, 0, 0, this);
    for (int i = 0; i < numOfMarkers; i++) {
      pieces[i].paintComponent(g);
    }
    // super.paint(g);
  }
  
  public void updateMarkers(){
    for (int i = 0; i < pieces.length; i++) {
          pieces[i].setX(players[i].getX());
          pieces[i].setY(players[i].getY());
        }
        repaint();
  }
  
  private class MarkerUpdate implements Runnable {
    public void run() {
      try {
        Thread.sleep(500);
        // System.out.println("zzzzz");
      } catch (Exception c) {
        // System.out.println("awake!");
        for (int i = 0; i < pieces.length; i++) {
          pieces[i].setX(players[i].getX());
          pieces[i].setY(players[i].getY());
        }
        repaint();
      }
    }
  }
}