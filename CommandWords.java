import java.util.HashMap;

/**
 * This class is the CommandWords class of the "London Underground"
 * application. "London Underground" is a simple, text based adventure
 * game that was inspired by the stations found in Central London.
 * 
 * This class holds an enumeration of all command words known in the game.
 * It is used to recognise commands as they are typed in. The valid commands
 * are stored in a HashMap, which are initialised in the constructor.
 *
 * @author Michael Kölling, David J. Barnes, and Mehmet Kutay Bozkurt
 * @version 1.0
 */
public class CommandWords {
  private HashMap<String, ValidCommand> validCommands; // key is the command name, value holds the description and the number of words the command should have

  /**
   * Constructor - initialise the command words with their descriptions and
   * the number of words they should have.
   */
  public CommandWords() {
    validCommands = new HashMap<>();
    
    validCommands.put("back", new ValidCommand("\"back\" - Go back to the last station you were on. You can go back as you desire.", 2));
    validCommands.put("take", new ValidCommand("\"take [direction] [line]\" - Take a line to a direction, for instance \"take eastbound piccadilly\".\nOr just \"take eastbound\" or \"take piccadilly\" if there is only one exit that can be determined by the line or direction by itself.", 2, 3));
    validCommands.put("help", new ValidCommand("\"help [command]\" - Print out the help message for the command, for instance \"help take\", or just \"help\" for all commands", 1, 2));
    validCommands.put("quit", new ValidCommand("\"quit\" - Quit the game", 1));
    validCommands.put("pick", new ValidCommand("\"pick [item]\" - Pick up an item, for instance \"pick oyster\"", 2));
    validCommands.put("drop", new ValidCommand("\"drop [item]\" - Drop an item, for instance \"drop oyster\"", 2));
    validCommands.put("inventory", new ValidCommand("\"inventory\" - List all the items in your inventory", 1));
    validCommands.put("use", new ValidCommand("\"use [item]\" - Use an item in your inventory, for instance \"use Oyster\"", 2));
    validCommands.put("give", new ValidCommand("\"give [character] [item]\" - Give an item in your inventory to a character, for instance \"give man Oyster\"", 3));
    validCommands.put("talk", new ValidCommand("\"talk [character]\" - Talk to a character, for instance \"talk man\"", 2));
  }

  /**
   * Check whether a given String is a valid command word.
   * @param commandName The command to check.
   * @return True if it is, false if it isn't.
   */
  public boolean isValidCommand(String commandName, int commandLength) {
    ValidCommand command = validCommands.get(commandName);
    if (command == null) {
      return false;
    }
    return command.verifyCommandLength(commandLength);
  }

  /**
   * Get a given command's description. Returns null if the command doesn't exist.
   * @param commandName The name of the command.
   * @return The description of the command, or null if it doesn't exist.
   */
  public String getCommandDescription(String commandName) {
    return validCommands.get(commandName).getDescription();
  }

  /**
   * @return All valid commands as String.
   */
  public String getAll() {
    String returnString = "  ";
    for (String command : validCommands.keySet()) {
      returnString += command + ", ";
    }

    // removes the last two characters, which are ", " and adds a period.
    return returnString.substring(0, returnString.length() - 2) + ".";
  }
}