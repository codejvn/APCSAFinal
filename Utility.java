public class Utility extends Sellable{//class to define the utlity space in the board
  private String name;
  private Player owner;
  private int cost;
  
  public Utility(String name, int x, int y){
    super(name, 150,x,y);
  }
   public String getInfo() {//overriding
    return super.getInfo() + ", Rent: Varies";
  }
}