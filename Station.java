import java.util.Map.Entry;

/**
 * A "Station" represents one location in the scenery of the game. It is 
 * connected to other stations via lines (that is exits).
 * 
 * @author Michael Kölling, David J. Barnes, and Mehmet Kutay Bozkurt
 * @version 1.0
 */
public class Station {
  private String description;
  private String name;
  private Exits exits; // stores exits of this station
  private Inventory items; // stores items in this station

  /**
   * Create a station described as "description" with name as "name". Initially, it has
   * no exits.
   * @param description The station's description.
   * @param name The station's name, like London Bridge.
   */
  public Station(String description, String name) {
    this.description = description;
    this.name = name;
    exits = new Exits();
    items = new Inventory(Integer.MAX_VALUE); // stations can have unlimited items
  }

  /**
   * Define an exit from this station.
   * @param direction The direction of the exit.
   * @param line The line taking the direction.
   * @param neighbor The station to which the exit leads.
   */
  public void setExit(String direction, String line, Station neighbor) {
    exits.setExit(direction, line, neighbor);
  }

  /**
   * Return a description of the station with its exits.
   * @return A long description of this station.
   */
  public String getDescription() {
    String itemsString = items.isEmpty() ? "" : "\n\n" + getItemString();
    return description + itemsString + "\n\n" + getExitString();
  }

  /**
   * Return a string describing the stations's exits.
   * @return Details of the station's exits.
   */
  private String getExitString() {
		return exits.toString();
  }

  /**
   * Return a string describing the items found in the station.
   * @return Details of the station's items.
   */
  private String getItemString() {
    String returnString = "You find the following items in the station:";

    return returnString + items.toString();
  }

  /**
   * Add an item to the station.
   * @param item The item to be added.
   */
  public void addItem(Item item) {
    items.addItem(item);
  }

  /**
   * Use an item in the station.
   * @param item The item to be used.
   * @return true if the item was used successfully, false otherwise.
   */
  public Entry<Boolean, String> useItem(Item item) {
    return item.use(this);
  }

  /**
   * Remove an item from the station.
   * @param item The item to be removed.
   */
  public void removeItem(Item item) {
    items.removeItem(item);
  }

  /**
   * Return the item with the given name.
   * @param itemName The name of the item.
   * @return The item with the given name.
   */
  public Item getItem(String itemName) {
    return items.getItem(itemName);
  }

  /**
   * Return the station that is reached if we go from this station in direction
   * "direction" with line "line". If there is no station in that direction, return null.
   * @param direction The exit's direction.
   * @param line The exit's specific line.
   * @return The station in the given direction.
   */
  public Station getExit(String word2, String word3) {
    return exits.getExit(word2, word3);
  }

  /**
   * @return The name of the station.
   */
  public String getName() {
    return name;
  }

  /**
   * @return A random exit from the station.
   */
  public Station getRandomExit() {
    return exits.getRandomExit();
  }
}