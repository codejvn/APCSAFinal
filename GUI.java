import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JFrame;

public class GUI extends JFrame {
  // class to handle all gui stuff
  private ImageIcon board;
  private MarkerManager markers;
  private StartingScreen starting;
  private Game g;

  public GUI() {
    super("GUI");
    starting = new StartingScreen(this);
    g = new Game(starting.getNumOfPlayers());
    Container c = getContentPane();
    // c.setLayout(mgr);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    markers = new MarkerManager(g.getPlayersArr());
    g.setMarkerManager(markers);
    // c.add(markers);
    // getContentPane().add(markers);
    this.setContentPane(markers);
    // setLocationRelativeTo(null);

    // pack();
    this.setSize(markers.getBackgroundSize());
    this.setLocationRelativeTo(null);
    setVisible(true);
    System.out.print("Ready to start? (y/n): ");
    String s = Game.getStringInput();
    while (!(s.equals("y") || s.equals("Y"))) {
      System.out.print("Ready to start? (y/n): ");
      s = Game.getStringInput();
    }
    g.start();
    // System.out.println("Is it visible?: " + isVisible());
    // System.out.println("Size?: " + this.getSize());
    // System.out.println("preferred size?: " + this.getPreferredSize());
  }

  public static void main(String[] args) {
    // GUI gui = new GUI();
    // gui.setVisible(true);
    new GUI();
  }
}