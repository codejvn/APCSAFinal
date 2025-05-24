import java.awt.Frame;
import java.awt.Label;
import java.awt.Button;
//this class is purely smth made just to learn about GUI
public class LearningGUI extends Frame{
  public LearningGUI(){
    Label l = new Label("Game");
    Button b = new Button("counting");
    add(l);
    setVisible(true);
    setSize(300, 300);
  }
  public static void main(String args[]){
    new LearningGUI();
  }
}