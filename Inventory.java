import java.util.ArrayList;

/**
 * The inventory class representing the player's inventory
 * or items in a station in the game, with a maximum weight.
 * 
 * @author Mehmet Kutay Bozkurt
 * @version 1.0
 */
public class Inventory {
  private int maxWeight;
  private ArrayList<Item> items;

  /**
   * Constructor for the inventory.
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
   */
  public void removeItem(Item item) {
    items.remove(item);
  }

  /**
   * @param itemName The name of the item.
   * @return The item with the given name.
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
  public int getTotalWeight() {
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
      return "Empty.";
    }

    String returnString = "";
    for (Item item : items) {
      returnString += "\n  " + item.toString();
    }
    return returnString;
  }

  /**
   * @return true if the inventory is empty, false otherwise.
   */
  public boolean isEmpty() {
    return items.isEmpty();
  }
}
