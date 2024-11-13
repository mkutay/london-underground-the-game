import java.util.ArrayList;

public class Inventory {
  public static final int MAX_WEIGHT = 10;
  private ArrayList<Item> items;

  public Inventory() {
    items = new ArrayList<Item>();
  }

  /**
   * Add an item to the inventory.
   * @return true if the item was added, false if the inventory is full.
   */
  public boolean addItem(Item item) {
    if (item.getWeight() + getTotalWeight() > MAX_WEIGHT) {
      return false;
    }
    items.add(item);
    return true;
  }

  public void removeItem(Item item) {
    items.remove(item);
  }

  public Item getItem(String itemName) {
    itemName = itemName.toLowerCase();
    for (Item item : items) {
      if (item.getName().equals(itemName)) {
        return item;
      }
    }
    return null;
  }

  public int getTotalWeight() {
    int totalWeight = 0;
    for (Item item : items) {
      totalWeight += item.getWeight();
    }
    return totalWeight;
  }

  public String toString() {
    if (items.isEmpty()) {
      return "Inventory: empty.";
    }
    
    String returnString = "Inventory: ";
    for (Item item : items) {
      returnString += "\n  " + item.getName() + ",";
    }
    return returnString.substring(0, returnString.length() - 1) + ".";
  }
}
