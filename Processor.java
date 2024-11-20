import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 * This class is the processor class of the "London Underground" application.
 * "London Underground" is a simple, text based adventure game.
 * 
 * This class processes the commands entered by the user and returns
 * the appropriate output. It also holds the Tube, Player, and Characters
 * objects.
 * 
 * @author Mehmet Kutay Bozkurt
 * @version 1.0
 */
public class Processor {
  private Tube tube;
  private Player player;
  private ArrayList<Character> characters;
  private HashMap<String, CommandAction> commandRegistry;

  /**
   * Constructor - Create the tube, player, and characters objects. Initialises the command registry.
   */
  public Processor() {
    tube = new Tube();
    player = new Player(tube.getStation("Piccadilly Circus"));
    characters = new ArrayList<>();

    createCharacters();
    createCommandRegistry();
  }

  /**
   * Initialise the command registry with the commands available in the game.
   */
  private void createCommandRegistry() {
    commandRegistry = new HashMap<>();

    commandRegistry.put("back", new BackCommand(1, 1));
    commandRegistry.put("take", new TakeCommand(3, 2));
    commandRegistry.put("pick", new PickCommand(2, 2));
    commandRegistry.put("drop", new DropCommand(2, 2));
    commandRegistry.put("use", new UseCommand(2, 2));
    commandRegistry.put("give", new GiveCommand(3, 3));
    commandRegistry.put("talk", new TalkCommand(2, 2));
    commandRegistry.put("inventory", new InventoryCommand(1, 1));
    commandRegistry.put("quit", new QuitCommand(1, 1));
    commandRegistry.put("help", new HelpCommand(2, 1));
  }

  /**
   * Process the command given by the user.
   * @param command The command to be processed.
   * @return The output of the command.
   */
  public String processCommand(Command command) {
    if (command.getWord(0) == null) {
      return "I don't know what you mean. Try typing \"help\" for more information.";
    }

    String commandWord = command.getWord(0).toLowerCase();
    CommandAction cmd = commandRegistry.get(commandWord);
    if (!cmd.verifyCommandLength(command.getWordCount())) {
      return incorrectFormat();
    }
    return cmd.execute(command, this);
  }

  /**
   * @return The player.
   */
  public Player getPlayer() {
    return player;
  }

  /**
   * @return The tube.
   */
  public Tube getTube() {
    return tube;
  }

  /**
   * @return The description of the current station, including the items, exits, and characters.
   */
  public String getDescription() {
    Station currentStation = player.getCurrentStation();

    String returnString = currentStation.getDescription();

    String characterString = "";
    for (Character character : characters) {
      if (character.getCurrentStation().equals(player.getCurrentStation())) {
        characterString += "\n  " + character.getName();
      }
    }
    if (!characterString.equals("")) {
      returnString += "\n\nYou also find the following characters in the station:" + characterString;
    }

    return returnString;
  }

  /**
   * @return The string to be displayed when the input format is incorrect.
   */
  private String incorrectFormat() {
    return "Entered input has incorrect format. Please enter again.";
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
   * Create the characters in the game and place them at the stations.
   * Additionally, create the items that the characters will have.
   */
  private void createCharacters() {
    Item oyster = new Item("Oyster", "Your Oyster card. You need this to leave the underground.", 1, "You have left the underground. Congratulations! You have won the game.");

    Item money = new Item("Money", "Some money that you can use to buy things.", 1, "You cannot use the money here.");

    Item candy = new Item("Candy", "Some candy that you can eat or give to somebody.", 1, "You ate the candy.");

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
}