import java.util.ArrayList;
//class to handle the rents for the utilities
public class Utilities{
  private Utility[] uts;
  public Utilities(Utility[] ut){
    uts = ut;
  }
  public int getRent(Player owner, int diceRoll){
    if ((uts[0].getOwner() == uts[1].getOwner()) && uts[0].getOwner() != null){
      return diceRoll*10;
    }
    else{
      return diceRoll*4;
    }
  }
  public ArrayList<Utility> checkOwnership(Player p){
    ArrayList<Utility> ownedU = new ArrayList<Utility>();
    for(Utility u: uts){
      if (u.getOwner().equals(p)){
        ownedU.add(u);
      }
    }
    return ownedU;
  }
}