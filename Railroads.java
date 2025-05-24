import java.util.ArrayList;

public class Railroads {//class used to find the rent of railroads
  private Railroad[] rrs;
  public Railroads(Railroad[] rr){
    rrs = rr;
  }
  public int getRent(Player owner){
    int count = 0;
    for (Railroad r: rrs){
      if(r.getOwner() == owner){
        count++;
      }
    }
    int rent = 25;
    for(int i = 1; i<count; i++){
      rent *= 2;
    }
    return rent;
  }
  public ArrayList<Railroad> checkOwnership(Player p){
    ArrayList<Railroad> ownedRR = new ArrayList<Railroad>();
    for(Railroad r: rrs){
      if (r.getOwner().equals(p)){
        ownedRR.add(r);
      }
    }
    return ownedRR;
  }
}