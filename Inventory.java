import java.util.ArrayList;

/**
 * This is the Inventory class of the "London Underground" application.
 * "London Underground" is a simple text-based adventure game.
 * 
 * The inventory class representing the player's inventory
 * or items in a station in the game, with a maximum weight.
 * 
 * @author Mehmet Kutay Bozkurt
 * @version 1.0
 */
public class Inventory {
  private int maxWeight; // The maximum weight the inventory can hold.
  private ArrayList<Item> items; // The items in the inventory, represented as an ArrayList.

  /**
   * Constructor - Create an empty inventory with a maximum weight.
   * @param maxWeight The maximum weight the inventory can hold.
   */
  public Inventory(int maxWeight) {
    items = new ArrayList<Item>();
    this.maxWeight = maxWeight;
  }

  /**
   * Add an item to the inventory.
   * @return true if the item was added, false if the inventory is full.
   */
  public boolean addItem(Item item) {
    if (item.getWeight() + getTotalWeight() > maxWeight) {
      return false;
    }
    items.add(item);
    return true;
  }

  /**
   * Remove an item from the inventory.
   * @return True if the item was removed, false if the item doesn't exist.
   */
  public boolean removeItem(Item item) {
    return items.remove(item);
  }

  /**
   * @param itemName The name of the item.
   * @return The item with the given name, null if it doesn't exist
   */
  public Item getItem(String itemName) {
    itemName = itemName.toLowerCase();
    for (Item item : items) {
      if (item.getName().toLowerCase().equals(itemName)) {
        return item;
      }
    }
    return null;
  }

  /**
   * @return The total weight of the items in the inventory.
   */
  private int getTotalWeight() {
    int totalWeight = 0;
    for (Item item : items) {
      totalWeight += item.getWeight();
    }
    return totalWeight;
  }

  /**
   * @return A string representation of the items in the inventory.
   */
  public String toString() {
    if (items.isEmpty()) {
      return "\n  Empty.";
    }

    String returnString = "";
    for (Item item : items) {
      returnString += "\n  " + item.toString();
    }
    return returnString;
  }

  /**
   * @return True if the inventory is empty, false otherwise.
   */
  public boolean isEmpty() {
    return items.isEmpty();
  }
}
