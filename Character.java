import java.util.ArrayList;
import java.util.Map.Entry;

/**
 * The character class represents a character in the game.
 * 
 * @author Mehmet Kutay Bozkurt
 * @version 1.0
 */
public class Character {
  private Inventory inventory;
  private Station currentStation;
  private String name;
  private String description;
  private ArrayList<Station> allowedStations;
  private ArrayList<Entry<Item, Item>> exchanges; // items that the character exchanges with the player
  
  /**
   * Constructor for the player class.
   * @param startStation The station where the player starts.
   */
  public Character(String name, String description, ArrayList<Station> allowedStations) {
    inventory = new Inventory(10);
    this.name = name;
    this.description = description;
    this.allowedStations = allowedStations;
    currentStation = allowedStations.get(0);
  }

  /**
   * @return The current station the player is at.
   */
  public Station getCurrentStation() {
    return currentStation;
  }

  public Item exchangeItem(Item item) {
    for (Entry<Item, Item> exchange : exchanges) {
      if (exchange.getKey().equals(item)) {
        return exchange.getValue();
      }
    }
    return null;
  }

  /**
   * @return The inventory of the player as its string representation.
   */
  public String getInventory() {
    return name + " has the following items:" + inventory.toString();
  }

  /**
   * @return The name of the character.
   */
  public String getName() {
    return name;
  }

  /**
   * @return The description of the character.
   */
  public String getDescription() {
    return description;
  }

  /**
   * Move the character to a random exit that is allowed.
   */
  public void moveRandom() {
    do {
      currentStation = currentStation.getRandomExit();
    } while (!allowedStations.contains(currentStation));
  }
}