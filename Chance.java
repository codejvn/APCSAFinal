public class Chance extends Position{//class to define the chance spaces on the board
  public Chance(int x, int y){
    super(x,y);
  }
  public int getChance(){
    double check = Math.random();
    if (check > 0.5){
      return (int) (Math.random() * -201);
    }
    else{
      return (int) (Math.random() * 201);
    }
  }
}