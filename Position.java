public class Position{//base class used to define spots on the board
  private int x;
  private int y;
  public Position(int x, int y){
    this.x = x;
    this.y = y;
  }
  public int getX(){
    return x;
  }
  public int getY(){
    return y;
  }
}