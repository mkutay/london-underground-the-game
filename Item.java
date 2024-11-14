/**
 * Represents an item in the game with a name, description, and weight.
 * 
 * @author Mehmet Kutay Bozkurt
 * @version 1.0
 */
public class Item {
  private String name;
  private String description;
  private int weight;
  
  /**
   * Create a new item with the given name, description, and weight.
   */
  public Item(String name, String description, int weight) {
    this.name = name;
    this.description = description;
    this.weight = weight;
  }
  
  /**
   * @return The name of the item.
   */
  public String getName() {
    return name;
  }
  
  /**
   * @return The description of the item.
   */
  public String getDescription() {
    return description;
  }
  
  /**
   * @return The weight of the item.
   */
  public int getWeight() {
    return weight;
  }
  
  /**
   * @return A string representation of the item including
   * its name, weight, and description.
   */
  public String toString() {
    return name + " (" + weight + " lbs): " + description;
  }
}
