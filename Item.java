/**
 * This class is the Item class of the "London Underground" application.
 * "London Underground" is a simple, text-based adventure game.
 * 
 * Represents an item in the game with a name, description, weight, and 
 * an effect function when used.
 * 
 * @author Mehmet Kutay Bozkurt
 * @version 1.0
 */
public class Item {
  private String name;
  private String description;
  private int weight; // the weight of the item in lbs
  private String effectDialogue; // the effect of the item when it is used

  /**
   * Constructor - Create a new item with the given name, description, weight, and effect.
   */
  public Item(String name, String description, int weight, String effectDialogue) {
    this.name = name;
    this.description = description;
    this.weight = weight;
    this.effectDialogue = effectDialogue;
  }
  
  /**
   * @return The name of the item.
   */
  public String getName() {
    return name;
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

  /**
   * @return True if the item has an effect when used, false otherwise.
   */
  public boolean isUseable() {
    return effectDialogue != null;
  }

  /**
   * Use the item.
   * @return The message to be displayed to the user.
   */
  public String useItem() {
    return effectDialogue;
  }
}
