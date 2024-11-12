import java.util.HashMap;
import java.util.Map.Entry;
import java.util.AbstractMap.SimpleEntry;

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
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Station {
  private String description;
  private String name;
  private HashMap<Entry<String, String>, Station> exits; // stores exits of this room

  /**
   * Create a room described "description". Initially, it has
   * no exits. "description" is something like "a kitchen" or
   * "an open court yard".
   * @param description The room's description.
   */
  public Station(String description, String name) {
    this.description = description;
    this.name = name;
    exits = new HashMap<>();
  }

  /**
   * Define an exit from this room.
   * @param direction The direction of the exit.
   * @param neighbor  The room to which the exit leads.
   */
  public void setExit(String direction, String line, Station neighbor) {
    exits.put(new SimpleEntry<>(direction.toLowerCase(), line.toLowerCase()), neighbor);
  }

  /**
   * @return The short description of the room
   * (the one that was defined in the constructor).
   */
  public String getShortDescription() {
    return "You are in " + name + ".";
  }

  /**
   * Return a description of the room in the form:
   *   You are in the kitchen.
   *   Exits: north west
   * @return A long description of this room
   */
  public String getLongDescription() {
    return description + "\n" + getExitString();
  }

  /**
   * Return a string describing the room's exits, for example
   * "Exits: north west".
   * @return Details of the room's exits.
   */
  private String getExitString() {
		// add lines one can take
    String returnString = "You can take the following lines:";
    for (Entry<String, String> exit : exits.keySet()) {
      returnString += "\n  " +
				capitalizeFirstLetter(exit.getKey()) + " " +
				capitalizeFirstLetter(exit.getValue()) + " line,";
    }
    return returnString.substring(0, returnString.length() - 1);
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

