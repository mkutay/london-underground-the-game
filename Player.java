import java.util.Stack;

/**
 * This is the Player class of the "London Underground" application.
 * "London Underground" is a simple text-based adventure game that was
 * inspired by the stations found in Central London.
 * 
 * This is the Player class representing the player in the game. The player
 * has a back stack of stations visited so far and an inventory of items.
 *
 * @author Mehmet Kutay Bozkurt
 * @version 1.0
 */
public class Player {
  private Stack<Station> backStack; // the stack of stations visited by the player
  private Inventory inventory; // the inventory of the player
  
  /**
   * Constructor - Create a player with a starting station and an empty inventory
   * with a maximum weight.
   * @param startStation The station where the player starts.
   * @param maxWeight The maximum weight the player can carry.
   */
  public Player(Station startStation, int maxWeight) {
    backStack = new Stack<Station>();
    backStack.push(startStation); // add the starting station to the back stack
    inventory = new Inventory(maxWeight); // create an inventory with the maximum weight
  }

  /**
   * @return The current station the player is at.
   */
  public Station getCurrentStation() {
    // The station at the top (that is, the peek) of the backStack is the
    // station that the player is currently in.
    return backStack.peek();
  }

  /**
   * Go back to the previous station that the player was in (that is,
   * remove the last station from the back stack). If the player is at the
   * starting station, then the player cannot go back any further.
   * @return False if the player is at the very beginning, return true otherwise.
   */
  public boolean goBack() {
    if (backStack.size() == 1) {
      return false;
    }
    backStack.pop();
    return true;
  }

  /**
   * Go to a station (that is, add the station to the back stack).
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
