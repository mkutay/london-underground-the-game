import java.util.HashMap;

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
  private HashMap<String, CommandAction> commandRegistry;

  /**
   * Constructor - Create the tube, player, and characters objects. Initialises the command registry.
   */
  public Processor() {
    tube = new Tube();
    player = new Player(tube.getStation("Piccadilly Circus")); // set the players location as Piccadilly Circus

    createCommandRegistry();
  }

  /**
   * Initialise the command registry with the commands available in the game.
   */
  private void createCommandRegistry() {
    commandRegistry = new HashMap<>();

    commandRegistry.put("back", new BackCommand());
    commandRegistry.put("take", new TakeCommand());
    commandRegistry.put("pick", new PickCommand());
    commandRegistry.put("drop", new DropCommand());
    commandRegistry.put("use", new UseCommand());
    commandRegistry.put("give", new GiveCommand());
    commandRegistry.put("talk", new TalkCommand());
    commandRegistry.put("inventory", new InventoryCommand());
    commandRegistry.put("quit", new QuitCommand());
    commandRegistry.put("help", new HelpCommand());
  }

  /**
   * Process the command given by the user.
   * @param command The command to be processed.
   * @return The output of the command.
   */
  public String processCommand(Command command) {
    if (command.getWord(0) == null) { // if the user enters nothing or given command is not in ValidCommands
      return "I don't know what you mean. Try typing \"help\" for more information.";
    }

    String commandWord = command.getWord(0).toLowerCase();
    CommandAction cmd = commandRegistry.get(commandWord);
    if (!cmd.verifyCommandLength(command.getWordCount())) {
      // if the command doesn't have the correct number of words
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
    String returnString = player.getCurrentStation().getDescription();
    String characterString = tube.getCharactersDescription(player.getCurrentStation());
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
   * Capitalize the first letter of a given string.
   * Used to make the output more readable.
   */
	public static String capitalizeFirstLetter(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}
}