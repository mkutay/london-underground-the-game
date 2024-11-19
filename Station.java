/**
 * This class is the Station class of the "London Underground" application. 
 * "London Underground" is a simple, text based adventure game.
 * 
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
   * Constructor - Create a station described as "description" with name as "name". Initially, it has
   * no exits and no items.
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
   * Return a description of the station with its exits and items.
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
    return "You find the following items in the station:" + items.toString();
  }

  /**
   * @return The items in the station.
   */
  public Inventory getItems() {
    return items;
  }

  /**
   * Return the station that is reached if we go from this station in direction
   * "direction" with line "line".
   * 
   * If only one variable is not null, look if there is only one exit that can be taken with the given
   * information, if yes, take it, if not, return null.
   * @param direction The exit's direction. If "line" is null, this can also be interpreted as the exit's line.
   * @param line The exit's specific line.
   * @return The station in the given direction, or null if it doesn't exist or cannot be determined.
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
   * @return A station randomly chosen from the exits of this station.
   */
  public Station getRandomExit() {
    return exits.getRandomExit();
  }
}