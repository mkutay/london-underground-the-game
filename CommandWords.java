import java.util.HashMap;

/**
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in. It also holds a
 * description of each command.
 *
 * @author Michael Kölling, David J. Barnes, Mehmet Kutay Bozkurt
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
    validCommands.put("take", "\"take [direction] [line]\" - Take a line to a direction, for instance \"take eastbound picadilly\", or just \"take eastbound\" or \"take piccadilly\" if there is only one exit that can be determined by the line or direction by itself.");
    validCommands.put("help", "\"help [command]\" - Print out the help message for the command, for instance \"help go\", or just \"help\" for all commands");
    validCommands.put("quit", "\"quit\" - Quit the game");
    validCommands.put("pick", "\"pick [item]\" - Pick up an item, for instance \"pick card\"");
    validCommands.put("drop", "\"drop [item]\" - Drop an item, for instance \"drop card\"");
    validCommands.put("inventory", "\"inventory\" - List all the items in your inventory");
  }

  /**
   * Check whether a given String is a valid command word. 
   * @return true if it is, false if it isn't.
   */
  public boolean isCommand(String command) {
    return validCommands.get(command) != null;
  }

  /**
   * Get a given command's description. Returns null if the command doesn't exist.
   * @return The description of the command or null.
   */
  public String getCommandDescription(String command) {
    return validCommands.get(command);
  }

  /**
   * @return all valid commands as String.
   */
  public String getAll() {
    String returnString = "";
    for (String command : validCommands.keySet()) {
      returnString += command + "  ";
    }
    return returnString;
  }
}
