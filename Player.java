import java.util.Stack;

/**
 * This is the Player class of the "London Underground" application.
 * "London Underground" is a simple text-based adventure game.
 * 
 * The player class representing the player in the game.
 * The player has a back stack of stations visited and an inventory of items.
 *
 * @author Mehmet Kutay Bozkurt
 * @version 1.0
 */
public class Player {
  private Stack<Station> backStack; // The stack of stations visited by the player.
  private Inventory inventory;
  
  /**
   * Constructor - Create a player with a starting station and an empty inventory.
   * @param startStation The station where the player starts.
   */
  public Player(Station startStation) {
    backStack = new Stack<Station>();
    backStack.push(startStation);
    inventory = new Inventory(10); // The player can carry up to 10 pounds.
  }

  /**
   * @return The current station the player is at.
   */
  public Station getCurrentStation() {
    return backStack.peek();
  }

  /**
   * Remove the last station from the back stack.
   * @return If the player is at the very beginning, return false.
   */
  public boolean goBack() {
    if (backStack.size() == 1) {
      return false;
    }
    backStack.pop();
    return true;
  }

  /**
   * Go to a station (that is add the station to the back stack).
   * @param station The station to go to.
   */
  public void goStation(Station station) {
    backStack.push(station);
  }

  /**
   * @return The inventory of the player.
   */
  public Inventory getInventory() {
    return inventory;
  }
}
