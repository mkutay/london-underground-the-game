import java.util.HashMap;

/**
 * This class holds an enumeration of all command words known to the game
 * "London Underground". It is used to recognise commands as they are
 * typed in. It also holds a description of each command in a HashMap.
 *
 * @author Michael Kölling, David J. Barnes, and Mehmet Kutay Bozkurt
 * @version 1.0
 */
public class CommandWords {
  private HashMap<String, String> validCommands; // key is the command, the value is the description

  /**
   * Constructor - initialise the command words with their descriptions.
   */
  public CommandWords() {
    validCommands = new HashMap<>();
    
    validCommands.put("back", "\"back\" - Go back to the last station you were on. You can go back as you desire.");
    validCommands.put("take", "\"take [direction] [line]\" - Take a line to a direction, for instance \"take eastbound picadilly\".\nOr just \"take eastbound\" or \"take piccadilly\" if there is only one exit that can be determined by the line or direction by itself.");
    validCommands.put("help", "\"help [command]\" - Print out the help message for the command, for instance \"help take\", or just \"help\" for all commands");
    validCommands.put("quit", "\"quit\" - Quit the game");
    validCommands.put("pick", "\"pick [item]\" - Pick up an item, for instance \"pick oyster\"");
    validCommands.put("drop", "\"drop [item]\" - Drop an item, for instance \"drop oyster\"");
    validCommands.put("inventory", "\"inventory\" - List all the items in your inventory");
    validCommands.put("use", "\"use [item]\" - Use an item in your inventory, for instance \"use Oyster\"");
    validCommands.put("give", "\"give [character] [item]\" - Give an item in your inventory to a character, for instance \"give man Oyster\"");
    validCommands.put("talk", "\"talk [character]\" - Talk to a character, for instance \"talk man\"");
  }

  /**
   * Check whether a given String is a valid command word.
   * @param commandName The command to check.
   * @return True if it is, false if it isn't.
   */
  public boolean isValidCommand(String commandName) {
    if (validCommands.get(commandName) == null) {
      return false;
    }
    return true;
  }

  /**
   * Get a given command's description. Returns null if the command doesn't exist.
   * @param commandName The name of the command.
   * @return The description of the command, or null if it doesn't exist.
   */
  public String getCommandDescription(String commandName) {
    return validCommands.get(commandName);
  }

  /**
   * @return All valid commands as String.
   */
  public String getEveryCommand() {
    String returnString = "  ";
    for (String command : validCommands.keySet()) {
      returnString += command + ", ";
    }

    // removes the last two characters, which are ", " and adds a period.
    return returnString.substring(0, returnString.length() - 2) + ".";
  }
}