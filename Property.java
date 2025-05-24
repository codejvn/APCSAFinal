public class Property extends Sellable {//class used to define a property on the board(aka spaces w/colors)
  private int rent;
  
  public Property(String name, int cost, int rent, int x, int y) {
    super(name, cost, x, y);
    this.rent = rent;
  }
  public String getInfo() {
    return super.getInfo() + ", Rent: " + rent;
  }
  public int getRent() {
    return rent;
  }
}