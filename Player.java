import java.util.Stack;

/**
 * The player class representing the player in the game.
 * The player has a back stack of stations visited and an inventory of items.
 * @author Mehmet Kutay Bozkurt
 * @version 1.0
 */
public class Player {
  private Stack<Station> backStack; // The stack of stations visited by the player.
  private Inventory inventory;
  
  /**
   * Constructor for the player class.
   * @param startStation The station where the player starts.
   */
  public Player(Station startStation) {
    backStack = new Stack<Station>();
    backStack.push(startStation);
    inventory = new Inventory(10);
  }

  /**
   * @return The current station the player is at.
   */
  public Station getCurrentStation() {
    return backStack.peek();
  }

  /**
   * @return The number of stations in the back stack.
   */
  public int getBackStackCount() {
    return backStack.size();
  }

  /**
   * Remove the last station from the back stack.
   */
  public void popBackStack() {
    backStack.pop();
  }

  /**
   * Add a station to the back stack.
   */
  public void pushBackStack(Station station) {
    backStack.push(station);
  }

  /**
   * Add a picked item to the inventory.
   */
  public boolean pickItem(Item item) {
    return inventory.addItem(item);
  }

  /**
   * Get an item from the inventory with the given name.
   */
  public Item getItem(String itemName) {
    return inventory.getItem(itemName);
  }

  /**
   * Drop an item from the inventory.
   */
  public void dropItem(Item item) {
    inventory.removeItem(item);
  }

  /**
   * @return The inventory of the player as its string representation.
   */
  public String getInventory() {
    return "Inventory:" + inventory.toString();
  }
}
