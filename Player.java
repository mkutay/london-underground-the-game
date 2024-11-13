import java.util.Stack;

public class Player {
  private Stack<Station> backStack;
  private Inventory inventory;
  
  public Player(Station startStation) {
    backStack = new Stack<Station>();
    backStack.push(startStation);
    inventory = new Inventory();
  }

  public Station getCurrentStation() {
    return backStack.peek();
  }

  public int getBackStackCount() {
    return backStack.size();
  }

  public void popBackStack() {
    backStack.pop();
  }

  public void pushBackStack(Station station) {
    backStack.push(station);
  }

  public boolean pickItem(Item item) {
    return inventory.addItem(item);
  }

  public Item getItem(String itemName) {
    return inventory.getItem(itemName);
  }

  public void dropItem(Item item) {
    inventory.removeItem(item);
  }

  public String getInventory() {
    return inventory.toString();
  }
}
