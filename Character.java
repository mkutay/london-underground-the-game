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
  private Entry<Item, Item> exchange; // gain key, lose value
  
  /**
   * Constructor for the player class.
   * @param startStation The station where the player starts.
   */
  public Character(String name, String description, ArrayList<Station> allowedStations, Entry<Item, Item> exchange) {
    inventory = new Inventory(10);
    this.name = name;
    this.description = description;
    this.allowedStations = allowedStations;
    currentStation = allowedStations.get(0);
    this.exchange = exchange;
  }

  /**
   * @return The current station the player is at.
   */
  public Station getCurrentStation() {
    return currentStation;
  }

  public Item exchangeItem(Item item) {
    if (item.equals(exchange.getKey())) {
      inventory.addItem(item);
      inventory.removeItem(exchange.getValue());
      return exchange.getValue();
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

  public Item talkWith() {
    if (exchange != null && exchange.getKey() == null) {
      inventory.removeItem(exchange.getValue());
      return exchange.getValue();
    }
    return null;
  }

  /**
   * Move the character to a random exit that is allowed.
   */
  public void moveRandom() {
    int randomNum = (int) (Math.random() * 10);
    if (allowedStations.size() == 1 || randomNum < 4) { // 40% chance that the character stays
      return;
    }

    Station nextStation = currentStation.getRandomExit();
    while (!allowedStations.contains(nextStation)) {
      nextStation = currentStation.getRandomExit();
    }
    currentStation = nextStation;
  }
}