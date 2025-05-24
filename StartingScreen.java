import javax.swing.JOptionPane;
import javax.swing.JFrame;

import java.beans.PropertyChangeEvent;
import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
//class to implement the starting screen that asks for the num of players playing
public class StartingScreen {
  private JFrame frame;
  private JDialog dialog;
  private JOptionPane optionPane;
  private String input;

  public StartingScreen(JFrame f) {
    this.frame = f;
    dialog = new JDialog(frame, "Start", true);
    String[] options = { "2", "3", "4", "5", "6", "7", "8" };
    ImageIcon i;
    try {// rendering the dialog depending on if the image can be rendered
      Image img = ImageIO.read(new File("Images/PersonIcon.png")).getScaledInstance(50, 70, Image.SCALE_DEFAULT);
    i = new ImageIcon(img);
      input = (String)JOptionPane.showInputDialog(frame, "Please select the number of players:", "Start",
          JOptionPane.INFORMATION_MESSAGE, i, options, options[0]);
    } catch (Exception e) {
      input = (String)JOptionPane.showInputDialog(frame, "Please select the number of players:", "Start",
          JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
    }
  }

  public int getNumOfPlayers() {
    return Integer.parseInt(input);
  }

}