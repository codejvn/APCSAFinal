public class CommunityChest extends Position{//class to define the communiy chest spaces 

  public CommunityChest(int x, int y){
    super(x,y);
  }
  public int getReward(){
    int reward = (int)(Math.random()*191 + 10);
    return (reward/10)*10;
  }
}