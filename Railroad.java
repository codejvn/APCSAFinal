public class Railroad extends Sellable {//class used to define railraods
  
  public Railroad(String name, int x, int y) {
    super(name, 200, x, y);
  }
  public String getRailroad() {
    return super.getInfo() + "Starting Rent: 25";
  }
}