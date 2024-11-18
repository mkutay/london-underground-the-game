import java.util.ArrayList;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;

/**
 * This class is the processor class of the Game.
 * It processes the commands entered by the user and
 * returns the appropriate output. It also holds the
 * Tube and Player objects.
 * @author Mehmet Kutay Bozkurt
 * @version 1.0
 */
public class Processor {
  private Tube tube;
  private Player player;
  private Characters characters;

  public Processor() {
    tube = new Tube();
    player = new Player(tube.getStartStation());
    characters = new Characters();

    createCharacters(); // initialise the characters
  }

  /**
   * Create the characters in the game.
   */
  private void createCharacters() {
    ArrayList<Station> bankStationList = new ArrayList<>();
    bankStationList.add(tube.getStation("Bank"));
    Character staff = new Character("Staff", "Did you know that you can take the Waterloo & City line at Bank station to teleport to a random station on the underground?", bankStationList);
    characters.addCharacter(staff);

    ArrayList<Station> piccadillyStationList = new ArrayList<>();
    piccadillyStationList.add(tube.getStation("Piccadilly Circus"));
    piccadillyStationList.add(tube.getStation("Leicester Square"));
    piccadillyStationList.add(tube.getStation("Covent Garden"));
    piccadillyStationList.add(tube.getStation("Holborn"));
    Character homeless = new Character("Homeless", "I see that you are lost on the underground. Take this note. It may help you leave the station.", piccadillyStationList);
    characters.addCharacter(homeless);
  }

  /** 
   * Process the "back" command, allowing the player to go back to the previous station.
   * @return String to be outputed to System.out
   */
  public String back(Command command) {
    // the "back" command cannot take parameters
    if (command.hasIndex(1)) {
      return incorrectFormat();
    }

    if (player.getBackStackCount() == 1) {
      return "You cannot go back any further. You are currently at the very beginning.";
    }

    player.popBackStack();
    return getCurrentExits();
  }

  /**
   * Process the "take" command, allowing the player to take a specific
   * line/direction to the next station.
   * @return String to be outputed to System.out
   */
  public String take(Command command) {
    if (!command.hasIndex(1)) {
      return incorrectFormat();
    }

    String word2 = command.getWord(1);
    String word3 = command.getWord(2);

    // trying to leave current station
    Station nextStation = player.getCurrentStation().getExit(word2, word3);

    if (nextStation == null) {
      if (word3 == null) {
        return "There is more than one possible exits. Please be more specific";
      }

      return "You cannot take " + word3 + " " + word3 + " line.";
    }

    // if we're at the invisible "Random" station, get an actual random station
    if (nextStation.getName().equals("Random")) {
      nextStation = tube.getRandomStation();
    }

    player.pushBackStack(nextStation);
    return getCurrentExits();
  }

  /**
   * Process the "pick" command, allowing the player to pick up an item from the station.
   * @return String to be outputed to System.out
   */
  public String pick(Command command) {
    if (!command.hasIndex(1) || command.hasIndex(2)) {
      return incorrectFormat();
    }

    String word2 = command.getWord(1);

    Item item = player.getCurrentStation().getItem(word2);

    if (item == null) {
      return "There is no " + word2 + " in this station.";
    }

    // pickItem returns true if the item was picked up (that is if it was light enough)
    if (player.addItem(item)) {
      player.getCurrentStation().removeItem(item);
      return "You have picked up " + item.getName() + ".";
    }

    return "You cannot pick up " + item.getName() + " because it is too heavy.";
  }

  /**
   * Process the "drop" command, allowing the player to drop an item from
   * their inventory into the station.
   * @return String to be outputed to System.out
   */
  public String drop(Command command) {
    if (!command.hasIndex(1) || command.hasIndex(2)) {
      return incorrectFormat();
    }

    String word2 = command.getWord(1);
    Item item = player.getItem(word2);

    if (item == null) {
      return "You do not have " + word2 + " in your inventory.";
    }

    player.removeItem(item);
    player.getCurrentStation().addItem(item);

    return "You have dropped " + item.getName() + " in your location.";
  }

  /**
   * Process the "use" command, allowing the player to use an item from
   * their inventory.
   * @return Boolean indicating quit sequence and String to be outputed to System.out
   */
  public Entry<Boolean, String> use(Command command) {
    if (!command.hasIndex(1) || command.hasIndex(2)) {
      return new SimpleEntry<Boolean, String>(false, incorrectFormat());
    }

    String word2 = command.getWord(1);
    Item item = player.getItem(word2);

    if (item == null) {
      return new SimpleEntry<Boolean, String>(false, "You do not have " + word2 + " in your inventory.");
    }

    Entry<Boolean, String> used = player.getCurrentStation().useItem(item);

    if (used.getKey()) {
      player.removeItem(item);
    }

    return used;
  }

  /**
   * Process the "give" command, allowing the player to give an item from
   * their inventory to a character.
   * @return String to be outputed to System.out
   */
  public String give(Command command) {
    if (!command.hasIndex(1) || !command.hasIndex(2)) {
      return incorrectFormat();
    }

    String word2 = command.getWord(1);
    String word3 = command.getWord(2);

    Item item = player.getItem(word2);
    if (item == null) {
      return "You do not have " + word2 + " in your inventory.";
    }

    Character character = characters.getCharacter(word3);
    if (character == null) {
      return "There is no character with the name " + word3 + ".";
    }

    Item exchangedItem = character.exchangeItem(item);
    if (exchangedItem == null) {
      return "The character does not want " + item.getName() + ".";
    }

    player.removeItem(item);
    player.addItem(exchangedItem);

    return "You have given " + item.getName() + " and received " + exchangedItem.getName() + " in exchange.";
  }

  /**
   * Process the "talk" command, allowing the player to talk with a character.
   * @return String to be outputed to System.out
   */
  public String talk(Command command) {
    if (!command.hasIndex(1) || command.hasIndex(2)) {
      return incorrectFormat();
    }

    String word2 = command.getWord(1);

    return null;
  }

  /**
   * Process the "inventory" command, allowing the player to see the items in their inventory.
   * @return String to be outputed to System.out
   */
  public String inventory(Command command) {
    if (command.hasIndex(1)) {
      return incorrectFormat();
    }

    return player.getInventory();
  }

  /** 
   * "quit" was entered. Check the rest of the command to see
   * whether we really quit the game.
   * @return null, if this command quits the game, an output otherwise.
   */
  public String quit(Command command) {
    if (command.hasIndex(1)) {
      return incorrectFormat();
    }

    return null; // signal that we want to quit
  }

  /**
   * @return the current exits of the player's current station as a String.
   */
  public String getCurrentExits() {
    return player.getCurrentStation().getDescription();
  }

  /**
   * @return a String indicating that the input has incorrect format.
   */
  private String incorrectFormat() {
    return "Entered input has incorrect format. Please enter again.";
  }
}
