import java.util.ArrayList;

/**
 * This is the Inventory class of the "London Underground" application.
 * "London Underground" is a simple text-based adventure game that was
 * inspired by the stations found in Central London.
 * 
 * This Inventory class can represent the inventory of the player,
 * the characters in the game, or the items in a station. An inventory
 * can have a maximum weight, and items can be added or removed from it.
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
   * @return True if the item was added, false if the inventory is full.
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
   * Get an item from the inventory by its name as String.
   * @param itemName The name of the item.
   * @return The item with the given name, null if it doesn't exist
   */
  public Item getItem(String itemName) {
    itemName = itemName.toLowerCase(); // make the item name case-insensitive
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
   * @return A string representation of the items in the inventory
   * that is to be displayed to the user.
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
