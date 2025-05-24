import java.util.Scanner;
//class to define the tax positions on the board
public class Tax extends Position{
  private int price;
  private boolean choice;
  public Tax(int x, int y){
    super(x,y);
    price = 0;
    choice = true;
  }
  public Tax(int price, int x, int y){
    super(x,y);
    this.price = price;
    choice = false;
  }
  public int getTax(Player p){
    if(choice){
    // Scanner kb = new Scanner(System.in);
    int normal = 200;
    int portion = (int)(p.getMoney() * 0.1);
    if(p.getMoney() > 200 || Game.hasAssets(200, p)){
     System.out.print("Type \'10\' to pay 10% of your wealth, or \'200\' to pay $200: ");//do i need the ghost kb.nextLine() here?
      int input = 0;
      try{
     input = Game.getIntInput(0);
      System.out.println("You said " + input);
      }
      catch(Exception e){
        System.out.println("Please enter an integer.");
        input = 0;
      }
    while(!(input == 200 || input == 10)){//validating input
      System.out.println("Invalid input. Please try again.");
      System.out.print("Type \'10\' to pay 10% of your wealth, or \'200\' to pay $200: ");
     try{
     input = Game.getIntInput(0);
      System.out.println("You said " + input);
      }
      catch(Exception e){
        e.printStackTrace();
        System.out.println("Please enter an integer.");
        input = 0;
      }
    }
    if (input == 10){
      return portion;
    }
    else{
      return normal;
    } 
    }
    else{//the player doesn't have enough to pay for 200, so js default make them pay 10%
        return portion;
      }
    }
    else{
      return price;
    }
  }
  }
