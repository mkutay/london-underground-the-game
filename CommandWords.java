import java.util.HashMap;

/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class CommandWords {
  private HashMap<String, String> validCommands; // key is the command, the value is the description

  /**
   * Constructor - initialise the command words with their descriptions.
   */
  public CommandWords() {
    validCommands = new HashMap<>();
    
    validCommands.put("go", "");
    validCommands.put("take", "");
    validCommands.put("help", "");
    validCommands.put("quit", "");
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
   * Print all valid commands to System.out.
   */
  public void showAll() {
    for (String command : validCommands.keySet()) {
      System.out.print(command + "  ");
    }
    System.out.println();
  }
}
