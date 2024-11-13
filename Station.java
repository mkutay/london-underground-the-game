import java.util.HashMap;
import java.util.Map.Entry;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Michael Kölling, David J. Barnes, and Mehmet Kutay Bozkurt
 * @version 2016.02.29
 */

public class Station {
  private String description;
  private String name;
  private HashMap<Entry<String, String>, Station> exits; // stores exits of this room
  private ArrayList<Item> items; // stores items in this room

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
    return description + "\n" + getExitString();
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

    for (Item item : items) {
      returnString += "\n  " +
				item.getName() + " (" +
        item.getWeight() + " pounds): " +
        item.getDescription() + ",";
    }

    return returnString.substring(0, returnString.length() - 1) + ".";
  }

  /**
   * Add an item to the station.
   * @param item The item to be added.
   */
  public void addItem(Item item) {
    items.add(item);
  }

  /**
   * Remove an item from the station.
   * @param item The item to be removed.
   */
  public void removeItem(Item item) {
    items.remove(item);
  }

  /**
   * Return the item with the given name.
   * @param itemName The name of the item.
   * @return The item with the given name.
   */
  public Item getItem(String itemName) {
    itemName = itemName.toLowerCase();
    for (Item item : items) {
      if (item.getName().equals(itemName)) {
        return item;
      }
    }
    return null;
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

	private String capitalizeFirstLetter(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

  public String getName() {
    return name;
  }
}

