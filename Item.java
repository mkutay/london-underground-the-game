import java.util.Map.Entry;

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
  private UseEffectFunction effect; // the effect of the item when it is used

  /**
   * Create a new item with the given name, description, and weight.
   */
  public Item(String name, String description, int weight, UseEffectFunction effect) {
    this.name = name;
    this.description = description;
    this.weight = weight;
    this.effect = effect;
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

  /**
   * Use the item, applying its effect to the given station.
   * @return true if the game finishes, false otherwise.
   */
  public Entry<Boolean, String> use(Station station) {
    return effect.use(station);
  }
}
