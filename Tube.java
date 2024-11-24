import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Map.Entry;

/**
 * This class represents the tube map. It creates all the stations and links them together.
 * The tube map is read from the stations.txt and connections.txt files.
 * 
 * @author Mehmet Kutay Bozkurt
 * @version 1.0
 */
public class Tube {
  private ArrayList<Station> stations;
  private ArrayList<Character> characters; // stores characters
  private Reader reader; // helper to read from files
  
  /**
   * Constructory - Initialise the tube by creating the stations and linking them together.
   */
  public Tube() {
    stations = new ArrayList<Station>();
    reader = new Reader();
    characters = new ArrayList<>();

    createStations();
    connectStations();
    createCharacters();
  }
  
  /**
   * Create all the stations after reading from the file.
   * Also create the Random station that is used to transport the player to a random station.
   */
  private void createStations() {
    ArrayList<String> stationsFile = reader.readFile("stations.txt");
    for (int i = 0; i < stationsFile.size(); i += 2) {
      Station station = new Station(stationsFile.get(i), stationsFile.get(i + 1));
      stations.add(station);
    }
    stations.add(new Station("You are now being transported to a random station on the ", "Random"));
  }

  /**
   * Link the station's exits together by reading from the files.
   * Also link the Random station to the Bank station.
   */
  private void connectStations() {
    ArrayList<String> connectionsFile = reader.readFile("connections.txt");
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
      // This should not run, but is used as a precaution.
      System.out.println("ERROR: station(s) not found: " + name1 + ", " + name2);
    }
  }

  /**
   * @return A random station from the tube, not including the Random station.
   */
  public Station getRandomStation() {
    int randomIndex = (int) (Math.random() * (stations.size() - 1));
    return stations.get(randomIndex);
  }

  /**
   * Get the station with the given name.
   * @param name The name of the station.
   * @return The station with the given name, null if not found.
   */
  public Station getStation(String name) {
    for (Station station : stations) {
      if (station.getName().equals(name)) {
        return station;
      }
    }
    return null;
  }

  /**
   * @return the character with the given name, null if it doesn't exist.
   */
  public Character getCharacter(String name) {
    for (Character character : characters) {
      if (character.getName().toLowerCase().equals(name)) {
        return character;
      }
    }
    return null;
  }

  /**
   * Move all characters to a random station that they are allowed to go to.
   */
  public void moveCharacters() {
    for (Character character : characters) {
      character.moveRandom();
    }
  }

  /**
   * @return The description of the characters in the station.
   */
  public String getCharactersDescription(Station playerCurrentStation) {
    String characterString = "";
    for (Character character : characters) {
      if (character.getCurrentStation().equals(playerCurrentStation)) {
        characterString += "\n  " + character.getName();
      }
    }
    return characterString;
  }

  /**
   * Create the characters in the game and place them at one of the stations.
   * Additionally, create the items that the characters will have.
   */
  private void createCharacters() {
    Item oyster = new Item("Oyster", "Your Oyster card. You need this to leave the underground.", 1, "You have left the underground. Congratulations! You have won the game.");

    Item money = new Item("Money", "Some money that you can use to buy things.", 1, null);

    Item candy = new Item("Candy", "Some candy that you can eat or give to somebody.", 1, null);

    ArrayList<Station> bankStationList = new ArrayList<>();
    bankStationList.add(getStation("Bank"));
    Character staff = new Character("Staff", "Did you know that you can take the Waterloo & City line at Bank station to teleport to a random station on the underground?", bankStationList, null);
    characters.add(staff);

    ArrayList<Station> piccadillyStationList = new ArrayList<>();
    piccadillyStationList.add(getStation("Holborn"));
    piccadillyStationList.add(getStation("Piccadilly Circus"));
    piccadillyStationList.add(getStation("Leicester Square"));
    piccadillyStationList.add(getStation("Covent Garden"));
    Entry<Item, Item> exchangeHomeless = new SimpleEntry<Item, Item>(null, money);
    Character homeless = new Character("Homeless", "I see that you are lost on the underground. Take this money. It may help you leave the station.", piccadillyStationList, exchangeHomeless);
    characters.add(homeless);

    ArrayList<Station> candyManStation = new ArrayList<>();
    candyManStation.add(getStation("Oxford Circus"));
    Entry<Item, Item> exchangeCandyMan = new SimpleEntry<Item, Item>(money, candy);
    Character candyMan = new Character("CandyMan", "Hey I am CandyMan! Would you like to buy some very reasonably priced candy?", candyManStation, exchangeCandyMan);
    characters.add(candyMan);

    ArrayList<Station> districtStationList = new ArrayList<>();
    districtStationList.add(getStation("Embankment"));
    districtStationList.add(getStation("Temple"));
    districtStationList.add(getStation("Blackfriars"));
    districtStationList.add(getStation("Bank"));
    Entry<Item, Item> exchangeChild = new SimpleEntry<Item, Item>(candy, oyster);
    Character child = new Character("Child", "Hey, I want some candy! Do you have some candy?", districtStationList, exchangeChild);
    characters.add(child);
  }
}