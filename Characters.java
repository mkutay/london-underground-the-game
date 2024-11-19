import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.ArrayList;

/**
 * This class represents the characters in the game.
 * 
 * @author Mehmet Kutay Bozkurt
 * @version 1.0
 */
public class Characters {
  private ArrayList<Character> characters;

  /**
   * Constructor - Create the characters in the game.
   * @param tube The tube object that the characters will be placed at.
   */
  public Characters(Tube tube) {
    characters = new ArrayList<>();
    createCharacters(tube);
  }

  /**
   * Create the characters in the game and place them at the stations.
   * Additionally, create the items that the characters will have.
   * @param tube The tube object that the characters will be placed at.
   */
  private void createCharacters(Tube tube) {
    Item oyster = new Item("Oyster", "Your Oyster card. You need this to leave the underground.", 1, new UseEffect() {
      public Entry<Boolean, String> use(Station station) {
        if (station.getName().equals("Bank")) {
          return new SimpleEntry<Boolean, String>(true, "You have left the underground. Congratulations! You have won the game.");
        }

        return new SimpleEntry<Boolean, String>(false, "You cannot use the Oyster card here.");
      }
    });

    Item money = new Item("Money", "Some money that you can use to buy things.", 1, new UseEffect() {
      public Entry<Boolean, String> use(Station station) {
        return new SimpleEntry<Boolean, String>(false, "You cannot use the money here.");
      }
    });

    Item candy = new Item("Candy", "Some candy that you can eat or give to somebody.", 1, new UseEffect() {
      public Entry<Boolean, String> use(Station station) {
        return new SimpleEntry<Boolean, String>(true, "You have eaten the candy.");
      }
    });

    ArrayList<Station> bankStationList = new ArrayList<>();
    bankStationList.add(tube.getStation("Bank"));
    Character staff = new Character("Staff", "Did you know that you can take the Waterloo & City line at Bank station to teleport to a random station on the underground?", bankStationList, null);
    characters.add(staff);

    ArrayList<Station> piccadillyStationList = new ArrayList<>();
    piccadillyStationList.add(tube.getStation("Holborn"));
    piccadillyStationList.add(tube.getStation("Piccadilly Circus"));
    piccadillyStationList.add(tube.getStation("Leicester Square"));
    piccadillyStationList.add(tube.getStation("Covent Garden"));
    Entry<Item, Item> exchangeHomeless = new SimpleEntry<Item, Item>(null, money);
    Character homeless = new Character("Homeless", "I see that you are lost on the underground. Take this money. It may help you leave the station.", piccadillyStationList, exchangeHomeless);
    characters.add(homeless);

    ArrayList<Station> candyManStation = new ArrayList<>();
    candyManStation.add(tube.getStation("Oxford Circus"));
    Entry<Item, Item> exchangeCandyMan = new SimpleEntry<Item, Item>(money, candy);
    Character candyMan = new Character("CandyMan", "Hey I am CandyMan! Would you like to buy some very reasonably priced candy?", candyManStation, exchangeCandyMan);
    characters.add(candyMan);

    ArrayList<Station> districtStationList = new ArrayList<>();
    districtStationList.add(tube.getStation("Embankment"));
    districtStationList.add(tube.getStation("Temple"));
    districtStationList.add(tube.getStation("Blackfriars"));
    districtStationList.add(tube.getStation("Bank"));
    Entry<Item, Item> exchangeChild = new SimpleEntry<Item, Item>(candy, oyster);
    Character child = new Character("Child", "Hey, I want some candy! Do you have some candy?", districtStationList, exchangeChild);
    characters.add(child);
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
   * @return The characters that are on the given station as a Characters object
   */
  public ArrayList<Character> getCharactersOnStation(Station station) {
    ArrayList<Character> charactersOnStation = new ArrayList<>();
    for (Character character : characters) {
      if (character.getCurrentStation().equals(station)) {
        charactersOnStation.add(character);
      }
    }
    return charactersOnStation;
  }

  /**
   * @return The characters that are on the given station as a String
   */
  public String toString() {
    if (characters.isEmpty()) {
      return "";
    }
    String characterString = "You also find the following characters in the station:";
    for (Character character : characters) {
      characterString += "\n  " + character.getName();
    }
    return characterString;
  }
}
