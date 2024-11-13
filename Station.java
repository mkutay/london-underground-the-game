import java.util.HashMap;
import java.util.Map.Entry;
import java.util.AbstractMap.SimpleEntry;

/**
 * Class Station - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Michael Kölling, David J. Barnes, and Mehmet Kutay Bozkurt
 * @version 1.0
 */

public class Station {
  private String description;
  private String name;
  private HashMap<Entry<String, String>, Station> exits; // stores exits of this station
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
    exits = new HashMap<>();
    items = new Inventory(Integer.MAX_VALUE); // stations can have unlimited items
  }

  /**
   * Define an exit from this station.
   * @param direction The direction of the exit.
   * @param line The line taking the direction.
   * @param neighbor The station to which the exit leads.
   */
  public void setExit(String direction, String line, Station neighbor) {
    exits.put(new SimpleEntry<>(direction.toLowerCase(), line.toLowerCase()), neighbor);
  }

  /**
   * Return a description of the station with its exits.
   * @return A long description of this station.
   */
  public String getDescription() {
    String itemsString = items.isEmpty() ? "" : "\n" + getItemString();
    return description + itemsString + "\n" + getExitString();
  }

  /**
   * Return a string describing the stations's exits.
   * @return Details of the station's exits.
   */
  private String getExitString() {
		// add lines one can take
    String returnString = "You can take the following lines:";

    for (Entry<String, String> exit : exits.keySet()) {
      returnString += "\n  " +
				capitalizeFirstLetter(exit.getKey()) + " " +
				capitalizeFirstLetter(exit.getValue()) + " line,";
    }

    return returnString.substring(0, returnString.length() - 1) + ".";
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
  public Station getExit(String direction, String line) {
    return exits.get(new SimpleEntry<>(direction.toLowerCase(), line.toLowerCase()));
  }

  /**
   * Capitalize the first letter of a given string.
   */
	private String capitalizeFirstLetter(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

  /**
   * @return The name of the station.
   */
  public String getName() {
    return name;
  }
}

