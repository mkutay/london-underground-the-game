import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;

/**
 * This class represents the exits of a station, stroing them in a hashmap.
 * 
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
   * If only word2 is given, look if there is only one exit to be taken with the given information,
   * if yes, take it, if not, return null.
   * @param word2 The exit's direction.
   * @param word3 The exit's specific line.
   * @return The station in the given direction, or null if it doesn't exist.
   */
  public Station getExit(String word2, String word3) {
    if (word3 != null) {
      return exits.get(new SimpleEntry<>(word2.toLowerCase(), word3.toLowerCase()));
    }
    Station returnStation = null;
    for (Entry<String, String> exit : exits.keySet()) {
      if (exit.getKey().equals(word2.toLowerCase()) || exit.getValue().equals(word2.toLowerCase())) {
        if (returnStation != null) {
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