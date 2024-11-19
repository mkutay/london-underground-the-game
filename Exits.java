import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;

/**
 * This class is the Exits class of the "London Underground" application. 
 * "London Underground" is a simple, text based adventure game.
 * 
 * This class represents the exits of an arbitrary station, storing them in a hashmap.
 * 
 * @author Mehmet Kutay Bozkurt
 * @version 1.0
 */
public class Exits {
  private HashMap<Entry<String, String>, Station> exits; // stores exits of a station

  /**
   * Constructor - Create an Exits object with no exits.
   */
  public Exits() {
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
   * Return a string describing the stations's exits.
   * @return Details of the station's exits.
   */
  public String toString() {
    String returnString = "You can take the following lines:";

    for (Entry<String, String> exit : exits.keySet()) { // add lines one can take
      returnString += "\n  " +
				capitalizeFirstLetter(exit.getKey()) + " " +
				capitalizeFirstLetter(exit.getValue()) + " line,";
    }

    // remove the last comma and add a period
    return returnString.substring(0, returnString.length() - 1) + ".";
  }

  /**
   * Capitalize the first letter of a given string.
   * Used to make the output more readable.
   */
	private String capitalizeFirstLetter(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

  /**
   * Return the station that is reached if we go in direction
   * "direction" with line "line". If there is no station in that direction, return null.
   * If only one variable is not null, look if there is only one exit to be taken with the given
   * information, if yes, take it, if not, return null.
   * @param direction The exit's direction. If "line" is null, this can also be interpreted as the exit's line.
   * @param line The exit's specific line.
   * @return The station in the given direction, or null if it doesn't exist.
   */
  public Station getExit(String direction, String line) {
    if (line != null) { // if line is not null, we can directly return the station
      return exits.get(new SimpleEntry<>(direction.toLowerCase(), line.toLowerCase()));
    }

    Station returnStation = null;
    for (Entry<String, String> exit : exits.keySet()) {
      if (exit.getKey().equals(direction.toLowerCase()) || exit.getValue().equals(direction.toLowerCase())) {
        if (returnStation != null) {
          // if there is more than one possible exit to take with the given information, return null
          return null;
        }
        returnStation = exits.get(exit);
      }
    }
    return returnStation;
  }

  /**
   * The following code was taken from https://stackoverflow.com/questions/929554/is-there-a-way-to-get-the-value-of-a-hashmap-randomly-in-java
   * @return A random exit from the exits of a station.
   */
  public Station getRandomExit() {
    Random generator = new Random();
    Object[] values = exits.values().toArray();
    return (Station) values[generator.nextInt(values.length)];
  }
}