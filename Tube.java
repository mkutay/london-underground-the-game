import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Arrays;

/**
 * This class is the Tube class of the "The London Underground" application.
 * "The London Underground" is a simple, text based adventure game that was
 * inspired by the stations found in Central London.
 * 
 * This class represents the tube map. It creates all the stations and
 * links them together. The tube map is read from the stations.txt and
 * connections.txt files using the Reader class. It also creates the
 * characters in the game and places them at one of the stations.
 * 
 * @author Mehmet Kutay Bozkurt
 * @version 1.0
 */
public class Tube {
  private ArrayList<Station> stations; // stores all of the stations on the underground
  private ArrayList<Character> characters; // stores characters
  private ArrayList<Item> items; // list of all of the items
  
  /**
   * Constructor - Initialise the tube by creating the stations and linking them together.
   * Also create the characters in the game and place them on the tube.
   */
  public Tube() {
    stations = new ArrayList<Station>();
    characters = new ArrayList<>();
    items = new ArrayList<>();

    createStations();
    connectStations();
    createItems();
    createCharacters();
  }
  
  /**
   * Create all the stations after reading from the file "stations.txt".
   * Add the "Random" station at the end of the list, which is used to transport
   * the player to a random station.
   */
  private void createStations() {
    ArrayList<String> stationsFile = Reader.readFile("stations.txt");
    for (int i = 0; i < stationsFile.size(); i += 2) {
      Station station = new Station(stationsFile.get(i), stationsFile.get(i + 1));
      stations.add(station);
    }
    // The "Random" station wouldn't be seen by the player, it is used to ease the implementation of the teleportation feature.
    stations.add(new Station("You are now being transported to a random station on the tube.", "Random"));
  }

  /**
   * Link the station's exits together by reading from the file "connections.txt".
   * Also link the "Random" station to the Bank station as the teleportation feature, 
   * which the player will be able to use by just taking the Waterloo&City line.
   */
  private void connectStations() {
    ArrayList<String> connectionsFile = Reader.readFile("connections.txt");
    for (int i = 0; i < connectionsFile.size(); i += 6) {
      setConnection(connectionsFile.get(i), connectionsFile.get(i + 1), connectionsFile.get(i + 2), connectionsFile.get(i + 3), connectionsFile.get(i + 4));
    }

    setConnection("Bank", "Random", "Waterloo&City", "Random", "Random");
  }

  /**
   * Set the connection between two stations.
   * @param name1 The name of the first station.
   * @param name2 The name of the second station.
   * @param line The line that connects the two stations, like Bakerloo
   * @param direction1 The direction from the first station to the second station, like Northbound
   * @param direction2 The direction from the second station to the first station, like Southbound
   */
  private void setConnection(String name1, String name2, String line, String direction1, String direction2) {
    Station station1 = null;
    Station station2 = null;
    for (Station station : stations) {
      if (station.getName().equals(name1)) {
        station1 = station;
      } else if (station.getName().equals(name2)) {
        station2 = station;
      }
    }

    if (station1 != null && station2 != null) {
      station1.setExit(direction1, line, station2);
      station2.setExit(direction2, line, station1);
    } else {
      // This should not run in the game, but is used as a precaution.
      System.out.println("ERROR: station(s) not found: " + name1 + ", " + name2);
    }
  }

  /**
   * @return A random station from the tube, not including the Random station.
   * @note "Random" station is at the end of the stations list
   */
  public Station getRandomStation() {
    // "Random" station is at the end of the stations list, so getting a random
    // number between the interval [0, size - 1) will exclude the "Random" station.
    return stations.get((int) (Math.random() * (stations.size() - 1)));
  }

  /**
   * Get the station with the given name.
   * @param name The name of the station.
   * @return The station with the given name, null if not found.
   */
  public Station getStation(String name) {
    name = name.toLowerCase(); // make the search case-insensitive
    for (Station station : stations) {
      if (station.getName().toLowerCase().equals(name)) {
        return station;
      }
    }
    return null;
  }

  /**
   * @return The character with the given name, null if it doesn't exist.
   */
  public Character getCharacter(String name) {
    name = name.toLowerCase(); // make the search case-insensitive
    for (Character character : characters) {
      if (character.getName().toLowerCase().equals(name)) {
        // Return the character if the name matches.
        return character;
      }
    }
    return null;
  }

  /**
   * Move all characters to a random station that they are allowed to go to.
   * This is method is run after the player "takes" a line to a new station.
   */
  public void moveCharacters() {
    for (Character character : characters) {
      character.moveRandom();
    }
  }

  /**
   * @return The description of the characters in the player's current station,
   * empty string if there are no characters.
   * @param playerCurrentStation The station where the player is currently at.
   */
  public String getCharactersDescription(Station playerCurrentStation) {
    String characterString = "";
    // Go through every character and check if they are at the player's current station.
    for (Character character : characters) {
      if (character.getCurrentStation().equals(playerCurrentStation)) {
        // Add the character's name to the string if they are at the same station as the player.
        characterString += "\n  " + character.getName();
      }
    }
    return characterString;
  }

  /**
   * Get the item with the given name.
   * @param name The name of the item.
   * @return The item with the given name, null if not found.
   * @note This method is used to get items when creating characters.
   */
  private Item getItem(String name) {
    name = name.toLowerCase(); // make the search case-insensitive
    for (Item item : items) {
      if (item.getName().toLowerCase().equals(name)) {
        return item;
      }
    }
    return null;
  }

  /**
   * Create the items in the game by reading from the "items.txt" file.
   * All of the items in the game are stored in the "items" list.
   * The items are created with their name, description, weight, and effect dialogue.
   */
  private void createItems() {
    ArrayList<String> itemsFile = Reader.readFile("items.txt");
    for (int i = 0; i < itemsFile.size(); i += 6) {
      String name = itemsFile.get(i);
      String description = itemsFile.get(i + 1);
      int weight = Integer.parseInt(itemsFile.get(i + 2));
      String effectDialogue = itemsFile.get(i + 3);
      // if the effectDialogue is "null", set it to actual null:
      effectDialogue = effectDialogue.equals("null") ? null : effectDialogue;
      Station station = getStation(itemsFile.get(i + 4));
      items.add(new Item(name, description, weight, effectDialogue));
      if (station != null) {
        // if the item is stated to be added to a station, then add the item to that station.
        station.getItems().addItem(items.get(items.size() - 1));
      }
    }
  }

  /**
   * Create the characters in the game by reading from the "characters.txt" file,
   * and place them at one of the stations.
   * The characters are created with their name, dialogue, allowed stations, and exchange items.
   */
  private void createCharacters() {
    ArrayList<String> charactersFile = Reader.readFile("characters.txt");
    for (int i = 0; i < charactersFile.size(); i += 6) {
      String name = charactersFile.get(i);
      String dialogue = charactersFile.get(i + 1);
      Item exchangeKey = getItem(charactersFile.get(i + 3));
      Item exchangeValue = getItem(charactersFile.get(i + 4));
      Entry<Item, Item> exchange = (exchangeKey == null || exchangeValue == null ? null : new SimpleEntry<>(exchangeKey, exchangeValue));
      // split the station names by commas:
      ArrayList<String> allowedStationNames = new ArrayList<>(Arrays.asList(charactersFile.get(i + 2).split(",")));
      ArrayList<Station> allowedStations = new ArrayList<>();
      for (String stationName : allowedStationNames) {
        allowedStations.add(getStation(stationName));
      }
      characters.add(new Character(name, dialogue, allowedStations, exchange));
    }
  }
}