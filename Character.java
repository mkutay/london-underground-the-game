import java.util.ArrayList;
import java.util.Map.Entry;

/**
 * This class represents a Character in the "London Underground" game.
 * "London Underground" is a text-based adventure game.
 *
 * A character has a name, a dialogue, an inventory, a current station,
 * a list of allowed stations, and an exchange. The exchange is an entry
 * of two items, where the character gives the key item and receives
 * the value item in return.
 * 
 * @author Mehmet Kutay Bozkurt
 * @version 1.0
 */
public class Character {
  private Inventory inventory; // items that the character has
  private Station currentStation; // station where the character is currently at
  private String name;
  private String dialogue;
  private ArrayList<Station> allowedStations; // stations where the character is allowed to go
  private Entry<Item, Item> exchange; // character gets "key", gives "value"
  
  /**
   * Constructor - creates a new character with the given name,
   * dialogue, allowed stations, and exchange.
   * @param startStation The station where the player starts.
   */
  public Character(String name, String dialogue, ArrayList<Station> allowedStations, Entry<Item, Item> exchange) {
    this.name = name;
    this.dialogue = dialogue;
    this.allowedStations = allowedStations;
    this.exchange = exchange;
    inventory = new Inventory(10);
    currentStation = allowedStations.get(0);

    if (exchange != null) {
      inventory.addItem(exchange.getValue()); // add the value item to the inventory
    }
  }

  /**
   * @return The current station the character is at.
   */
  public Station getCurrentStation() {
    return currentStation;
  }

  /**
   * Exchange an item with the character.
   * @param item The item that the player gives the character.
   * @return The item that the character gives in exchange, null if the exchange does not happen.
   */
  public Item exchangeItem(Item item) {
    if (item.equals(exchange.getKey())) {
      inventory.addItem(item);
      inventory.removeItem(exchange.getValue());
      return exchange.getValue();
    }
    return null;
  }

  /**
   * @return The name of the character.
   */
  public String getName() {
    return name;
  }

  /**
   * @return The dialogue of the character.
   */
  public String getDialogue() {
    return dialogue;
  }

  /**
   * Talk with the character and get an item if possible.
   * @return Item if the character gives an item for free, null otherwise.
   */
  public Item talkWith() {
    if (exchange != null && exchange.getKey() == null) {
      inventory.removeItem(exchange.getValue());
      return exchange.getValue();
    }
    return null;
  }

  /**
   * Move the character to a random exit that it's allowed to go to.
   */
  public void moveRandom() {
    int randomNum = (int) (Math.random() * 10);
    if (allowedStations.size() == 1 || randomNum < 4) { // 40% chance that the character stays
      return;
    }

    Station nextStation = currentStation.getRandomExit();
    while (!allowedStations.contains(nextStation)) {
      // keep getting a random exit until it's an allowed station
      nextStation = currentStation.getRandomExit();
    }
    currentStation = nextStation;
  }
}