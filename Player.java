
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

//class made to define a player in the game
class Player {
  private String name;
  private int money;
  private int pos;// the index in the board array where the player is
  private ArrayList<Sellable> props;
  private ArrayList<Position> board;
  private boolean inJail;
  private int turnsInJail;
  private Position p;// the x,y position of the player
  private int firstX;
  private int firstY;

  public Player(ArrayList<Position> b,int ind, int x, int y) {// shouldnt need a name field bc
    // ppl are identificied by their index in the arr in game.java
    // this.name = name;
    board = b;
    name = "P" + (ind + 1);
    this.money = 1500;
    this.pos = 0;
    this.props = new ArrayList<Sellable>();
    this.inJail = false;
    this.turnsInJail = 0;
    this.p = board.get(0);
    firstX = x;
    firstY = y;
  }

  public boolean inJail() {
    return inJail;
  }

  public void setJail(boolean b) {
    inJail = b;
  }

  public int getPos() {
    return pos;
  }

  public void setPos(int x) {
    pos = x;
    p = board.get(x);
  }

  public void setPositionObj(Position p) {
    this.p = p;
  }
  public Position getPositionObj(){
    return p;
  }

  public int getY() {
    if(pos == 0){
    return firstY;
    }
    else if(inJail){
      return 380;
    }
    else{
      return p.getY();
    }
  }

  public int getX() {
    if(pos == 0){
      return firstX;
    }
    else if (inJail){
      return 25;
    }
    else{
    return p.getX();
    }
  }

  public int getMoney() {
    return money;
  }

  public void setMoney(int m) {
    money = m;
  }
  public int getTotalAssets(){
    int sum =0;
    for(Sellable s: props){
      sum += s.getCost();
    }
    return sum + money;
  }
  public String getName() {
    return name;
  }

  public ArrayList<Sellable> getProps() {
    return props;
  }
  public void printBasic(){
    System.out.println(name + " has $" +money);
  }
  public void printProps(){
    System.out.println("Properties owned by " + name + ":");
    for(Sellable s: props){
      System.out.println(s.getInfo());
    }
    if(props.isEmpty()){
      System.out.println("No properties owned.");
    }
  }
  public String toString(){
    return "Name: " + name + ",Money: " + money;
  }
  public boolean equals(Object other){
    Player x = (Player) other;
    return this.name.equals(x.getName());
  }
}