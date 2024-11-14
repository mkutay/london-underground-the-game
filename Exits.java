import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 * This class represents the exits of a station.
 * @author Mehmet Kutay Bozkurt
 * @version 1.0
 */
public class Exits {
  private HashMap<Entry<String, String>, Station> exits; // stores exits of a station

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
   * Capitalize the first letter of a given string.
   */
	private String capitalizeFirstLetter(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1);
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
}