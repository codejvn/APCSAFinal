import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class Game {// class to run the game
  private static ArrayList<Position> board;
  private Player[] players;
  private int currPlayer;
  private int round;
  private boolean done;
  private int numPlayers;
  private Utilities utilities;
  private Railroads railroads;
  private int numRolled;
  private boolean gameOver;
  private static Scanner kb;
  private Property[] allProperties;
  private static ArrayList<Sellable> allSellables;
  private static Sellable[][] grouped;
  private MarkerManager manager;

  public Game(int numPlayers) {
    this.numPlayers = numPlayers;
    System.out.println("numPLayers: " + numPlayers);
    this.currPlayer = 0; // change
    this.round = 1;
    this.done = false;
    this.board = new ArrayList<Position>();
    gameOver = false;
    setBoard();
    players = new Player[numPlayers];
    int x = 365;
    int y = 370;
    boolean changeX = true;
    boolean changeY = false;
    int diffX = 0;
    int diffY = 0;
    for (int i = 0; i < numPlayers; i++) {
      players[i] = new Player(board, i, x + diffX, y + diffY);// don't need to pass board in, it's static
      System.out.println(players[i]);
      if (changeX) {
        diffX += 20;
      }
      if (diffX == 60) {
        diffX = 0;
        diffY += 20;
      }
      // if (changeY) {
      // y += 20;
      // changeX = !changeX;
      // changeY = !changeY;
      // }
    }
    kb = new Scanner(System.in);
    allSellables = new ArrayList<Sellable>();
    setBoard();
    setSellables();
    setGroups();
    // setAllProps();
    setSpecialRents();
  }

  public void setMarkerManager(MarkerManager m) {
    manager = m;
  }

  public void setBoard() {
    board.add(new Position(400, 400));// go space
    // bottom row
    board.add(new Property("Mediterranean Avenue", 60, 2, 340, 396));// 1
    board.add(new CommunityChest(305, 393));
    board.add(new Property("Baltic Avenue", 60, 4, 270, 396));// 3
    board.add(new Tax(235, 393));
    board.add(new Railroad("Reading RR", 200, 392));// 6
    board.add(new Property("Oriental Avenue", 100, 6, 170, 394));// 7
    board.add(new Chance(135, 394));
    board.add(new Property("Vermont Avenue", 100, 6, 100, 395));// 9
    board.add(new Property("Connecticut Avenue", 120, 8, 65, 394));// 10
    board.add(new Position(4, 400));// the actual jail
    // left side
    board.add(new Property("St. Charles Avenue", 140, 10, 31, 340));// 12
    board.add(new Utility("Electric Company", 29, 305));// 13
    board.add(new Property("States Avenue", 140, 10, 27, 275));// 14
    board.add(new Property("Virginia Avenue", 160, 12, 25, 235));// 15
    board.add(new Railroad("Pennsylvania RR", 32, 200));// 16
    board.add(new Property("St. James Place", 180, 14, 30, 165));// 17
    board.add(new CommunityChest(30, 130));
    board.add(new Property("Tennessee Avenue", 180, 14, 29, 100));// 19
    board.add(new Property("New York Avenue", 200, 16, 27, 65));// 20
    board.add(new Position(27, 15));// free parking
    // top row
    board.add(new Property("Kentucky Avenue", 220, 18, 65, 8));// 22
    board.add(new Chance(100, 8));
    board.add(new Property("Indiana Avenue", 220, 18, 135, 8));// 24
    board.add(new Property("Illinois Avenue", 240, 20, 170, 8));// 25
    board.add(new Railroad("B&O Railroad", 205, 8));// 26
    board.add(new Property("Atlantic Avenue", 260, 22, 235, 8));// 27
    board.add(new Property("Ventnor Avenue", 260, 22, 270, 8));// 28
    board.add(new Utility("Public Works", 303, 8));// 29
    board.add(new Property("Marvin Gardens", 280, 24, 338, 8));// 30
    board.add(new Position(400, 8));// go to jail spot
    // right side
    board.add(new Property("Pacific Avenue", 300, 26, 395, 60));// 32
    board.add(new Property("North Carolina Avenue", 300, 26, 395, 95));// 33
    board.add(new CommunityChest(395, 130));
    board.add(new Property("Pennsylvania Avenue", 320, 28, 395, 165));// 35
    board.add(new Railroad("Short Line", 395, 200));// 36
    board.add(new Chance(395, 235));
    board.add(new Property("Park Place", 350, 35, 395, 270));// 38
    board.add(new Tax(395, 305));
    board.add(new Property("Boardwalk", 400, 50, 395, 338));// 40

  }

  public void setSpecialRents() {
    Utility[] u = { (Utility) board.get(12), (Utility) board.get(28) };
    utilities = new Utilities(u);
    Railroad[] r = { (Railroad) board.get(5), (Railroad) board.get(15), (Railroad) board.get(25),
        (Railroad) board.get(35) };
    railroads = new Railroads(r);
  }

  // public void setAllProps(){
  // //1,3,7,9,10,12,14,15,17,19,20,22,24,25,27,28,30,32,33,35,38,40
  // Property[] propsBoard = {board.get(1), board.get(3), board.get(7),
  // board.get(9), board.get(10), board.get(12), board.get(14), board.get(15),
  // board.get(17), board.get(19), board.get(20), board.get(22), board.get(24),
  // board.get(25),
  // board.get(27),board.get(28),board.get(30),board.get(32),board.get(33),
  // board.get(35),board.get(38),board.get(40)};
  // allProperties = propsBoard;
  // }
  public void setSellables() {
    for (Position p : board) {
      if (p instanceof Sellable) {
        allSellables.add((Sellable) p);
      }
    }
  }

  public void setGroups() {// cast all of these
    Sellable[][] s = {
        { (Sellable) board.get(1), (Sellable) board.get(3) }, // brown
        { (Sellable) board.get(6), (Sellable) board.get(8), (Sellable) board.get(9) }, // lightblue
        { (Sellable) board.get(11), (Sellable) board.get(13), (Sellable) board.get(14) }, // magenta
        { (Sellable) board.get(16), (Sellable) board.get(18), (Sellable) board.get(19) }, // orange
        { (Sellable) board.get(21), (Sellable) board.get(23), (Sellable) board.get(24) }, // red
        { (Sellable) board.get(26), (Sellable) board.get(27), (Sellable) board.get(29) }, // yellow
        { (Sellable) board.get(31), (Sellable) board.get(32), (Sellable) board.get(34) }, // gtreen
        { (Sellable) board.get(37), (Sellable) board.get(39) }// blue
    };
    grouped = s;
  }

  public Player[] getPlayersArr() {
    return players;
  }

  public void rollDice() {
    System.out.print("Roll? (y/n): ");
    String str = kb.nextLine();
    while (!((str.equals("y") || str.equals("Y")))) {
      System.out.print("Roll? (y/n): ");
      str = kb.nextLine();
    }
    Random random = new Random();
    int d1 = random.nextInt(6) + 1;
    int d2 = random.nextInt(6) + 1;
    // if (d1 == d2) {
    // rollDice();
    // }
    int sum = d1 + d2;
    System.out.println("You rolled a " + sum);
    numRolled = sum;
  }

  public void movePlayer(int steps) {
    Player player = players[currPlayer];
    player.setPos((player.getPos() + steps) % board.size());
  }

  public static String getStringInput() {
    return kb.nextLine();
  }

  public static int getIntInput(int defaultVal) {
    try {
      int z = kb.nextInt();
      kb.nextLine();// taking care of cursor not being on next line
      return z;
    } catch (Exception e) {
      kb.nextLine();
      return defaultVal;
    }
  }

  public static boolean hasAssets(int amt, Player p) {
    int sumVals = 0;
    for (Sellable s : p.getProps()) {
      sumVals += s.getCost();
    }
    if (sumVals < amt) {
      // game over, even if the player sells all properties, they can't pay for the
      // thing
      System.out.println("game over");
      return false;
    }
    return true;// they have the assets to cover the cost
  }

  // ideally you would call this method if they don't have money in their wallet,
  // and extract payment after this method, if it returns true;
  public static void sellProps(int amt, Player p) {// this method checks if the current player can sell properties to
                                                   // pay for smth
    System.out.println("In order to pay the balance of $" + amt + ", you must sell some of your properties.");
    // getting all of the properties that the person can sell
    ArrayList<Sellable> sellableProps = new ArrayList<Sellable>();
    for (int i = 0; i < allSellables.size(); i++) {
      if (allSellables.get(i).getOwner() != null && allSellables.get(i).getOwner().equals(p)) {// added equals method
        sellableProps.add(allSellables.get(i));
      }
    }
    while (p.getMoney() < amt) {// allows ppl to sell properties until they have enough money
      System.out.println("These are your availible properties that you can sell: ");
      for (int i = 0; i < sellableProps.size(); i++) {// printing out properties
        System.out.println("Index " + i + ": " + sellableProps.get(i).getInfo());
      }
      System.out.println("Type the index of which property you want to sell");
      int index;
      try {// making sure ppl input integers
        index = getIntInput(sellableProps.size() + 5);// index of the property to sell
      } catch (Exception e) {
        System.out.println("Please enter an integer.");
        index = sellableProps.size() + 5;
      }
      // validate user input???
      while (index >= sellableProps.size()) {// validating user input
        System.out.println("Invalid Input. Please try again.");
        System.out.println("Type the index of which property you want to sell");
        try {// making sure ppl input integers
          index = getIntInput(sellableProps.size() + 5);// index of the property to sell
        } catch (Exception e) {
          System.out.println("Please enter an integer.");
          index = sellableProps.size() + 5;
        }
      }
      System.out.println("You chose to sell this: " + sellableProps.get(index).getInfo());
      Sellable sold = sellableProps.remove(index);// removing the property from the available properties to sell
      // removing the property from the player's properties
      // Sellable sold = null;
      ArrayList<Sellable> props = p.getProps();
      for (int j = 0; j < props.size(); j++) {
        if (props.get(j).equals(sold)) {
          props.remove(j);
        }
      }
      p.setMoney(p.getMoney() + sold.getCost());// sells property for the cost to buy
      sold.setOwner(null);// making the sold prop available again
      if (p.getMoney() < amt) {
        System.out.println("You still don't have enough money. You have $" + p.getMoney() + ", and you need $" + amt);
      }
    }
    System.out.println("You have raised sufficient funds.");
  }

  public void chooseSellable(Player p) {
    Sellable currProp = (Sellable) (board.get(p.getPos()));
    if (currProp.getOwner() == null) {// if the position landed on is open for sell
      if (p.getMoney() >= currProp.getCost()) {
        System.out.println("The property " + currProp.getInfo() + " can be acquired. Do you want to buy it? (y/n)");
        String anw = kb.nextLine();
        if (anw.equals("y") || anw.equals("Y")) { // use try/catch to check for capitalized letters?
          p.getProps().add(currProp);// will this work? what is this trying to do
          p.setMoney(p.getMoney() - currProp.getCost());
          currProp.setOwner(p);
        }
      } else {
        System.out.println("Not enough money to buy this property.");
      }
    } else {// if someone already owns the sellable landed on
      Player owner = currProp.getOwner();
      Player curr = players[currPlayer];// should be the same as the input to the method lol
      int rent = 0;
      if (!owner.equals(curr)) {// if the sellable landed on is someone else's property
        if (currProp instanceof Property) {
          Property temp = (Property) currProp;
          rent = temp.getRent();
          // checking if the owner owns all of the properties of the same color
          boolean sameOwner = true;
          for (int r = 0; r < grouped.length; r++) {
            for (int c = 0; c < grouped[r].length; c++) {
              if (grouped[r][c].getOwner() != null && grouped[r][c].getOwner().equals(temp.getOwner())) {
                Player orig = grouped[r][0].getOwner();
                for (int i = 1; i < grouped[r].length; i++) {
                  if (grouped[r][i].getOwner() != null && orig != null && !(orig.equals(grouped[r][i].getOwner()))) {
                    sameOwner = false;
                    break;
                  }
                }
              }
            }
          }
          if (sameOwner) {
            rent *= 2;
          }
        } else if (currProp instanceof Railroad) {
          rent = railroads.getRent(currProp.getOwner());
        } else {
          rent = utilities.getRent(currProp.getOwner(), numRolled);
        }
        System.out.println(curr.getName() + ", you owe " + owner.getName() + " $" + rent + " in rent.");
        if (curr.getMoney() < rent) {
          if (hasAssets(rent, curr)) {
            sellProps(rent, curr);// helps curr get the money needed to pay rent
          } else {
            gameOver = true;
            return;
          }
        }
        curr.setMoney(curr.getMoney() - rent);
        owner.setMoney(owner.getMoney() + rent);
      } else {
        System.out.println("This is your property.");
      }
    }
  }

  public void turn() {
    Player player = players[currPlayer];
    System.out.println("It is " + player.getName() + "'s turn");
    if (!player.inJail()) {
      rollDice();
      int oldPos = player.getPos();
      movePlayer(numRolled);
      manager.update();
      if (board.get(player.getPos()) instanceof Sellable) {
        System.out.println("Moved to {" + ((Sellable) board.get(player.getPos())).getInfo() + "}");
      } else {
        System.out.println("Moved to position #" + player.getPos() % board.size());// should prolly fix this to be
                                                                                   // useful lol
      }
      player.setPositionObj(board.get(player.getPos()));
      int newPosNum = player.getPos();
      Position newPos = player.getPositionObj();
      // checking if the player passed GO and giving money
      // if((newPosNum <= 11) && (oldPos >= 29)) {
      // System.out.println("You passed GO! You get $200!");
      // player.setMoney(player.getMoney() + 200);
      // }
      // checking which type of position that the person landed on
      if (newPos instanceof Sellable) {
        // add prinnt statements in chooseSellable
        chooseSellable(player);
      } else if (player.getPos() == 30) {
        System.out.println("Oh no! You got sent to Jail!!");
        Jail.toJail(player);
      } else if (player.getPos() == 10) {
        System.out.println("You're just visiting jail. Don't end up here!");
      } else if (player.getPos() == 0) {
        System.out.println("You landed on GO!");
      } else if (player.getPos() == 20) {
        System.out.println("Free Parking! Nice!");
      } else if (newPos instanceof Tax) {
        Tax temp = (Tax) newPos;
        System.out.println("Ouch! You have to pay your taxes.");
        // call the getTax() method to get the tax and use the sell method to see if
        // they can afford it
        System.out.println("You have $" + player.getMoney());
        int amt = temp.getTax(player);
        System.out.println("You owe $" + amt + " in taxes");
        if (player.getMoney() > amt) {
          player.setMoney(player.getMoney() - amt);// amt is negative
        } else {
          if (hasAssets(amt, player)) {
            sellProps(amt, player);
            player.setMoney(player.getMoney() - amt);
          } else {
            gameOver = true;
          }
        }
      } else if (newPos instanceof Chance) {
        Chance temp = (Chance) newPos;
        int amt = temp.getChance();
        System.out.println("You landed on Chance!");
        System.out.println("You earned/lost $" + amt + "!");
        if (amt >= 0) {
          player.setMoney(player.getMoney() + amt);
        } else {// if they're losing money from the chance
          if (player.getMoney() > amt) {
            player.setMoney(player.getMoney() + amt);// amt is negative
          } else {
            if (hasAssets(amt, player)) {
              sellProps(amt, player);
            } else {
              gameOver = true;
            }
          }
        }
      } else if (newPos instanceof CommunityChest) {
        CommunityChest temp = (CommunityChest) newPos;
        int amt = temp.getReward();
        System.out.println("You landed on Community Chest!");
        System.out.println("You've earned $" + amt + "!!");
        player.setMoney(player.getMoney() + amt);
      }
    } else {
      boolean b = Jail.leaveJail(player);
      if (!b) {
        gameOver = true;
      } else {
        System.out.println("You escaped Jail! Roll again.");
        turn();
      }
    }
  }

  public void start() {
    while (!gameOver) {
      turn();
      System.out.println("End of turn.");
      players[currPlayer].printBasic();
      players[currPlayer].printProps();
      System.out.println("Next Player.");
      System.out.print("\n\n");
      currPlayer = (currPlayer + 1) % players.length;
      if (currPlayer % players.length == 0) {
        round++;
        System.out.println("this is what round should be: " + round);
        System.out.println("New round!! " + round);
      }
      // if (round == 10) {
      // gameOver = true;
      // }
    }
    System.out.println(+"The game is over");
    int max = players[0].getTotalAssets();
    int ind = 0;
    for (int i = 1; i < players.length; i++) {
      if (players[i].getTotalAssets() > max) {
        max = players[i].getTotalAssets();
        ind = i;
      }
    }
    System.out.println(players[ind].getName() + " is the winner, with $" + players[ind].getTotalAssets()
        + " in total assets. Congrats!");
    kb.close();
  }
}