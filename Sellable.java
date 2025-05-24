public class Sellable extends Position{//base class to define spaces on the board that can be sold
  //ie properrty, utility,and railroad
  private String name;
  private Player owner;
  private int cost;
  public Sellable(String name, int cost, int x, int y){
    super(x,y);
    this.name = name;
    owner = null;
    this.cost = cost;
  }
  public Player getOwner(){
    return owner;
  }
  public int getCost(){
    return cost;
  }
  public String getName(){
    return name;
  }
  public String getInfo(){
    if(owner != null){
    return "Name: " + name + ", Cost: " + cost + ", Owner: " + owner.getName();
    }
    else{
      return "Name: " + name + ", Cost: " + cost + ", Owner: None";
    }
  }
  public void setOwner(Player owner){
    this.owner = owner;
  }
  public boolean equals(Object other){
    return this.name == ((Sellable)other).name;
  }
}
