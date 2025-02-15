import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;

/**
 * This class is the Station class of the "The London Underground" application. 
 * "The London Underground" is a simple, text based adventure game that was
 * inspired by the stations found in Central London.
 * 
 * A "Station" represents one location in the scenery of the game. It is 
 * connected to other stations via lines (in other words, exits). Each 
 * station can hold items using the Inventory class, and the player can
 * pick items up from the stations or drop them in the stations.
 * 
 * @author Michael Kölling, David J. Barnes, and Mehmet Kutay Bozkurt
 * @version 1.0
 */
public class Station {
  private String description;
  private String name;
  private Inventory items; // stores items in this station
  private HashMap<Entry<String, String>, Station> exits; // stores exits of this station

  /**
   * Constructor - Create a station with a given description and name, and initialise its exits and items.
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
   * @return A long description of this station with the exits and the items
   * of the station.
   */
  public String getDescription() {
    String itemsString = "";
    if (!items.isEmpty()) {
      // If there are items in the station, add them to the description.
      itemsString = "\n\nYou find the following items in the station:" + items.toString();
    }

    String exitsString = "\n\nYou can take the following lines:";

    // go through every exit of the station
    for (Entry<String, String> exit : exits.keySet()) {
      // "key" is the direction, "value" is the line:
      exitsString += "\n  " +
        capitaliseFirstLetter(exit.getKey()) + " " +
        capitaliseFirstLetter(exit.getValue()) + " line,";
    }

    // Remove the last comma and add a period instead.
    exitsString = exitsString.substring(0, exitsString.length() - 1) + ".";

    // Return the description with the items and exits.
    return description + itemsString + exitsString;
  }

  /**
   * @return The name of the station.
   */
  public String getName() {
    return name;
  }

  /**
   * @return The items in the station.
   */
  public Inventory getItems() {
    return items;
  }

  /**
   * Return the station that is reached if we go in direction "direction" with
   * line "line". If there is no station in that direction, return null.
   * @param direction The exit's direction.
   * @param line The exit's specific line.
   * @return The station in the given direction, or null if it doesn't exist.
   */
  public Station getExit(String direction, String line) {
    // We want direction and line to be case-insensitive.
    return exits.get(new SimpleEntry<>(direction.toLowerCase(), line.toLowerCase()));
  }

  /**
   * Return the station that is reached if we go in either a direction or a line
   * when "word" is interpreted as such. If there is no station in that direction
   * or there are more than one possible ways, return null.
   * @param word The exit's direction or line to be interpreted.
   * @return The station in the given way, or null if it doesn't exist or cannot be determined.
   */
  public Station getExit(String word) {
    Station returnStation = null;
    for (Entry<String, String> exit : exits.keySet()) {
      // Either interpret the word as a "direction" or a "line".
      if (exit.getKey().equals(word.toLowerCase()) || exit.getValue().equals(word.toLowerCase())) {
        if (returnStation != null) {
          // If there is more than one possible exit to take with the given information, return null
          // as we cannot determine which one to take.
          return null;
        }
        returnStation = exits.get(exit);
      }
    }
    return returnStation;
  }

  /**
   * The following code was taken from https://stackoverflow.com/questions/929554/is-there-a-way-to-get-the-value-of-a-hashmap-randomly-in-java
   * @return A random exit from the exits of this station.
   */
  public Station getRandomExit() {
    Random generator = new Random(); // create a random stream
    Object[] values = exits.values().toArray();
    return (Station) values[generator.nextInt(values.length)];
  }

  /**
   * Capitalise the first letter of a given string.
   * Used to make the output more readable.
   */
	private String capitaliseFirstLetter(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}
}