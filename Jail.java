import java.util.Random;
public class Jail {//class to handle players going to jail
  public static void toJail(Player p) {
    p.setJail(true);
    System.out.println("Go to jail");
    p.setPos(20);
  }

  public static boolean leaveJail(Player p) {
    int cost = 50;
      p.setJail(false);
      // p.setPos(rollDice());
      Random rand = new Random();
      int d1;
      int d2;
      int i = 0;
      do{
        d1 = rand.nextInt(6) + 1;
        d2 = rand.nextInt(6) + 1;
        if(d1 == d2){
          p.setJail(false);
          return true;//call in game to run turn again
        }
        }
      while(i < 3);
      if(p.getMoney() > 50){
        p.setMoney(p.getMoney()-50);
        p.setJail(false);
        return true;
      }
        //add smth to check if they can sell to achieve this
      return false;//wasn't able to roll doubles + can't pay bail
      //in game, check if player has money to pay bail, or sell
    // if(hasAssets(p.getCost(), p) == false) {
    //   System.out.println("Game over for "+p.Name());
    // }
}
}